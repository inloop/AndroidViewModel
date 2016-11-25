package com.tamaslosonczi.viewmodel;

import android.support.annotation.Nullable;

import com.tamaslosonczi.viewmodel.base.ViewModelBaseActivity;

/**
 * Your {@link android.app.Activity} must implement this interface if
 * any of the contained Fragments the {@link ViewModelHelper}
 */
public interface IViewModelProvider {

    /**
     * See {@link ViewModelBaseActivity} on how to implement.
     * @return the {@link ViewModelProvider}.
     */
    @Nullable
    ViewModelProvider getViewModelProvider();
}