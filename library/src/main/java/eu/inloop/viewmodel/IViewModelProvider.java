package eu.inloop.viewmodel;

import androidx.annotation.Nullable;

/**
 * Your {@link android.app.Activity} must implement this interface if
 * any of the contained Fragments the {@link eu.inloop.viewmodel.ViewModelHelper}
 */
public interface IViewModelProvider {

    /**
     * See {@link eu.inloop.viewmodel.base.ViewModelBaseActivity} on how to implement.
     * @return the {@link ViewModelProvider}.
     */
    @Nullable
    ViewModelProvider getViewModelProvider();
}