package eu.inloop.viewmodel.sample.module;

import android.widget.ArrayAdapter;

import java.util.ArrayList;

import dagger.Module;
import dagger.Provides;
import eu.inloop.viewmodel.sample.fragment.UserListFragment;
import eu.inloop.viewmodel.sample.loader.UserLoader;
import eu.inloop.viewmodel.sample.loader.UserLoaderImpl;


/**
 * Created by losonczitamas on 10/5/16.
 */
@Module
public class UserListModule {

    private final UserListFragment mUserListFragment;

    public UserListModule(UserListFragment userListFragment) {
        mUserListFragment = userListFragment;
    }

    @Provides
    ArrayAdapter<String> provideArrayAdapter() {
        return new ArrayAdapter(mUserListFragment.getActivity(), android.R.layout.simple_list_item_1, android.R.id.text1, new ArrayList<String>());
    }

    @Provides
    UserLoader provideUserLoader(UserLoaderImpl impl) {
        return impl;
    }
}
