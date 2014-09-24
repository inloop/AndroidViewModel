package eu.inloop.viewmodel.sample.viewmodel.view;

import java.util.List;

import eu.inloop.viewmodel.library.IView;

public interface IUserListView extends IView {

    public void setUsers(List<String> users);
    public void showIntermediateProgress(boolean show);
}
