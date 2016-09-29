package eu.inloop.viewmodel.sample.component;

import dagger.Component;
import eu.inloop.viewmodel.sample.activity.ViewPagerActivity;
import eu.inloop.viewmodel.sample.module.ViewPagerModule;


/**
 * Created by losonczitamas on 10/5/16.
 */
@Component(modules = ViewPagerModule.class)
public interface ViewPagerComponent {

    void inject(ViewPagerActivity viewPagerActivity);

    final class Injector {

        public static void inject(ViewPagerActivity viewPagerActivity) {

            DaggerViewPagerComponent.builder()
                    .viewPagerModule(new ViewPagerModule(viewPagerActivity))
                    .build()
                    .inject(viewPagerActivity);
        }
    }
}
