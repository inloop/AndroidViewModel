package eu.inloop.viewmodel.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import eu.inloop.viewmodel.AbstractViewModel;
import eu.inloop.viewmodel.IView;
import eu.inloop.viewmodel.ViewModelHelper;

public abstract class ViewModelBaseActivity<T extends IView, R extends AbstractViewModel<T>> extends AppCompatActivity implements IView {

    private final ViewModelHelper<T, R> mViewModeHelper = new ViewModelHelper<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModeHelper.onCreate(savedInstanceState, getViewModelClass());
        //noinspection unchecked
        mViewModeHelper.initWithView((T) this);
    }

    public abstract Class<R> getViewModelClass();

    @Override
    public void onSaveInstanceState(Bundle outState) {
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

    @SuppressWarnings("unused")
    public R getViewModel() {
        return mViewModeHelper.getViewModel();
    }
}
