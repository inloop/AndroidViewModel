package com.tamaslosonczi.viewmodel.sample.component;

import com.squareup.leakcanary.RefWatcher;

import javax.inject.Singleton;

import dagger.Component;
import com.tamaslosonczi.viewmodel.sample.SampleApplication;
import com.tamaslosonczi.viewmodel.sample.module.SampleApplicationModule;


/**
 * Created by losonczitamas on 10/5/16.
 */
@Singleton
@Component(modules = SampleApplicationModule.class)
public interface SampleApplicationComponent {

    void inject(SampleApplication sampleApplication);

    RefWatcher refWatcher();

    final class Injector {

        private static SampleApplicationComponent appComponent;

        private Injector() {
        }

        public static void inject(SampleApplication sampleApplication) {
            appComponent = DaggerSampleApplicationComponent.builder()
                    .sampleApplicationModule(new SampleApplicationModule(sampleApplication))
                    .build();

            appComponent.inject(sampleApplication);
        }

        public static SampleApplicationComponent component() {
            return appComponent;
        }

    }

}
