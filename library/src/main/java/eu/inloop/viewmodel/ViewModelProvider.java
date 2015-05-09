package eu.inloop.viewmodel;
import android.support.annotation.NonNull;
import android.util.SparseArray;

/**
 * Create and keep this class inside your Activity. Store it
 * in {@link android.support.v4.app.FragmentActivity#onRetainCustomNonConfigurationInstance()
 * and restore in {@link android.support.v4.app.FragmentActivity#onCreate(android.os.Bundle)} before
 * calling the super implemenentation.
 */
public class ViewModelProvider {

    private static final ViewModelProvider sInstance = new ViewModelProvider();

    private final SparseArray<AbstractViewModel<? extends IView>> mViewModelCache;

    private ViewModelProvider() {
        mViewModelCache = new SparseArray<>();
    }

    public static ViewModelProvider getInstance() {
        return sInstance;
    }

    public synchronized void remove(int modelIndex) {
        mViewModelCache.remove(modelIndex);
    }

    @SuppressWarnings("unchecked")
    @NonNull
    public synchronized <T extends IView> ViewModelWrapper<T> getViewModel(int modelIndex, @NonNull Class<? extends AbstractViewModel<T>> viewModelClass) {
        AbstractViewModel<T> instance = (AbstractViewModel<T>) mViewModelCache.get(modelIndex);
        if (instance != null) {
            return new ViewModelWrapper<>(instance, false);
        }

        try {
            instance = viewModelClass.newInstance();
            instance.setUniqueIdentifier(modelIndex);
            mViewModelCache.put(modelIndex, instance);
            return new ViewModelWrapper<>(instance, true);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
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
}
