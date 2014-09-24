package eu.inloop.viewmodel.sample.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

import butterknife.ButterKnife;
import eu.inloop.viewmodel.library.AbstractViewModel;
import eu.inloop.viewmodel.library.ViewModelHelper;
import eu.inloop.viewmodel.library.IView;

public abstract class ProjectBaseFragment<T extends IView, R extends AbstractViewModel<T>> extends Fragment {

    private ViewModelHelper<T, R> mViewModeHelper = new ViewModelHelper<T, R>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModeHelper.onCreate(savedInstanceState, getViewModelClass());
    }

    public abstract Class<? extends AbstractViewModel<T>> getViewModelClass();

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewModeHelper.initWithView((T) this);
        ButterKnife.inject(this, view);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mViewModeHelper.onSaveInstanceState(outState);
    }

    @Override
    public void onDestroyView() {
        ButterKnife.reset(this);
        mViewModeHelper.onDestroyView(this);
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        mViewModeHelper.onDestroy(this);
        super.onDestroy();
    }
}
