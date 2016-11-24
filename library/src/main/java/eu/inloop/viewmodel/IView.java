package eu.inloop.viewmodel;

import android.support.annotation.Nullable;

import eu.inloop.viewmodel.binding.ViewModelBindingConfig;

public interface IView {
    /**
     * This method is used for Data Binding to bind correct layout and variable atomatically
     * Can return null value in case that Data Binding is not used.
     *
     * @return defined ViewModelBinding Config for a specific screen.
     */
    @Nullable
    ViewModelBindingConfig getViewModelBindingConfig();
}
