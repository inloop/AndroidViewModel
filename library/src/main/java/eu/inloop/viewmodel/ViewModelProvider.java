package eu.inloop.viewmodel;
import android.support.annotation.NonNull;

import java.util.HashMap;

/**
 * Create and keep this class inside your Activity. Store it
 * in {@link android.support.v4.app.FragmentActivity#onRetainCustomNonConfigurationInstance()
 * and restore in {@link android.support.v4.app.FragmentActivity#onCreate(android.os.Bundle)} before
 * calling the super implemenentation.
 */
public class ViewModelProvider {

    private static final ViewModelProvider sInstance = new ViewModelProvider();

    private final HashMap<String, AbstractViewModel<? extends IView>> mViewModelCache;

    private ViewModelProvider() {
        mViewModelCache = new HashMap<>();
    }

    public static ViewModelProvider getInstance() {
        return sInstance;
    }

    public boolean remove(@NonNull String key) {
        return mViewModelCache.remove(key) != null;
    }

    public static class ViewModelWrapper<T extends IView> {
        @NonNull
        public final AbstractViewModel<T> viewModel;
        public final boolean wasCreated;

        private ViewModelWrapper(@NonNull AbstractViewModel<T> mViewModel, boolean mWasCreated) {
            this.viewModel = mViewModel;
            this.wasCreated = mWasCreated;
        }
    }

    /**
     * Call this in {@link android.app.Activity#onStop()} if {@link android.app.Activity#isFinishing()}
     */
    public void removeAllViewModels() {
        mViewModelCache.clear();
    }

    @SuppressWarnings("unchecked")
    @NonNull
    public synchronized <T extends IView> ViewModelWrapper<T> getViewModel(@NonNull String key, @NonNull Class<? extends AbstractViewModel<T>> viewModelClass) {
        AbstractViewModel<T> instance = (AbstractViewModel<T>) mViewModelCache.get(key);
        if (instance != null) {
            return new ViewModelWrapper<>(instance, false);
        }

        try {
            instance = viewModelClass.newInstance();
            instance.setUniqueIdentifier(key);
            mViewModelCache.put(key, instance);
            return new ViewModelWrapper<>(instance, true);
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

}
