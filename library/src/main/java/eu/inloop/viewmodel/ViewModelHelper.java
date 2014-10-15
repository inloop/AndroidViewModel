package eu.inloop.viewmodel;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;

import java.util.UUID;

public class ViewModelHelper<T extends IView, R extends AbstractViewModel<T>> {

    private String mScreenId;
    private R mViewModel;
    private boolean mModelRemoved;

    public void onCreate(@NonNull Activity activity, @Nullable Bundle savedInstanceState,
                         @NonNull Class<? extends AbstractViewModel<T>> viewModelClass) {
        if (viewModelClass == null) {
            //no viewmodel for this fragment
            mViewModel = null;
            return;
        }
        if (savedInstanceState == null) {
            mScreenId = UUID.randomUUID().toString();
        } else {
            mScreenId = savedInstanceState.getString("identifier");
        }

        final ViewModelProvider.ViewModelWrapper<T> viewModelWrapper = getViewModelProvider(activity).getViewModelProvider().getViewModel(mScreenId, viewModelClass);
        mViewModel = (R) viewModelWrapper.viewModel;
        if (savedInstanceState != null && viewModelWrapper.wasCreated) {
            Log.d("model", "Fragment recreated by system - restoring viewmodel");
            mViewModel.restoreState(savedInstanceState);
        }
    }

    public void initWithView(T view) {
        if (mViewModel == null) {
            //no viewmodel for this fragment
            return;
        }
        mViewModel.initWithView(view);
    }

    public void onDestroyView(@NonNull Fragment fragment) {
        if (mViewModel == null) {
            //no viewmodel for this fragment
            return;
        }
        mViewModel.clearView();
        if (fragment.getActivity() != null && fragment.getActivity().isFinishing()) {
            removeViewModel(fragment.getActivity());
        }
    }

    public void onDestroy(@NonNull Fragment fragment) {
        if (mViewModel == null) {
            //no viewmodel for this fragment
            return;
        }
        if (fragment.getActivity().isFinishing()) {
            removeViewModel(fragment.getActivity());
        } else if (fragment.isRemoving()) {
            Log.d("mode", "Removing viewmodel - fragment replaced");
            removeViewModel(fragment.getActivity());
        }
    }

    public void onDestroy(@NonNull Activity activity) {
        if (mViewModel == null) {
            //no viewmodel for this fragment
            return;
        }
        mViewModel.clearView();
        if (activity.isFinishing()) {
            removeViewModel(activity);
        }
    }

    public void onStop() {
        if (mViewModel == null) {
            //no viewmodel for this fragment
            return;
        }
        mViewModel.onStop();
    }

    public void onStart() {
        if (mViewModel == null) {
            //no viewmodel for this fragment
            return;
        }
        mViewModel.onStart();
    }

    public R getViewModel() {
        return mViewModel;
    }

    public void onSaveInstanceState(@Nullable Bundle bundle) {
        bundle.putString("identifier", mScreenId);
        if (mViewModel != null) {
            mViewModel.saveState(bundle);
        }
    }

    private IViewModelProvider getViewModelProvider(@NonNull Activity activity) {
        if (!(activity instanceof IViewModelProvider)) {
            throw new IllegalStateException("Your activity must implement IViewModelProvider");
        }
        return ((IViewModelProvider)activity);
    }

    protected boolean removeViewModel(@NonNull Activity activity) {
        if (!mModelRemoved) {
            boolean removed = getViewModelProvider(activity).getViewModelProvider().remove(mScreenId);
            mViewModel.onModelRemoved();
            mModelRemoved = true;
            return removed;
        } else {
            return false;
        }
    }
}
