package eu.inloop.viewmodel.sample.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

import javax.inject.Inject;
import javax.inject.Provider;

import butterknife.ButterKnife;
import butterknife.InjectView;
import eu.inloop.viewmodel.base.ViewModelBaseEmptyActivity;
import eu.inloop.viewmodel.sample.R;
import eu.inloop.viewmodel.sample.fragment.PagerFragment;

import static eu.inloop.viewmodel.sample.component.ViewPagerComponent.Injector.inject;


public class ViewPagerActivity extends ViewModelBaseEmptyActivity {

    @InjectView(R.id.pager)
    ViewPager mViewPager;

    @Inject
    Provider<TestPagerAdapter> mAdapterProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        inject(this);
        setContentView(R.layout.activity_pager);
        ButterKnife.inject(this);

        mViewPager.setAdapter(mAdapterProvider.get());
    }

    public final static class TestPagerAdapter extends FragmentStatePagerAdapter {

        public TestPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return PagerFragment.newInstance(position);
        }

        @Override
        public int getCount() {
            return 10;
        }
    }
}
