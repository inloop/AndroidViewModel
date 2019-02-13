package eu.inloop.viewmodel.support;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import android.util.Log;
import android.view.ViewGroup;

import eu.inloop.viewmodel.BuildConfig;
import eu.inloop.viewmodel.IView;

/**
 * This class extends {@link FragmentStatePagerAdapter}. It removes the ViewModel once the
 * pager item is destroyed ({@link #destroyItem(ViewGroup, int, Object)}). The ViewModel state
 * is stored and then restored once you return back to this pager item and {@link #instantiateItem(ViewGroup, int)}
 * is called.
 */
public abstract class ViewModelStatePagerAdapter extends FragmentStatePagerAdapter {

    public ViewModelStatePagerAdapter(@NonNull final FragmentManager fm) {
        super(fm);
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        super.destroyItem(container, position, object);
        final Fragment fragment = (Fragment) object;

        if (fragment instanceof IView) {
            IView viewModelBaseFragment = (IView) fragment;
            viewModelBaseFragment.removeViewModel();
        } else {
            if (BuildConfig.DEBUG) {
                Log.w("model", "Fragment " + fragment + " in FragmentStatePagerAdapter " + this + " doesn't implent IView"); //NON-NLS
            }
        }
    }
}
