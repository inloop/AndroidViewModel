package sample.viewmodel.inloop.eu.viewmodelsample.viewmodel;

import java.util.concurrent.ConcurrentHashMap;

import sample.viewmodel.inloop.eu.viewmodelsample.viewmodel.iface.IView;

public class ViewModelService {

    private static ViewModelService sInstance;
    public ConcurrentHashMap<String, AbstractViewModel<?>> mViewModelCache = new ConcurrentHashMap<String, AbstractViewModel<?>>();

    public static synchronized ViewModelService getInstance() {
        if (sInstance == null) {
            sInstance = new ViewModelService();
        }
        return sInstance;
    }

    private ViewModelService() {

    }

    public boolean remove(String key) {
        return mViewModelCache.remove(key) != null;
    }

    public static class ViewModelWrapper<T extends AbstractViewModel<?>> {
        public T viewModel;
        public boolean wasCreated = true;

        private ViewModelWrapper(T mViewModel, boolean mWasCreated) {
            this.viewModel = mViewModel;
            this.wasCreated = mWasCreated;
        }
    }

    @SuppressWarnings("unchecked")
    public synchronized <T extends AbstractViewModel<? extends IView>> ViewModelWrapper<T> getViewModel(String key, Class<T> viewModelClass) {
        AbstractViewModel<?> instance =  mViewModelCache.get(key);
        if (instance != null) {
            return new ViewModelWrapper<T>((T) instance, false);
        }

        try {
            instance = viewModelClass.newInstance();
            instance.setUniqueScreenIdentifier(key);
            mViewModelCache.put(key, instance);
            return new ViewModelWrapper<T>((T) instance, true);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

}
