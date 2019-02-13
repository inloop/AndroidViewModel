package eu.inloop.viewmodel.sample.fragment;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;

import androidx.fragment.app.Fragment;
import eu.inloop.viewmodel.binding.ViewModelBaseBindingFragment;
import eu.inloop.viewmodel.binding.ViewModelBindingConfig;
import eu.inloop.viewmodel.sample.R;
import eu.inloop.viewmodel.sample.databinding.FragmentSampleBindingBinding;
import eu.inloop.viewmodel.sample.viewmodel.SampleBindingViewModel;
import eu.inloop.viewmodel.sample.viewmodel.view.ISampleBindingView;

/**
 * A simple {@link Fragment} subclass.
 */
public class SampleBindingFragment
        extends ViewModelBaseBindingFragment<ISampleBindingView, SampleBindingViewModel, FragmentSampleBindingBinding>
        implements ISampleBindingView {

    public SampleBindingFragment() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setModelView(this);
    }

    @Override
    public ViewModelBindingConfig getViewModelBindingConfig() {
        return new ViewModelBindingConfig(R.layout.fragment_sample_binding, requireActivity());
    }
}
