package com.tamaslosonczi.viewmodel.sample;

import android.app.Application;

import static com.tamaslosonczi.viewmodel.sample.component.SampleApplicationComponent.Injector.inject;


public class SampleApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        inject(this);
    }

}
