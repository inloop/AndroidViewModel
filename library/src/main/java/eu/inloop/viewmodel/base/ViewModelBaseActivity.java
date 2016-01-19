package eu.inloop.viewmodel.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import eu.inloop.viewmodel.AbstractViewModel;
import eu.inloop.viewmodel.IView;
import eu.inloop.viewmodel.IViewModelProvider;
import eu.inloop.viewmodel.ViewModelHelper;
import eu.inloop.viewmodel.ViewModelProvider;

public abstract class ViewModelBaseActivity<T extends IView, R extends AbstractViewModel<T>> extends AppCompatActivity implements IView, IViewModelProvider {

    private final ViewModelHelper<T, R> mViewModeHelper = new ViewModelHelper<>();
    private ViewModelProvider mViewModelProvider;

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        //This code must be execute prior to super.onCreate()
        mViewModelProvider = ViewModelProvider.newInstance(this);
        super.onCreate(savedInstanceState);
        mViewModeHelper.onCreate(this, savedInstanceState, getViewModelClass(), getIntent().getExtras());
    }

    /**
     * Call this after your view is ready - usually on the end of {@link android.app.Activity#onCreate(Bundle)}
     * @param view view
     */
    @SuppressWarnings("unused")
    public void setModelView(@NonNull final T view) {
        mViewModeHelper.setView(view);
    }

    public abstract Class<R> getViewModelClass();

    @Override
    @Nullable
    public Object onRetainCustomNonConfigurationInstance() {
        return mViewModelProvider;
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
        if (isFinishing()) {
            mViewModelProvider.removeAllViewModels();
        }
    }

    @Override
    public void onDestroy() {
        mViewModeHelper.onDestroy(this);
        super.onDestroy();
    }

    @SuppressWarnings("unused")
    @Nullable
    public R getViewModel() {
        return mViewModeHelper.getViewModel();
    }

    @Override
    public ViewModelProvider getViewModelProvider() {
        return mViewModelProvider;
    }
}
