package com.tamaslosonczi.viewmodel.sample.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.squareup.leakcanary.RefWatcher;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import dagger.Lazy;
import com.tamaslosonczi.viewmodel.base.ViewModelBaseFragment;
import com.tamaslosonczi.viewmodel.sample.R;
import com.tamaslosonczi.viewmodel.sample.viewmodel.PageModel;
import com.tamaslosonczi.viewmodel.sample.viewmodel.view.IPageView;

import static com.tamaslosonczi.viewmodel.sample.component.PagerComponent.Injector.inject;


public class PagerFragment extends ViewModelBaseFragment<IPageView, PageModel> implements IPageView {

    private static final String ARGS_POSITION = "position";

    @InjectView(R.id.text)
    TextView mTextView;

    @Inject
    RefWatcher mRefWatcher;

    @Inject
    Lazy<PageModel> mPageModelInjector;

    public static PagerFragment newInstance(int position) {
        final Bundle bundle = new Bundle();
        bundle.putInt(ARGS_POSITION, position);
        final PagerFragment fragment = new PagerFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        inject(this);
        super.onCreate(savedInstanceState);
        setModelView(this);
    }

    @NonNull
    @Override
    public PageModel createViewModel() {
        return mPageModelInjector.get();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pager, container, false);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mTextView.setText(Integer.toString(getArguments().getInt(ARGS_POSITION)));
    }


    @Override
    public void onDestroy() {
        super.onDestroy();

        //watch for memory leaks
        mRefWatcher.watch(this);
    }
}
