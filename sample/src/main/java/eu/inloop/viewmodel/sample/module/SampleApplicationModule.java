package eu.inloop.viewmodel.sample.module;

import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import eu.inloop.viewmodel.sample.SampleApplication;


/**
 * Created by losonczitamas on 10/5/16.
 */
@Module
public class SampleApplicationModule {

    private final SampleApplication mSampleApplication;

    public SampleApplicationModule(SampleApplication sampleApplication) {
        mSampleApplication = sampleApplication;
    }

    @Provides
    @Singleton
    RefWatcher provideRefWatcher() {
        return LeakCanary.install(mSampleApplication);
    }

}
