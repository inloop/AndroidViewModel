package eu.inloop.viewmodel;

import android.support.annotation.NonNull;

import java.util.concurrent.ConcurrentHashMap;

public class ViewModelService {

    private static volatile ViewModelService sInstance;
    private final ConcurrentHashMap<String, AbstractViewModel<? extends IView>> mViewModelCache;

    @NonNull
    public static ViewModelService getInstance() {
        if (sInstance == null) {
            synchronized (ViewModelService.class) {
                if (sInstance == null) {
                    sInstance = new ViewModelService();
                }
            }
        }
        return sInstance;
    }

    private ViewModelService() {
        mViewModelCache = new ConcurrentHashMap<>();
    }

    public boolean remove(String key) {
        return mViewModelCache.remove(key) != null;
    }

    public static class ViewModelWrapper<T extends IView> {
        public final AbstractViewModel<T> viewModel;
        public final boolean wasCreated;

        private ViewModelWrapper(@NonNull AbstractViewModel<T> mViewModel, boolean mWasCreated) {
            this.viewModel = mViewModel;
            this.wasCreated = mWasCreated;
        }
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
            mViewModelCache.put(key, instance);
            return new ViewModelWrapper<>(instance, true);
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

}
