package eu.inloop.viewmodel;

/**
 * Your {@link android.app.Activity} must implement this interface if
 * any of the contained Fragments the {@link eu.inloop.viewmodel.ViewModelHelper}
 */
public interface IViewModelProvider {

    /**
     * See {@link eu.inloop.viewmodel.base.ViewModelBaseActivity} on how to implement.
     * @return
     */
    public ViewModelProvider getViewModelProvider();
}