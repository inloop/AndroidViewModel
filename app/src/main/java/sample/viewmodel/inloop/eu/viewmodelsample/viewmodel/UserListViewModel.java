package sample.viewmodel.inloop.eu.viewmodelsample.viewmodel;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import sample.viewmodel.inloop.eu.viewmodelsample.TaskStatus;
import sample.viewmodel.inloop.eu.viewmodelsample.viewmodel.iface.IUserListView;

public class UserListViewModel extends AbstractViewModel<IUserListView> {

    private List<String> mLoadedUsers;

    private TaskStatus mUserLoadState = TaskStatus.NOT_RUNNING;
    public UserListViewModel() {

    }

    @Override
    public void initWithView(@NonNull IUserListView view) {
        super.initWithView(view);

        if (mLoadedUsers != null) {
            view.setUsers(mLoadedUsers);
        } else if (mUserLoadState == TaskStatus.RUNNING) {
            view.showLoading();
        } else if (mUserLoadState == TaskStatus.NOT_RUNNING) {
            loadUsers();
        }
    }

    private void loadUsers() {
        mUserLoadState = TaskStatus.RUNNING;
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
                mUserLoadState = TaskStatus.FINISHED;
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
