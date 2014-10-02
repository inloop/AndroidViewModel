package eu.inloop.viewmodel.base;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import eu.inloop.viewmodel.IViewModelProvider;
import eu.inloop.viewmodel.ViewModelProvider;

public class ViewModelBaseActivity extends FragmentActivity implements IViewModelProvider {

    private ViewModelProvider mViewModelService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //This code must be execute prior to super.onCreate()
        if (getLastCustomNonConfigurationInstance() == null) {
            mViewModelService = new ViewModelProvider();
        } else {
            mViewModelService = (ViewModelProvider) getLastCustomNonConfigurationInstance();
        }
        super.onCreate(savedInstanceState);
    }

    @Override
    public Object onRetainCustomNonConfigurationInstance() {
        return mViewModelService;
    }

    @Override
    public ViewModelProvider getViewModelProvider() {
        return mViewModelService;
    }

    @Override
    protected void onStop() {
        if (isFinishing()) {
            mViewModelService.removeAllViewModels();
        }
        super.onStop();
    }
}
