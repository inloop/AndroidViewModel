package eu.inloop.viewmodel.sample.fragment;

import butterknife.ButterKnife;
import eu.inloop.viewmodel.AbstractViewModel;
import eu.inloop.viewmodel.IView;
import eu.inloop.viewmodel.base.ViewModelBaseFragment;

public abstract class ProjectBaseFragment<T extends IView, R extends AbstractViewModel<T>> extends ViewModelBaseFragment<T,R> {

    @Override
    public void onDestroyView() {
        ButterKnife.reset(this);
        super.onDestroyView();
    }
}
