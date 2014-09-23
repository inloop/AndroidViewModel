package sample.viewmodel.inloop.eu.viewmodelsample.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;

import java.util.UUID;

import butterknife.ButterKnife;
import sample.viewmodel.inloop.eu.viewmodelsample.viewmodel.AbstractViewModel;
import sample.viewmodel.inloop.eu.viewmodelsample.viewmodel.ViewModelService;
import sample.viewmodel.inloop.eu.viewmodelsample.viewmodel.iface.IView;

public abstract class ProjectBaseFragment<T extends AbstractViewModel<? extends IView>> extends Fragment {

    private String mUniqueScreenIdentifier;
    private T mViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            mUniqueScreenIdentifier = UUID.randomUUID().toString();
        } else {
            mUniqueScreenIdentifier = savedInstanceState.getString("identifier");
        }
        ViewModelService.ViewModelWrapper<T> viewModelWrapper = ViewModelService.getInstance().getViewModel(getUniqueScreenIdentifier(), getViewModelClass());
        mViewModel = viewModelWrapper.viewModel;
        if (savedInstanceState != null && viewModelWrapper.wasCreated) {
            Log.e("model", "Application recreated by system - restoring viewmodel");
            mViewModel.restoreState(savedInstanceState);
        }

    }

    protected abstract Class<T> getViewModelClass();

    protected T getViewModel() {
        return mViewModel;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.inject(this, view);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("identifier", mUniqueScreenIdentifier);
        mViewModel.saveState(outState);
    }

    protected String getUniqueScreenIdentifier() {
        return mUniqueScreenIdentifier;
    }


    @Override
    public void onDestroyView() {
        getViewModel().destroyView(getActivity());
        ButterKnife.reset(this);
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
