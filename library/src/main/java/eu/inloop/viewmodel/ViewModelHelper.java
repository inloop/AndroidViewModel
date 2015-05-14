package eu.inloop.viewmodel;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

public class ViewModelHelper<T extends IView, R extends AbstractViewModel<T>> {

    private String mScreenId;
    private R mViewModel;
    private boolean mModelRemoved;
    private boolean mOnSaveInstanceCalled;

    /**
     * Call from {@link android.app.Activity#onCreate(android.os.Bundle)} or
     * {@link android.support.v4.app.Fragment#onCreate(android.os.Bundle)}
     * @param savedInstanceState savedInstance state from {@link Activity#onCreate(Bundle)} or
     *                           {@link Fragment#onCreate(Bundle)}
     * @param viewModelClass the {@link Class} of your ViewModel
     * @param arguments pass {@link Fragment#getArguments()}  or
     *                  {@link Activity#getIntent()}.{@link Intent#getExtras() getExtras()}
     */
    public void onCreate(@Nullable Bundle savedInstanceState,
                         @Nullable Class<? extends AbstractViewModel<T>> viewModelClass,
                         @Nullable Bundle arguments) {
        // no viewmodel for this fragment
        if (viewModelClass == null) {
            mViewModel = null;
            return;
        }

        // screen (activity/fragment) created for first time, attach unique ID
        if (savedInstanceState == null) {
            mScreenId = UUID.randomUUID().toString();
        } else {
            mScreenId = savedInstanceState.getString("identifier");
            mOnSaveInstanceCalled = false;
        }

        // get model instance for this screen
        final ViewModelProvider.ViewModelWrapper<T> viewModelWrapper = ViewModelProvider.getInstance().getViewModel(mScreenId, viewModelClass);
        //noinspection unchecked
        mViewModel = (R) viewModelWrapper.viewModel;

        if (viewModelWrapper.wasCreated) {
            // detect that the system has killed the app - saved instance is not null, but the model was recreated
            if (BuildConfig.DEBUG && savedInstanceState != null) {
                Log.d("model", "Fragment recreated by system - restoring viewmodel");
            }
            mViewModel.onCreate(arguments, savedInstanceState);
        }
    }

    /**
     * Call from {@link android.support.v4.app.Fragment#onViewCreated(android.view.View, android.os.Bundle)}
     * or {@link android.app.Activity#onCreate(android.os.Bundle)}
     * @param view
     */
    public void initWithView(@NonNull T view) {
        if (mViewModel == null) {
            //no viewmodel for this fragment
            return;
        }
        mViewModel.initWithView(view);
    }

    /**
     * Use in case this model is associated with an {@link android.support.v4.app.Fragment}
     * Call from {@link android.support.v4.app.Fragment#onDestroyView()}. Use in case model is associated
     * with Fragment
     * @param fragment
     */
    public void onDestroyView(@NonNull Fragment fragment) {
        if (mViewModel == null) {
            //no viewmodel for this fragment
            return;
        }
        mViewModel.clearView();
        if (fragment.getActivity() != null && fragment.getActivity().isFinishing()) {
            removeViewModel();
        }
    }

    /**
     * Use in case this model is associated with an {@link android.support.v4.app.Fragment}
     * Call from {@link android.support.v4.app.Fragment#onDestroy()}
     * @param fragment
     */
    public void onDestroy(@NonNull Fragment fragment) {
        if (mViewModel == null) {
            //no viewmodel for this fragment
            return;
        }
        if (fragment.getActivity().isFinishing()) {
            removeViewModel();
        } else if (fragment.isRemoving() && !mOnSaveInstanceCalled) {
            // The fragment can be still in backstack even if isRemoving() is true.
            // We check mOnSaveInstanceCalled - if this was not called then the fragment is totally removed.
            if (BuildConfig.DEBUG) {
                Log.d("mode", "Removing viewmodel - fragment replaced");
            }
            removeViewModel();
        }
    }

    /**
     * Use in case this model is associated with an {@link android.app.Activity}
     * Call from {@link android.app.Activity#onDestroy()}
     * @param activity
     */
    public void onDestroy(@NonNull Activity activity) {
        if (mViewModel == null) {
            //no viewmodel for this fragment
            return;
        }
        mViewModel.clearView();
        if (activity.isFinishing()) {
            removeViewModel();
        }
    }

    /**
     * Call from {@link android.app.Activity#onStop()} or {@link android.support.v4.app.Fragment#onStop()}
     */
    public void onStop() {
        if (mViewModel == null) {
            //no viewmodel for this fragment
            return;
        }
        mViewModel.onStop();
    }

    /**
     * Call from {@link android.app.Activity#onStart()} ()} or {@link android.support.v4.app.Fragment#onStart()} ()}
     */
    public void onStart() {
        if (mViewModel == null) {
            //no viewmodel for this fragment
            return;
        }
        mViewModel.onStart();
    }


    @Nullable
    public R getViewModel() {
        return mViewModel;
    }

    /**
     * Call from {@link android.app.Activity#onSaveInstanceState(android.os.Bundle)}
     * or {@link android.support.v4.app.Fragment#onSaveInstanceState(android.os.Bundle)}.
     * This allows the model to save its state.
     * @param bundle
     */
    public void onSaveInstanceState(@NonNull Bundle bundle) {
        bundle.putString("identifier", mScreenId);
        if (mViewModel != null) {
            mViewModel.saveState(bundle);
            mOnSaveInstanceCalled = true;
        }
    }

    private void removeViewModel() {
        if (!mModelRemoved) {
            ViewModelProvider.getInstance().remove(mScreenId);
            mViewModel.onModelRemoved();
            mModelRemoved = true;
        }
    }
}
