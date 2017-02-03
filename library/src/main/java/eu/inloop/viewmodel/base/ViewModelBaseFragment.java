package eu.inloop.viewmodel.base;

import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

import eu.inloop.viewmodel.AbstractViewModel;
import eu.inloop.viewmodel.IView;
import eu.inloop.viewmodel.IViewModelFactory;
import eu.inloop.viewmodel.ViewModelHelper;

public abstract class ViewModelBaseFragment<T extends IView, R extends AbstractViewModel<T>> extends Fragment implements IView, IViewModelFactory<T, R> {

    @NonNull
    private final ViewModelHelper<T, R> mViewModeHelper = new ViewModelHelper<>();

    @CallSuper
    @Override
    public void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModeHelper.onCreate(getActivity(), savedInstanceState, this, getArguments());
    }

    @NonNull
    @Override
    public abstract R createViewModel();

    /**
     * Call this after your view is ready - usually on the end of {@link Fragment#onViewCreated(View, Bundle)}
     *
     * @param view view
     */
    protected void setModelView(@NonNull final T view) {
        mViewModeHelper.setView(view);
    }

    @CallSuper
    @Override
    public void onSaveInstanceState(@NonNull final Bundle outState) {
        super.onSaveInstanceState(outState);
        mViewModeHelper.onSaveInstanceState(outState);
    }

    @CallSuper
    @Override
    public void onStart() {
        super.onStart();
        mViewModeHelper.onStart();
    }

    @CallSuper
    @Override
    public void onStop() {
        super.onStop();
        mViewModeHelper.onStop();
    }

    @CallSuper
    @Override
    public void onDestroyView() {
        mViewModeHelper.onDestroyView(this);
        super.onDestroyView();
    }

    @CallSuper
    @Override
    public void onDestroy() {
        mViewModeHelper.onDestroy(this);
        super.onDestroy();
    }

    /**
     * @see ViewModelHelper#getViewModel()
     */
    @NonNull
    @SuppressWarnings("unused")
    public R getViewModel() {
        return mViewModeHelper.getViewModel();
    }
}
