package eu.inloop.viewmodel.fixture.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import eu.inloop.viewmodel.base.ViewModelBaseFragment;
import eu.inloop.viewmodel.fixture.activity.VMTestActivity;

public class VMTestFragment extends ViewModelBaseFragment<IVMTestFragmentView, VMTestFragmentViewModel>
        implements IVMTestFragmentView {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return new LinearLayout(getContext());
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setModelView(this);
    }

    @Override
    public void onLoadData(boolean loaded) {
        requireActivity().setResult(VMTestActivity.RESULT_CODE_OK);
        requireActivity().finish();
    }
}
