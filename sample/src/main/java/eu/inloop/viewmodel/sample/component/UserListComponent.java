package eu.inloop.viewmodel.sample.component;

import dagger.Component;
import eu.inloop.viewmodel.sample.PerActivity;
import eu.inloop.viewmodel.sample.fragment.UserListFragment;
import eu.inloop.viewmodel.sample.module.UserListModule;


/**
 * Created by losonczitamas on 10/5/16.
 */
@PerActivity
@Component(modules = UserListModule.class, dependencies = SampleApplicationComponent.class)
public interface UserListComponent {

    void inject(UserListFragment userListFragment);

    final class Injector {

        public static void inject(UserListFragment userListFragment) {

            DaggerUserListComponent.builder()
                    .sampleApplicationComponent(SampleApplicationComponent.Injector.component())
                    .userListModule(new UserListModule(userListFragment))
                    .build()
                    .inject(userListFragment);
        }
    }
}
