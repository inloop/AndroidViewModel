package com.tamaslosonczi.viewmodel.sample.activity;

import android.os.Bundle;

import butterknife.ButterKnife;
import com.tamaslosonczi.viewmodel.base.ViewModelBaseEmptyActivity;
import com.tamaslosonczi.viewmodel.sample.R;
import com.tamaslosonczi.viewmodel.sample.fragment.UserListFragment;


public class MainActivity extends ViewModelBaseEmptyActivity {

    private static final String TAG_USER_LIST_FRAGMENT = "user-list-fragment";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);

        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.root_content, new UserListFragment(), TAG_USER_LIST_FRAGMENT)
                    .commit();
        }
    }

}
