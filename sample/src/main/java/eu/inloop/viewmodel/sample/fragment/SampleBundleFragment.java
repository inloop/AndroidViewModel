package eu.inloop.viewmodel.sample.fragment;

import android.os.Bundle;
import androidx.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import eu.inloop.viewmodel.IView;
import eu.inloop.viewmodel.base.ViewModelBaseFragment;
import eu.inloop.viewmodel.sample.R;
import eu.inloop.viewmodel.sample.viewmodel.SampleArgumentViewModel;


public class SampleBundleFragment extends ViewModelBaseFragment<IView, SampleArgumentViewModel> {

    public static SampleBundleFragment newInstance(int userId) {
        final Bundle bundle = new Bundle();
        bundle.putInt(SampleArgumentViewModel.ARG_INT_USER_ID, userId);

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
        ButterKnife.bind(this, view);
        setModelView(this);
    }
}
