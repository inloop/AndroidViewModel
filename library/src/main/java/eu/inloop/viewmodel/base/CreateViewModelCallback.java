package eu.inloop.viewmodel.base;

import eu.inloop.viewmodel.AbstractViewModel;
import eu.inloop.viewmodel.IView;

public interface CreateViewModelCallback<T extends IView, R extends AbstractViewModel<T>> {
    R onViewModelRequested();
}
