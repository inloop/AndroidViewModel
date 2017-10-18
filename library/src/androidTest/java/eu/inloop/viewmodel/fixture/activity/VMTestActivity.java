package eu.inloop.viewmodel.fixture.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.widget.LinearLayout;

import eu.inloop.viewmodel.base.ViewModelBaseActivity;
import eu.inloop.viewmodel.fixture.fragment.VMTestFragment;

public class VMTestActivity extends ViewModelBaseActivity<IVMTestActivityView, VMTestActivityViewModel> implements IVMTestActivityView {

    public static final int RESULT_CODE_OK = 1;
    public static final String EXTRA_CALL_ON_BIND = "EXTRA_CALL_ON_BIND";

    @NonNull
    public static Intent makeIntent(@NonNull Context context, boolean callOnBindModel) {
        Intent intent = new Intent(context, VMTestActivity.class);
        intent.putExtra(EXTRA_CALL_ON_BIND, callOnBindModel);

        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout view = new LinearLayout(this);
        view.setId(android.R.id.content);
        setContentView(view);

        if (savedInstanceState == null) {
            addTestFragment();
        }

        setModelView(this);
    }

    public void addTestFragment() {
        getSupportFragmentManager()
                .beginTransaction()
                .add(android.R.id.content, new VMTestFragment())
                .commitNow();
    }

    public void removeTestFragment() {
        getSupportFragmentManager()
                .beginTransaction()
                .remove(getTestFragment())
                .commitNow();
    }

    @NonNull
    public VMTestFragment getTestFragment() {
        for (Fragment fragment : getSupportFragmentManager().getFragments()) {
            if (fragment instanceof VMTestFragment) {
                return (VMTestFragment) fragment;
            }
        }
        throw new AssertionError("Fragment not found");
    }

    @Override
    public void onLoadData(boolean loaded) {
        setResult(RESULT_CODE_OK);
        finish();
    }

}
