package eu.inloop.viewmodel.sample;

import android.app.Application;

import static eu.inloop.viewmodel.sample.component.SampleApplicationComponent.Injector.inject;


public class SampleApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        inject(this);
    }

}
