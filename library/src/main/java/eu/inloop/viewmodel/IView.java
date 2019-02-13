package eu.inloop.viewmodel;

import android.app.Activity;
import androidx.annotation.Nullable;

import eu.inloop.viewmodel.base.ViewModelBaseActivity;
import eu.inloop.viewmodel.base.ViewModelBaseFragment;
import eu.inloop.viewmodel.binding.ViewModelBindingConfig;

/**
 * Any Activity or Fragment that needs a ViewModel needs to implement this interface.
 * You don't need to implement it yourself - use {@link ViewModelBaseActivity} and
 * {@link ViewModelBaseFragment} instead.
 */
public interface IView {
    /**
     * This method is used for Data Binding to bind correct layout and variable automatically
     * Can return null value in case that Data Binding is not used.
     *
     * @return defined ViewModelBinding Config for a specific screen.
     */
    @Nullable
    ViewModelBindingConfig getViewModelBindingConfig();

    /**
     * Implement this method to remove the ViewModel associated with the Fragment or Activity.
     * This is usually implemented by calling {@link ViewModelHelper#removeViewModel(Activity)},
     * see {@link ViewModelBaseActivity#removeViewModel()} and {@link ViewModelBaseFragment#removeViewModel()}.
     */
    void removeViewModel();
}
