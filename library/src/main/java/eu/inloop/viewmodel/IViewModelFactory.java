package eu.inloop.viewmodel;

import android.support.annotation.NonNull;

public interface IViewModelFactory <T extends IView, R extends AbstractViewModel<T>> {

    @NonNull
    R createViewModel();
}
