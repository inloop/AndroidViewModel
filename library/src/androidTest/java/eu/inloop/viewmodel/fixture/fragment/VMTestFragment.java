package eu.inloop.viewmodel.fixture.fragment;

import android.os.Bundle;
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
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return new LinearLayout(getContext());
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setModelView(this);
    }

    @Override
    public void onLoadData(boolean loaded) {
        getActivity().setResult(VMTestActivity.RESULT_CODE_OK);
        getActivity().finish();
    }
}
