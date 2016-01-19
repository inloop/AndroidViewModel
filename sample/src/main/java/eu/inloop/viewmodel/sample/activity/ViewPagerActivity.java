package eu.inloop.viewmodel.sample.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

import eu.inloop.viewmodel.base.ViewModelBaseActivity;
import eu.inloop.viewmodel.sample.R;
import eu.inloop.viewmodel.sample.fragment.PagerFragment;

public class ViewPagerActivity extends ViewModelBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pager);

        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        viewPager.setAdapter(new TestPagerAdapter(getSupportFragmentManager()));
    }

    @Override
    public Class getViewModelClass() {
        return null;
    }

    private final static class TestPagerAdapter extends FragmentStatePagerAdapter {
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
