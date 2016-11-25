package com.tamaslosonczi.viewmodel.sample.viewmodel;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import com.tamaslosonczi.viewmodel.AbstractViewModel;
import com.tamaslosonczi.viewmodel.sample.loader.UserLoader;
import com.tamaslosonczi.viewmodel.sample.viewmodel.view.IUserListView;


public class UserListViewModel extends AbstractViewModel<IUserListView> implements UserLoader.Callback {

    private static final String KEY_USER_LIST = "userlist";

    private List<String> mLoadedUsers;

    private final UserLoader mUserLoader;


    @Inject
    public UserListViewModel(UserLoader userLoader) {
        mUserLoader = userLoader;
    }


    @Override
    public void onCreate(@Nullable Bundle arguments, @Nullable Bundle savedInstanceState) {
        super.onCreate(arguments, savedInstanceState);
        //this will be only not null in case the application was killed due to low memory
        if (savedInstanceState != null) {
            mLoadedUsers = savedInstanceState.getStringArrayList(KEY_USER_LIST);
        }
    }


    @Override
    public void onBindView(@NonNull IUserListView view) {
        super.onBindView(view);
        mUserLoader.setCallback(this);
        //downloading list of users
        if (mLoadedUsers != null) {
            showUsers(mLoadedUsers);
        } else if (mUserLoader.isLoading()) {
            showLoading(mUserLoader.getProgress());
        } else {
            loadUser();
        }
    }


    private void loadUser() {
        showLoading(mUserLoader.getProgress());
        mUserLoader.loadUser();
    }


    @Override
    public void setLoadedUsers(List<String> users) {
        mLoadedUsers = users;
    }


    @Override
    public void removeItem(String item) {
        mLoadedUsers.remove(item);
    }


    @Override
    public void showUsers() {
        if (getView() != null) {
            getView().showUsers(mLoadedUsers);
        }
    }


    @Override
    public void showUsers(List<String> users) {
        if (getView() != null) {
            getView().showUsers(users);
        }
    }


    @Override
    public void hideProgress() {
        if (getView() != null) {
            getView().hideProgress();
        }
    }


    @Override
    public void showLoading(float progress) {
        if (getView() != null) {
            getView().showLoading(progress);
        }
    }


    public void deleteUser(final int position) {
        if (position > mLoadedUsers.size() - 1) {
            return;
        }
        mLoadedUsers.set(position, "Deleting in 5 seconds...");
        showUsers(mLoadedUsers);

        final String itemToDelete = mLoadedUsers.get(position);
        mUserLoader.deleteUser(itemToDelete);
    }


    @Override
    public void onSaveInstanceState(@NonNull final Bundle bundle) {
        super.onSaveInstanceState(bundle);
        if (mLoadedUsers != null) {
            bundle.putStringArrayList(KEY_USER_LIST, new ArrayList<>(mLoadedUsers));
        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        //use this to cancel any planned requests
    }
}
