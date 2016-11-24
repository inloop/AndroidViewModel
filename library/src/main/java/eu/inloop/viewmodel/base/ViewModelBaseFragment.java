package eu.inloop.viewmodel.base;

import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

import eu.inloop.viewmodel.AbstractViewModel;
import eu.inloop.viewmodel.IView;
import eu.inloop.viewmodel.ViewModelHelper;

public abstract class ViewModelBaseFragment<T extends IView, R extends AbstractViewModel<T>> extends Fragment implements IView {

    @NonNull
    private final ViewModelHelper<T, R> mViewModeHelper = new ViewModelHelper<>();

    @CallSuper
    @Override
    public void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getViewModeHelper().onCreate(getActivity(), savedInstanceState, getViewModelClass(), getArguments());
    }

    @CallSuper
    @Override
    public void onSaveInstanceState(@NonNull final Bundle outState) {
        super.onSaveInstanceState(outState);
        getViewModeHelper().onSaveInstanceState(outState);
    }

    @CallSuper
    @Override
    public void onStart() {
        super.onStart();
        getViewModeHelper().onStart();
    }

    @CallSuper
    @Override
    public void onStop() {
        super.onStop();
        getViewModeHelper().onStop();
    }

    @CallSuper
    @Override
    public void onDestroyView() {
        getViewModeHelper().onDestroyView(this);
        super.onDestroyView();
    }

    @CallSuper
    @Override
    public void onDestroy() {
        getViewModeHelper().onDestroy(this);
        super.onDestroy();
    }

    @Nullable
    public abstract Class<R> getViewModelClass();

    /**
     * @see ViewModelHelper#getViewModel()
     */
    @NonNull
    @SuppressWarnings("unused")
    public R getViewModel() {
        return getViewModeHelper().getViewModel();
    }

    @NonNull
    public ViewModelHelper<T, R> getViewModeHelper() {
        return mViewModeHelper;
    }

    /**
     * Call this after your view is ready - usually on the end of {@link
     * Fragment#onViewCreated(View, Bundle)}
     *
     * @param view view
     */
    protected void setModelView(@NonNull final T view) {
        getViewModeHelper().setView(view);
    }
}
