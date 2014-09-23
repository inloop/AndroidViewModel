package sample.viewmodel.inloop.eu.viewmodelsample.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import butterknife.ButterKnife;
import butterknife.OnClick;
import sample.viewmodel.inloop.eu.viewmodelsample.R;
import sample.viewmodel.inloop.eu.viewmodelsample.fragment.EmptyFragment;
import sample.viewmodel.inloop.eu.viewmodelsample.fragment.UserListFragment;


public class MainActivity extends ProjectBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.root_content, new UserListFragment(), "user-list-fragment").commit();
        }
    }

    @OnClick(R.id.button1)
    public void onOpenFragmentClicked() {
        getSupportFragmentManager().beginTransaction().replace(R.id.root_content, new EmptyFragment(), "empty-fragment").addToBackStack(null).commit();
    }

}
