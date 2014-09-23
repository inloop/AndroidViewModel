package sample.viewmodel.inloop.eu.viewmodelsample.viewmodel;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import sample.viewmodel.inloop.eu.viewmodelsample.viewmodel.iface.IView;

public abstract class AbstractViewModel<T extends IView> {

    @Nullable
    private T mView;
    private String mUniqueScreenIdentifier;

    public void initWithView(@NonNull T view) {
        mView = view;
    }

    public void setUniqueScreenIdentifier(String identifier) {
        this.mUniqueScreenIdentifier = identifier;
    }

    public String getUniqueScreenIdentifier() {
        return mUniqueScreenIdentifier;
    }

    public void destroyView(Activity activity) {
        mView = null;
        if (activity != null && activity.isFinishing()) {
            boolean removed = ViewModelService.getInstance().remove(mUniqueScreenIdentifier);
            if (removed) {
                Log.d("mode", "Removing view model");
            }
        }
    }

    public T getView() {
        return mView;
    }

    public void saveState(Bundle bundle) {

    }

    public void restoreState(Bundle bundle) {

    }
}
