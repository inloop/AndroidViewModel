package eu.inloop.viewmodel.sample.viewmodel.view;

import java.util.List;

import eu.inloop.viewmodel.IView;
import eu.inloop.viewmodel.sample.widget.HeaderInfoView;


public interface IUserListView extends IView, HeaderInfoView.Callback {

    void showLoading(float progress);

    void hideProgress();

    void showUsers(List<String> users);
}
