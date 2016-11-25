package com.tamaslosonczi.viewmodel.sample.component;

import dagger.Component;
import com.tamaslosonczi.viewmodel.sample.PerActivity;
import com.tamaslosonczi.viewmodel.sample.fragment.UserListFragment;
import com.tamaslosonczi.viewmodel.sample.module.UserListModule;


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
