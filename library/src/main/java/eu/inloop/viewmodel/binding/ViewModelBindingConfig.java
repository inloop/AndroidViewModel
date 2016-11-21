package eu.inloop.viewmodel.binding;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;

import eu.inloop.viewmodel.BR;

/**
 * Use this to define a ViewModelBinding Config for a specific screen.
 * <p>
 * Config contains layout resource ID, Context, ViewModel binding variable name
 */
public class ViewModelBindingConfig {

    @LayoutRes
    private final int mLayoutResource;
    private final int mViewModelVariableName;
    @NonNull
    private final Context mContext;

    /**
     * Create a ViewModelBinding Config object for an Activity/Fragment
     * This constructor should be used if the binding variable is named differently
     *
     * @param layoutResource        Layout resource ID
     * @param viewModelVariableName Data Binding variable name for injecting the ViewModel - use
     *                              generated id (e.g. BR.mViewModel)
     */
    public ViewModelBindingConfig(@LayoutRes int layoutResource, int viewModelVariableName, @NonNull Context context) {
        mLayoutResource = layoutResource;
        mViewModelVariableName = viewModelVariableName;
        mContext = context;
    }

    /**
     * Create a ViewModelBinding Config object for an Activity/Fragment
     * Use this constructor if the binding variable is named viewModel
     *
     * @param layoutResource Layout resource ID
     */
    public ViewModelBindingConfig(@LayoutRes int layoutResource, @NonNull Context context) {
        this(layoutResource, BR.viewModel, context);
    }

    @LayoutRes
    public int getLayoutResource() {
        return mLayoutResource;
    }

    public int getViewModelVariableName() {
        return mViewModelVariableName;
    }

    @NonNull
    public Context getContext() {
        return mContext;
    }
}
