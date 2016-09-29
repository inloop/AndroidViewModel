package eu.inloop.viewmodel.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import eu.inloop.viewmodel.AbstractViewModel;
import eu.inloop.viewmodel.IView;
import eu.inloop.viewmodel.IViewModelFactory;
import eu.inloop.viewmodel.ViewModelHelper;

public abstract class ViewModelBaseActivity<T extends IView, R extends AbstractViewModel<T>> extends ViewModelBaseEmptyActivity implements IView  {

    private final ViewModelHelper<T, R> mViewModeHelper = new ViewModelHelper<>();

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModeHelper.onCreate(this, savedInstanceState, getViewModelFactory(), getIntent().getExtras());
    }

    public abstract IViewModelFactory getViewModelFactory();

    /**
     * Call this after your view is ready - usually on the end of {@link android.app.Activity#onCreate(Bundle)}
     * @param view view
     */
    @SuppressWarnings("unused")
    public void setView(@NonNull final T view) {
        mViewModeHelper.setView(view);
    }


    @Override
    public void onSaveInstanceState(@NonNull final Bundle outState) {
        super.onSaveInstanceState(outState);
        mViewModeHelper.onSaveInstanceState(outState);
    }

    @Override
    public void onStart() {
        super.onStart();
        mViewModeHelper.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
        mViewModeHelper.onStop();
    }

    @Override
    public void onDestroy() {
        mViewModeHelper.onDestroy(this);
        super.onDestroy();
    }

    /**
     * @see ViewModelHelper#getViewModel()
     */
    @SuppressWarnings("unused")
    @NonNull
    public R getViewModel() {
        return mViewModeHelper.getViewModel();
    }

}
