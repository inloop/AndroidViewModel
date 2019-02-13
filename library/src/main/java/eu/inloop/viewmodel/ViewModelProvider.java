package eu.inloop.viewmodel;
import android.app.Activity;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.fragment.app.FragmentActivity;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Create and keep this class inside your Activity. Store it
 * in {@link androidx.core.app.FragmentActivity#onRetainCustomNonConfigurationInstance()
 * and restore in {@link androidx.core.app.FragmentActivity#onCreate(android.os.Bundle)} before
 * calling the super implemenentation.
 */
public class ViewModelProvider {

    @NonNull
    private final HashMap<String, AbstractViewModel<? extends IView>> mViewModelCache;

    @NonNull
    public static ViewModelProvider newInstance(@NonNull final FragmentActivity activity) {
        if (activity.getLastCustomNonConfigurationInstance() == null) {
            return new ViewModelProvider();
        } else {
            return (ViewModelProvider) activity.getLastCustomNonConfigurationInstance();
        }
    }

    @SuppressWarnings({"deprecation", "unused"})
    @NonNull
    @Deprecated
    public static ViewModelProvider newInstance(@NonNull final Activity activity) {
        if (activity.getLastNonConfigurationInstance() == null) {
            return new ViewModelProvider();
        } else {
            return (ViewModelProvider) activity.getLastNonConfigurationInstance();
        }
    }

    private ViewModelProvider() {
        mViewModelCache = new HashMap<>();
    }

    public synchronized void remove(@Nullable String modeIdentifier) {
        mViewModelCache.remove(modeIdentifier);
    }

    public synchronized void removeAllViewModels() {
        mViewModelCache.clear();
    }

    @VisibleForTesting
    @NonNull
    Map<String, AbstractViewModel<? extends IView>> getViewModels() {
        return Collections.unmodifiableMap(mViewModelCache);
    }

    @SuppressWarnings("unchecked")
    @NonNull
    public synchronized <T extends IView> ViewModelWrapper<T> getViewModel(@NonNull final String modelIdentifier,
                                                                           @NonNull final Class<? extends AbstractViewModel<T>> viewModelClass) {
        AbstractViewModel<T> instance = (AbstractViewModel<T>) mViewModelCache.get(modelIdentifier);
        if (instance != null) {
            return new ViewModelWrapper<>(instance, false);
        }

        try {
            instance = viewModelClass.newInstance();
        } catch (final Exception ex) {
            throw new RuntimeException(ex);
        }
        instance.setUniqueIdentifier(modelIdentifier);
        mViewModelCache.put(modelIdentifier, instance);
        return new ViewModelWrapper<>(instance, true);
    }

    final static class ViewModelWrapper<T extends IView> {
        @NonNull
        final AbstractViewModel<T> viewModel;
        final boolean wasCreated;

        private ViewModelWrapper(@NonNull AbstractViewModel<T> mViewModel, final boolean mWasCreated) {
            this.viewModel = mViewModel;
            this.wasCreated = mWasCreated;
        }
    }
}
