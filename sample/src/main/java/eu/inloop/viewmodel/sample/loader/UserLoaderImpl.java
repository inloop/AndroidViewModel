package eu.inloop.viewmodel.sample.loader;

import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;


/**
 * Created by losonczitamas on 10/10/16.
 */
public class UserLoaderImpl implements UserLoader {

    private static final int TOTAL_USERS = 7;

    private Callback mCallback;

    private float mCurrentLoadingProgress;

    private boolean mLoadingUsers;

    @Inject
    public UserLoaderImpl() {

    }

    @Override
    public void deleteUser(final String itemToDelete) {

        new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void... voids) {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    //
                }
                mCallback.removeItem(itemToDelete);
                return null;
            }


            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                mCallback.showUsers();
            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    @Override
    public void loadUser() {
        mLoadingUsers = true;
        mCurrentLoadingProgress = 0;
        mCallback.showLoading(mCurrentLoadingProgress);
        new AsyncTask<Void, Float, List<String>>() {

            @Override
            protected List<String> doInBackground(Void... voids) {
                while (!isCancelled()) {

                    final List<String> list = new ArrayList<>();
                    for (int i = 0; i < TOTAL_USERS; i++) {
                        list.add("User " + i);

                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {

                        }
                        publishProgress((i + 1) / (float) TOTAL_USERS);
                    }

                    return list;
                }

                return Collections.EMPTY_LIST;
            }


            @Override
            protected void onProgressUpdate(Float... values) {
                super.onProgressUpdate(values);
                mCurrentLoadingProgress = values[0];
                mCallback.showLoading(mCurrentLoadingProgress);
            }

            @Override
            protected void onPostExecute(List<String> s) {
                super.onPostExecute(s);
                mLoadingUsers = false;
                mCallback.setLoadedUsers(s);
                mCallback.showUsers();
                mCallback.hideProgress();
            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

    }

    @Override
    public boolean isLoading() {
        return mLoadingUsers;
    }

    @Override
    public void setCallback(Callback callback) {
        this.mCallback = callback;
    }

    @Override
    public float getProgress() {
        return mCurrentLoadingProgress;
    }
}
