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
import eu.inloop.viewmodel.ProxyViewHelper;
import eu.inloop.viewmodel.ViewModelHelper;
import eu.inloop.viewmodel.binding.ViewModelBindingConfig;

public abstract class ViewModelBaseFragment<T extends IView, R extends AbstractViewModel<T>> extends Fragment implements IView {

    @NonNull
    private final ViewModelHelper<T, R> mViewModelHelper = new ViewModelHelper<>();

    @CallSuper
    @Override
    public void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        IViewModelFactory<T, R> viewModelFactory = getViewModelFactory();
        // try to extract the ViewModel class from the implementation
        if (viewModelFactory == null) {
            //noinspection unchecked
            final Class<? extends AbstractViewModel<T>> viewModelClass = (Class<? extends AbstractViewModel<T>>) ProxyViewHelper.getGenericType(getClass(), AbstractViewModel.class);
            if (viewModelClass != null) {
                viewModelFactory = new  IViewModelFactory<T, R>() {
                    @Nullable
                    @Override
                    public R createViewModel() {
                        try {
                            return (R) viewModelClass.newInstance();
                        } catch (java.lang.InstantiationException e) {
                            throw new RuntimeException(e);
                        } catch (IllegalAccessException e) {
                            throw new RuntimeException(e);
                        }
                    }
                };
            }
        }
        getViewModelHelper().onCreate(getActivity(), savedInstanceState, viewModelFactory, getArguments());
    }

    @CallSuper
    @Override
    public void onSaveInstanceState(@NonNull final Bundle outState) {
        super.onSaveInstanceState(outState);
        getViewModelHelper().onSaveInstanceState(outState);
    }

    @CallSuper
    @Override
    public void onStart() {
        super.onStart();
        getViewModelHelper().onStart();
    }

    @CallSuper
    @Override
    public void onStop() {
        super.onStop();
        getViewModelHelper().onStop();
    }

    @CallSuper
    @Override
    public void onDestroyView() {
        getViewModelHelper().onDestroyView(this);
        super.onDestroyView();
    }

    @CallSuper
    @Override
    public void onDestroy() {
        getViewModelHelper().onDestroy(this);
        super.onDestroy();
    }

    @Nullable
    public IViewModelFactory<T, R> getViewModelFactory() {
        return null;
    }

    /**
     * @see ViewModelHelper#getViewModel()
     */
    @NonNull
    @SuppressWarnings("unused")
    public R getViewModel() {
        return getViewModelHelper().getViewModel();
    }

    @Nullable
    @Override
    public ViewModelBindingConfig getViewModelBindingConfig() {
        return null;
    }

    @NonNull
    public ViewModelHelper<T, R> getViewModelHelper() {
        return mViewModelHelper;
    }

    @Override
    public void removeViewModel() {
        mViewModelHelper.removeViewModel(getActivity());
    }

    /**
     * Call this after your view is ready - usually on the end of {@link
     * Fragment#onViewCreated(View, Bundle)}
     *
     * @param view view
     */
    protected void setModelView(@NonNull final T view) {
        getViewModelHelper().setView(view);
    }
}
