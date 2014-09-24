package eu.inloop.viewmodel.sample.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import eu.inloop.viewmodel.library.AbstractViewModel;
import sample.viewmodel.inloop.eu.viewmodelsample.R;
import eu.inloop.viewmodel.sample.viewmodel.UserListViewModel;
import eu.inloop.viewmodel.sample.viewmodel.view.IUserListView;

public class UserListFragment extends ProjectBaseFragment<IUserListView, UserListViewModel> implements IUserListView {

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
    public Class<UserListViewModel> getViewModelClass() {
        return UserListViewModel.class;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_userlist, container, false);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mListview.setAdapter(mAdapter);
        mListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                getViewModel().deleteUser(i);
            }
        });
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
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
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
