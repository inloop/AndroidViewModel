package eu.inloop.viewmodel.sample.loader;

import java.util.List;


/**
 * Created by losonczitamas on 10/10/16.
 */
public interface UserLoader {

    interface Callback {

        void showUsers();

        void showUsers(List<String> users);

        void hideProgress();

        void showLoading(float progress);

        void removeItem(String item);

        void setLoadedUsers(List<String> users);
    }

    void deleteUser(String itemToDelete);

    void loadUser();

    boolean isLoading();

    void setCallback(Callback callback);

    float getProgress();
}
