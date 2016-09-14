package eu.inloop.viewmodel.sample.viewmodel;

import android.util.Log;

import eu.inloop.viewmodel.AbstractViewModel;
import eu.inloop.viewmodel.IView;


public class SampleArgumentViewModel extends AbstractViewModel<IView> {

    public void setUserId(int userId) {
        Log.d("SampleArgumentViewModel", "userId passed to ViewModel with the value of - " + userId);
    }

    public void viewModelRestored() {
        Log.d("SampleArgumentViewModel", "Application killed by system, viewmodel is restored");
    }
}
