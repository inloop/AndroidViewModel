package eu.inloop.viewmodel.library;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;

import java.util.UUID;

public class ViewModelHelper<T extends IView, R extends AbstractViewModel<T>> {

    private String mScreenId;
    private R mViewModel;

    public void onCreate(Bundle savedInstanceState, Class<? extends AbstractViewModel<T>> viewModelClass) {
        if (savedInstanceState == null) {
            mScreenId = UUID.randomUUID().toString();
        } else {
            mScreenId = savedInstanceState.getString("identifier");
        }

        final ViewModelService.ViewModelWrapper<T> viewModelWrapper = ViewModelService.getInstance().getViewModel(mScreenId, viewModelClass);
        mViewModel = (R) viewModelWrapper.viewModel;
        if (savedInstanceState != null && viewModelWrapper.wasCreated) {
            Log.e("model", "Application recreated by system - restoring viewmodel");
            mViewModel.restoreState(savedInstanceState);
        }
    }

    public void initWithView(T view) {
        mViewModel.initWithView(view);
    }

    public void onDestroyView(Fragment fragment) {
        getViewModel().clearView();
        if (fragment.getActivity() != null && fragment.getActivity().isFinishing()) {
            removeViewModel();
        }
    }

    public void onDestroy(Fragment fragment) {
        if (fragment.getActivity() != null && fragment.getActivity().isFinishing()) {
            removeViewModel();
        } else if (fragment.isRemoving()) {
            Log.d("mode", "Removing viewmodel - fragment replaced");
            removeViewModel();
        }
    }

    public void onStop() {
        getViewModel().onStop();
    }

    public void onStart() {
        getViewModel().onStart();
    }

    public R getViewModel() {
        return mViewModel;
    }

    public void onSaveInstanceState(Bundle bundle) {
        bundle.putString("identifier", mScreenId);
        mViewModel.saveState(bundle);
    }

    private boolean removeViewModel() {
        return ViewModelService.getInstance().remove(mScreenId);
    }
}
