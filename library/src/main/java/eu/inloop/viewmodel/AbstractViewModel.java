package eu.inloop.viewmodel;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import eu.inloop.viewmodel.IView;

public abstract class AbstractViewModel<T extends IView> {

    private String mUniqueIdentifier;

    @Nullable
    private T mView;

    void setUniqueIdentifier(String uniqueIdentifier) {
        mUniqueIdentifier = uniqueIdentifier;
    }

    /**
     *
     * @return An app unique identifier for the current viewmodel instance (will be kept during orientation
     * change). This identifier will be reset in case the corresponding activity is killed.
     */
    public String getUniqueIdentifier() {
        return mUniqueIdentifier;
    }

    /**
     * Called when the ViewModel instance is created.
     * @param arguments initial ViewModel arguments passed from {@link Fragment#getArguments()}  or
     *                  {@link Activity#getIntent()}.{@link Intent#getExtras()}
     * @param savedInstanceState bundle with saved state, will be not null
     *               only in case the system is killed due to low memory
     *               and restored (and {@link #saveState(Bundle)} returned a non-null bundle.
     */
    @SuppressWarnings("EmptyMethod")
    public void onCreate(@Nullable Bundle arguments, @Nullable Bundle savedInstanceState) {

    }

    public void bindView(@NonNull T view) {
        mView = view;
    }

    public T getView() {
        return mView;
    }

    public void clearView() {
        mView = null;
    }

    @SuppressWarnings("EmptyMethod")
    public void saveState(Bundle bundle) {

    }

    @SuppressWarnings("EmptyMethod")
    public void onStop() {

    }

    @SuppressWarnings("EmptyMethod")
    public void onStart() {

    }

    /**
     * Called when there parent fragment or view is already gone and destroyed.
     * This is a good place to empty any planned tasks that are useless without a UI.
     */
    @SuppressWarnings("EmptyMethod")
    public void onModelRemoved() {

    }
}
