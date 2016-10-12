package eu.inloop.viewmodel.sample.component;

import dagger.Component;
import eu.inloop.viewmodel.sample.PerActivity;
import eu.inloop.viewmodel.sample.fragment.PagerFragment;
import eu.inloop.viewmodel.sample.module.PagerModule;


/**
 * Created by losonczitamas on 10/5/16.
 */
@PerActivity
@Component(modules = PagerModule.class, dependencies = SampleApplicationComponent.class)
public interface PagerComponent {

    void inject(PagerFragment pagerFragment);

    final class Injector {

        public static void inject(PagerFragment pagerFragment) {

            DaggerPagerComponent.builder()
                    .sampleApplicationComponent(SampleApplicationComponent.Injector.component())
                    .pagerModule(new PagerModule(pagerFragment))
                    .build()
                    .inject(pagerFragment);
        }
    }

}
