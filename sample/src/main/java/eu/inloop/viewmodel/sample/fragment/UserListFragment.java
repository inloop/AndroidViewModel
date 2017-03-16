package eu.inloop.viewmodel.sample.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.leakcanary.RefWatcher;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import eu.inloop.viewmodel.IViewModelFactory;
import eu.inloop.viewmodel.base.ViewModelBaseFragment;
import eu.inloop.viewmodel.binding.ViewModelBindingConfig;
import eu.inloop.viewmodel.sample.R;
import eu.inloop.viewmodel.sample.SampleApplication;
import eu.inloop.viewmodel.sample.activity.SampleBindingActivity;
import eu.inloop.viewmodel.sample.activity.ViewPagerActivity;
import eu.inloop.viewmodel.sample.viewmodel.UserListViewModel;
import eu.inloop.viewmodel.sample.viewmodel.view.IUserListView;

public class UserListFragment extends ViewModelBaseFragment<IUserListView, UserListViewModel> implements IUserListView {

    @InjectView(android.R.id.progress)
    View mProgressView;
    @InjectView(R.id.progress_text)
    TextView mProgressText;
    @InjectView(android.R.id.list)
    ListView mListview;
    @InjectView(R.id.open_binding_fragment)
    Button mOpenBindingFragment;

    private ArrayAdapter<String> mAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, android.R.id.text1, new ArrayList<String>());
    }

    @Nullable
    @Override
    public IViewModelFactory<IUserListView, UserListViewModel> getViewModelFactory() {
        return new  IViewModelFactory<IUserListView, UserListViewModel>() {
            @Nullable
            @Override
            public UserListViewModel createViewModel() {
                return new UserListViewModel();
            }
        };
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_userlist, container, false);
        ButterKnife.inject(this, view);

        final View headerView = inflater.inflate(R.layout.view_header_info, null, false);
        headerView.findViewById(R.id.button1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction().replace(R.id.root_content, SampleBundleFragment.newInstance(1234), "empty-fragment").addToBackStack(null).commit();
            }
        });
        headerView.findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().finish();
                getActivity().startActivity(getActivity().getIntent());
            }
        });
        headerView.findViewById(R.id.button3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), ViewPagerActivity.class));
            }
        });
        mListview.addHeaderView(headerView, null, false);
        mOpenBindingFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(SampleBindingActivity.newIntent(getActivity()));
            }
        });
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mListview.setAdapter(mAdapter);
        mListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                getViewModel().deleteUser(i - mListview.getHeaderViewsCount());
            }
        });
        setModelView(this);
    }

    @Override
    public void showUsers(List<String> users) {
        mAdapter.setNotifyOnChange(false);
        mAdapter.clear();
        mAdapter.addAll(users);
        mAdapter.setNotifyOnChange(true);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void showLoading(float progress) {
        mProgressView.setVisibility(View.VISIBLE);
        mProgressText.setText((int) (progress * 100) + "%");
    }

    @Override
    public void hideProgress() {
        mProgressView.setVisibility(View.GONE);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        // watch for memory leaks
        RefWatcher refWatcher = SampleApplication.getRefWatcher(getActivity());
        refWatcher.watch(this);
    }
}
