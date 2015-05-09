package eu.inloop.viewmodel;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import eu.inloop.viewmodel.IView;

public abstract class AbstractViewModel<T extends IView> {

    private int mUniqueIdentifier;

    @Nullable
    private T mView;

    void setUniqueIdentifier(int uniqueIdentifier) {
        mUniqueIdentifier = uniqueIdentifier;
    }

    /**
     *
     * @return An app unique identifier for the current viewmodel instance (will be kept during orientation
     * change). This identifier will be reset in case the corresponding activity is killed.
     */
    public int getUniqueIdentifier() {
        return mUniqueIdentifier;
    }

    public void initWithView(@NonNull T view) {
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

    /**
     * Will be called only in case the system is killed due to low memory and restored
     * @param bundle - bundle with saved state
     */
    @SuppressWarnings("EmptyMethod")
    public void restoreState(Bundle bundle) {

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
