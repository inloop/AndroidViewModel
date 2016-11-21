package eu.inloop.viewmodel.binding;

import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.jetbrains.annotations.NotNull;

import eu.inloop.viewmodel.AbstractViewModel;
import eu.inloop.viewmodel.IView;
import eu.inloop.viewmodel.base.ViewModelBaseFragment;

public abstract class ViewModelBaseBindingFragment<T extends IView, R extends AbstractViewModel<T>, B extends ViewDataBinding>
        extends ViewModelBaseFragment<T, R>
        implements IView {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getViewModeHelper().performBinding(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getViewModeHelper().performBinding(this);
        final ViewDataBinding binding = getViewModeHelper().getBinding();
        if (binding != null) {
            return binding.getRoot();
        } else {
            throw new IllegalStateException("Binding cannot be null. Perform binding before calling getBinding()");
        }
    }

    @SuppressWarnings("unused")
    @NotNull
    public B getBinding() {
        try {
            return (B) getViewModeHelper().getBinding();
        } catch (ClassCastException ex) {
            throw new IllegalStateException("Method getViewModelBindingConfig() has to return same " +
                    "ViewDataBinding type as it is set to base Fragment");
        }
    }
}
