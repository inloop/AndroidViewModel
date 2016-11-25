package com.tamaslosonczi.viewmodel.sample.module;

import dagger.Module;
import dagger.Provides;
import com.tamaslosonczi.viewmodel.sample.activity.ViewPagerActivity;


/**
 * Created by losonczitamas on 10/5/16.
 */
@Module
public class ViewPagerModule {

    private final ViewPagerActivity mViewPagerActivity;

    public ViewPagerModule(ViewPagerActivity viewPagerActivity) {
        mViewPagerActivity = viewPagerActivity;
    }

    @Provides
    ViewPagerActivity.TestPagerAdapter provideTestPagerAdapter() {
        return new ViewPagerActivity.TestPagerAdapter(mViewPagerActivity.getSupportFragmentManager());
    }

}
