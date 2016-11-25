package com.tamaslosonczi.viewmodel.sample.viewmodel.view;

import java.util.List;

import com.tamaslosonczi.viewmodel.IView;
import com.tamaslosonczi.viewmodel.sample.widget.HeaderInfoView;


public interface IUserListView extends IView, HeaderInfoView.Callback {

    void showLoading(float progress);

    void hideProgress();

    void showUsers(List<String> users);
}
