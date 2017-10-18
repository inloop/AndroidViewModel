package eu.inloop.viewmodel.fixture.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import eu.inloop.viewmodel.AbstractViewModel;

public class VMTestActivityViewModel extends AbstractViewModel<IVMTestActivityView> {

    private boolean mCallOnBind;

    @Override
    public void onCreate(@Nullable Bundle arguments, @Nullable Bundle savedInstanceState) {
        super.onCreate(arguments, savedInstanceState);
        if (arguments == null) {
            throw new AssertionError("Arguments must be set for this ViewModel");
        }
        mCallOnBind = arguments.getBoolean(VMTestActivity.EXTRA_CALL_ON_BIND);
    }

    @Override
    public void onBindView(@NonNull IVMTestActivityView view) {
        super.onBindView(view);

        if (mCallOnBind) {
            loadData();
        }
    }

    public void loadData() {
        getView().onLoadData(true);
    }

    public void loadDataOptional() {
        getViewOptional().onLoadData(true);
    }

}
