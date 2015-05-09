package eu.inloop.viewmodel.sample.viewmodel.view;

import java.util.List;

import eu.inloop.viewmodel.IView;

public interface IUserListView extends IView {

    void showLoading(float progress);
    void hideProgress();

    void showUsers(List<String> users);
}
