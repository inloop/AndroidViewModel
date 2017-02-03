package eu.inloop.viewmodel.sample.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.leakcanary.RefWatcher;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Provider;

import butterknife.ButterKnife;
import butterknife.InjectView;
import dagger.Lazy;
import eu.inloop.viewmodel.base.ViewModelBaseFragment;
import eu.inloop.viewmodel.sample.R;
import eu.inloop.viewmodel.sample.activity.ViewPagerActivity;
import eu.inloop.viewmodel.sample.viewmodel.UserListViewModel;
import eu.inloop.viewmodel.sample.viewmodel.view.IUserListView;
import eu.inloop.viewmodel.sample.widget.HeaderInfoView;

import static eu.inloop.viewmodel.sample.component.UserListComponent.Injector.inject;


public class UserListFragment extends ViewModelBaseFragment<IUserListView, UserListViewModel> implements IUserListView {

    private static final String TAG_EMPTY_FRAGMENT = "empty-fragment";

    @InjectView(android.R.id.progress)
    View mProgressView;
    @InjectView(R.id.progress_text)
    TextView mProgressText;
    @InjectView(android.R.id.list)
    ListView mListView;

    @Inject
    RefWatcher mRefWatcher;
    @Inject
    Provider<ArrayAdapter<String>> mArrayAdapterProvider;
    @Inject
    Lazy<UserListViewModel> mUserListViewModelInjector;

    private ArrayAdapter<String> mAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        inject(this);
        super.onCreate(savedInstanceState);
    }

    @NonNull
    @Override
    public UserListViewModel createViewModel() {
        return  mUserListViewModelInjector.get();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_userlist, container, false);
        ButterKnife.inject(this, view);
        final HeaderInfoView headerView = new HeaderInfoView(getActivity());
        headerView.setCallback(this);
        mListView.addHeaderView(headerView, null, false);
        return view;
    }

    @Override
    public void onOpenNextFragmentButtonClicked() {
        getFragmentManager().beginTransaction()
                .replace(R.id.root_content, SampleBundleFragment.newInstance(1234), TAG_EMPTY_FRAGMENT)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onRestartActivityButtonClicked() {
        getActivity().finish();
        getActivity().startActivity(getActivity().getIntent());
    }

    @Override
    public void onOpenFragmentPagerButtonClicked() {
        startActivity(new Intent(getContext(), ViewPagerActivity.class));
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mAdapter = mArrayAdapterProvider.get();
        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                getViewModel().deleteUser(i - mListView.getHeaderViewsCount());
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
        mRefWatcher.watch(this);
    }
}
