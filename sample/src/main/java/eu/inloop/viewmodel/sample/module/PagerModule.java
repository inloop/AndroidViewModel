package eu.inloop.viewmodel.sample.module;

import dagger.Module;
import dagger.Provides;
import eu.inloop.viewmodel.sample.fragment.PagerFragment;
import eu.inloop.viewmodel.sample.viewmodel.PageModel;


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
