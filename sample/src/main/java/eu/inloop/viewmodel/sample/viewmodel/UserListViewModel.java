package eu.inloop.viewmodel.sample.viewmodel;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import eu.inloop.viewmodel.AbstractViewModel;
import eu.inloop.viewmodel.sample.viewmodel.view.IUserListView;

public class UserListViewModel extends AbstractViewModel<IUserListView> {

    private List<String> mLoadedUsers;

    //Don't persist state variables
    private boolean mLoadingUsers;

    private int numberOfRunningDeletes = 0;

    @Override
    public void initWithView(@NonNull IUserListView view) {
        super.initWithView(view);

        //downloading list of users
        if (mLoadedUsers != null) {
            view.setUsers(mLoadedUsers);
        } else if (mLoadingUsers) {
            view.showLoading();
        } else {
            loadUsers();
        }

        refreshLoading();
    }

    private void refreshLoading() {
        //loading user
        if (numberOfRunningDeletes > 0 && getView() != null) {
            getView().showIntermediateProgress(true);
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

    public void deleteUser(final int position) {
        if (position > mLoadedUsers.size() - 1) {
            return;
        }
        mLoadedUsers.set(position, "Deleting");
        if (getView() != null) {
            getView().showIntermediateProgress(true);
            getView().setUsers(mLoadedUsers);
        }
        numberOfRunningDeletes++;
        new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void... voids) {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (mLoadedUsers.size() > position) {
                    mLoadedUsers.remove(position);
                } else {
                    mLoadedUsers.remove(0);
                }
                numberOfRunningDeletes--;
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                if (getView() != null) {
                    getView().setUsers(mLoadedUsers);
                    if (numberOfRunningDeletes == 0) {
                        getView().showIntermediateProgress(false);
                    }
                }
            }
        }.execute();
    }

    @Override
    public void onStart() {
        refreshLoading();
    }

    @Override
    public void onStop() {
        getView().showIntermediateProgress(false);
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

    @Override
    public void onModelRemoved() {
        super.onModelRemoved();
        //use this to cancel any planned requests
    }
}
