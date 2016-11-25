package com.tamaslosonczi.viewmodel;

import android.support.annotation.NonNull;

public interface IViewModelFactory <T extends IView> {

    @NonNull
    AbstractViewModel<T> createViewModel();
}
