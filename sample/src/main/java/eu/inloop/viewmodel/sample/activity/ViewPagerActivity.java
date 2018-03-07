package eu.inloop.viewmodel.sample.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;

import butterknife.BindView;
import butterknife.ButterKnife;
import eu.inloop.viewmodel.base.ViewModelBaseEmptyActivity;
import eu.inloop.viewmodel.sample.R;
import eu.inloop.viewmodel.sample.fragment.PagerFragment;
import eu.inloop.viewmodel.support.ViewModelStatePagerAdapter;

public class ViewPagerActivity extends ViewModelBaseEmptyActivity {

    @BindView(R.id.pager)
    ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pager);
        ButterKnife.bind(this);
        mViewPager.setAdapter(new TestPagerAdapter(getSupportFragmentManager()));
    }

    private final static class TestPagerAdapter extends ViewModelStatePagerAdapter {
        TestPagerAdapter(FragmentManager fm) {
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
