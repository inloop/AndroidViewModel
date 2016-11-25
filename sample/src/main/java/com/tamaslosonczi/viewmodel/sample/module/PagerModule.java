package com.tamaslosonczi.viewmodel.sample.module;

import dagger.Module;
import dagger.Provides;
import com.tamaslosonczi.viewmodel.sample.fragment.PagerFragment;
import com.tamaslosonczi.viewmodel.sample.viewmodel.PageModel;


/**
 * Created by losonczitamas on 10/5/16.
 */
@Module
public class PagerModule {

    private final PagerFragment mPagerFragment;

    public PagerModule(PagerFragment pagerFragment) {
        mPagerFragment = pagerFragment;
    }

    @Provides
    PageModel providePageModel() {
        return new PageModel();
    }
}
