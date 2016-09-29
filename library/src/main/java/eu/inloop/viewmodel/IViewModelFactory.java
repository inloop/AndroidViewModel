package eu.inloop.viewmodel;

/**
 * Created by losonczitamas on 9/28/16.
 */
public interface IViewModelFactory <T extends IView> {

	AbstractViewModel<T> createViewModel();

}
