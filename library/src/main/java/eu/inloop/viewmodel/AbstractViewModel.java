package eu.inloop.viewmodel;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.CallSuper;
import androidx.annotation.CheckResult;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.util.Log;
import android.view.View;

public abstract class AbstractViewModel<T extends IView> {

    @Nullable
    private String mUniqueIdentifier;

    @Nullable
    private T mView;

    @Nullable
    private final Class<?> mClassType;

    private boolean mBindViewWasCalled;

    public AbstractViewModel() {
        mClassType = ProxyViewHelper.getGenericType(getClass(), IView.class);
    }

    void setUniqueIdentifier(@NonNull final String uniqueIdentifier) {
        mUniqueIdentifier = uniqueIdentifier;
    }

    /**
     *
     * @return An app unique identifier for the current viewmodel instance (will be kept during orientation
     * change). This identifier will be reset in case the corresponding activity is killed.
     */
    @SuppressWarnings("unused")
    @Nullable
    public String getUniqueIdentifier() {
        return mUniqueIdentifier;
    }

    /**
     * Called when the ViewModel instance is created.
     * @param arguments initial ViewModel arguments passed from {@link Fragment#getArguments()}  or
     *                  {@link Activity#getIntent()}.{@link Intent#getExtras()}
     * @param savedInstanceState bundle with saved state, will be not null
     *               only in case the system is killed due to low memory
     *               and restored (and {@link #onSaveInstanceState(Bundle)} returned a non-null bundle.
     */
    @SuppressWarnings({"EmptyMethod", "UnusedParameters"})
    public void onCreate(@Nullable Bundle arguments, @Nullable Bundle savedInstanceState) {

    }

    /**
     * This method is an equivalent of {@link Fragment#onViewCreated(View, Bundle)} or {@link Activity#onCreate(Bundle)}.
     * At this point, the View is ready and you can initialise it.
     * @param view - View assigned to this ViewModel
     */
    @CallSuper
    public void onBindView(@NonNull T view) {
        mBindViewWasCalled = true;
        mView = view;
    }

    @CheckResult
    @Nullable
    public T getView() {
        return mView;
    }

    /**
     * Alternative to {@link #getView()}. This method will never return a null view - not even in case the current Fragment or
     * Activity is already destroyed or between orientation change. It will return a dummy
     * implementation in that case.
     * @return the View instance which implements {@link T}. It's never null.
     */
    @CheckResult
    @NonNull
    public T getViewOptional() {
        if (mView != null) {
            return mView;
        } else {
            if (mClassType == null) {
                throw new IllegalStateException("Your view must implement IView");
            }
            return ProxyViewHelper.init(mClassType);
        }
    }

    @CallSuper
    public void clearView() {
        mView = null;
    }

    @SuppressWarnings({"EmptyMethod", "UnusedParameters"})
    public void onSaveInstanceState(@NonNull final Bundle bundle) {

    }

    @SuppressWarnings({"EmptyMethod", "WeakerAccess"})
    public void onStop() {

    }

    @SuppressWarnings({"EmptyMethod", "WeakerAccess"})
    public void onStart() {
        if (mView == null && !mBindViewWasCalled) {
            Log.e("AndroidViewModel", this.getClass().getSimpleName() + " - no view associated. You probably did not call setModelView() in your Fragment or Activity");
        }
    }

    /**
     * Called when there parent fragment or view is already gone and destroyed.
     * This is a good place to empty any planned tasks that are useless without a UI.
     */
    @SuppressWarnings("EmptyMethod")
    public void onDestroy() {

    }
}
