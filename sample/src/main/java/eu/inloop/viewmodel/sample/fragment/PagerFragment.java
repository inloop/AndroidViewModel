package eu.inloop.viewmodel.sample.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.squareup.leakcanary.RefWatcher;

import eu.inloop.viewmodel.AbstractViewModel;
import eu.inloop.viewmodel.IViewModelFactory;
import eu.inloop.viewmodel.base.ViewModelBaseFragment;
import eu.inloop.viewmodel.sample.R;
import eu.inloop.viewmodel.sample.SampleApplication;
import eu.inloop.viewmodel.sample.viewmodel.PageModel;
import eu.inloop.viewmodel.sample.viewmodel.view.IPageView;

public class PagerFragment extends ViewModelBaseFragment<IPageView, PageModel> {

    public static PagerFragment newInstance(int position) {
        final Bundle bundle = new Bundle();
        bundle.putInt("position", position);
        final PagerFragment fragment = new PagerFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_pager, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((TextView)view.findViewById(R.id.text)).setText(Integer.toString(getArguments().getInt("position")));
    }

    @Nullable
    @Override
    public IViewModelFactory<IPageView> getViewModelFactory() {
        return new IViewModelFactory<IPageView>() {
            @NonNull
            @Override
            public AbstractViewModel<IPageView> createViewModel() {
                return new PageModel();
            }
        };
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        // watch for memory leaks
        RefWatcher refWatcher = SampleApplication.getRefWatcher(getActivity());
        refWatcher.watch(this);
    }
}
