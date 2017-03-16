package eu.inloop.viewmodel;

import android.support.annotation.Nullable;

public interface IViewModelFactory <T extends IView, R extends AbstractViewModel<T>> {

    @Nullable
    R createViewModel();
}