package eu.inloop.viewmodel.sample.viewmodel;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import eu.inloop.viewmodel.library.AbstractViewModel;
import eu.inloop.viewmodel.sample.viewmodel.view.IUserListView;

public class UserListViewModel extends AbstractViewModel<IUserListView> {

    private List<String> mLoadedUsers;

    //Don't persist state variables
    private boolean mLoadingUsers;

    @Override
    public void initWithView(@NonNull IUserListView view) {
        super.initWithView(view);

        if (mLoadedUsers != null) {
            view.setUsers(mLoadedUsers);
        } else if (mLoadingUsers) {
            view.showLoading();
        } else {
            loadUsers();
        }
    }

    private void loadUsers() {
        mLoadingUsers = true;
        if (getView() != null) {
            getView().showLoading();
        }
        new AsyncTask<Void, Void, List<String>>() {

            @Override
            protected List<String> doInBackground(Void... voids) {
                final List<String> list = new ArrayList<String>();
                list.add("User 1");
                list.add("User 2");
                list.add("User 3");
                list.add("User 3");
                list.add("User 4");
                list.add("User 5");

                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                return list;
            }

            @Override
            protected void onPostExecute(List<String> s) {
                super.onPostExecute(s);
                mLoadedUsers = s;
                mLoadingUsers = false;
                if (getView() != null) {
                    getView().setUsers(s);
                    getView().hideProgress();
                }
            }
        }.execute();
    }

    @Override
    public void restoreState(Bundle bundle) {
        super.restoreState(bundle);
        mLoadedUsers = bundle.getStringArrayList("userlist");
    }

    @Override
    public void saveState(Bundle bundle) {
        super.saveState(bundle);
        if (mLoadedUsers != null) {
            bundle.putStringArrayList("userlist", new ArrayList<String>(mLoadedUsers));
        }
    }
}
