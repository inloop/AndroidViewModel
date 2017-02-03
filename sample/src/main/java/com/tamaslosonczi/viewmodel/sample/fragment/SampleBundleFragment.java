package com.tamaslosonczi.viewmodel.sample.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import com.tamaslosonczi.viewmodel.AbstractViewModel;
import com.tamaslosonczi.viewmodel.IView;
import com.tamaslosonczi.viewmodel.IViewModelFactory;
import com.tamaslosonczi.viewmodel.base.ViewModelBaseFragment;
import com.tamaslosonczi.viewmodel.sample.R;
import com.tamaslosonczi.viewmodel.sample.viewmodel.SampleArgumentViewModel;


public class SampleBundleFragment extends ViewModelBaseFragment<IView, SampleArgumentViewModel> {

    public static final String ARG_INT_USER_ID = "ARG_INT_USER_ID";

    public static SampleBundleFragment newInstance(int userId) {
        final Bundle bundle = new Bundle();
        bundle.putInt(ARG_INT_USER_ID, userId);

        final SampleBundleFragment fragment = new SampleBundleFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_empty, container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.inject(this, view);
        setModelView(this);

        getViewModel().setUserId(getArguments().getInt(ARG_INT_USER_ID));
        if (savedInstanceState != null) {
            getViewModel().viewModelRestored();
        }
    }

    @NonNull
    @Override
    public SampleArgumentViewModel createViewModel() {
        return new SampleArgumentViewModel();
    }
}
