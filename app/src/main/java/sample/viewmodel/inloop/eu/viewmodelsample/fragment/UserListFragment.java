package sample.viewmodel.inloop.eu.viewmodelsample.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import sample.viewmodel.inloop.eu.viewmodelsample.R;
import sample.viewmodel.inloop.eu.viewmodelsample.viewmodel.AbstractViewModel;
import sample.viewmodel.inloop.eu.viewmodelsample.viewmodel.UserListViewModel;
import sample.viewmodel.inloop.eu.viewmodelsample.viewmodel.iface.IUserListView;
import sample.viewmodel.inloop.eu.viewmodelsample.viewmodel.iface.IView;

public class UserListFragment extends ProjectBaseFragment implements IUserListView {

    @InjectView(android.R.id.progress)
    View mProgressView;
    @InjectView(android.R.id.list)
    ListView mListview;

    private ArrayAdapter<String> mAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, android.R.id.text1, new ArrayList<String>());
    }

    @Override
    protected Class<? extends AbstractViewModel<? extends IView>> getViewModelClass() {
        return UserListViewModel.class;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_userlist, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.inject(this, view);
        getViewModel().initWithView(this);
        mListview.setAdapter(mAdapter);
    }

    @Override
    public void setUsers(List<String> users) {
        mAdapter.setNotifyOnChange(false);
        mAdapter.clear();
        mAdapter.addAll(users);
        mAdapter.setNotifyOnChange(true);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void showLoading() {
        mProgressView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        mProgressView.setVisibility(View.GONE);
    }
}
