package eu.inloop.viewmodel.fixture.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import eu.inloop.viewmodel.AbstractViewModel;

public class VMTestFragmentViewModel extends AbstractViewModel<IVMTestFragmentView> {

    private static final String STATE_INT = "STATE_INT";

    private int mStateValue;

    @Override
    public void onCreate(@Nullable Bundle arguments, @Nullable Bundle savedInstanceState) {
        super.onCreate(arguments, savedInstanceState);

        if (savedInstanceState != null) {
            mStateValue = savedInstanceState.getInt(STATE_INT);
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putInt(STATE_INT, mStateValue);
    }

    public void setStateValue(int value) {
        mStateValue = value;
    }

    public int getStateValue() {
        return mStateValue;
    }

    public void loadData() {
        getView().onLoadData(true);
    }

}
