package eu.inloop.viewmodel.sample.activity;

import android.os.Bundle;
import android.view.Window;

import butterknife.ButterKnife;
import butterknife.OnClick;
import eu.inloop.viewmodel.sample.R;
import eu.inloop.viewmodel.sample.fragment.EmptyFragment;
import eu.inloop.viewmodel.sample.fragment.UserListFragment;


public class MainActivity extends ProjectBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.root_content, new UserListFragment(), "user-list-fragment").commit();
        }
    }

    @Override
    public Class getViewModelClass() {
        return null;
    }

    @OnClick(R.id.button1)
    public void onOpenFragmentClicked() {
        getSupportFragmentManager().beginTransaction().replace(R.id.root_content, new EmptyFragment(), "empty-fragment").addToBackStack(null).commit();
    }

    @OnClick(R.id.button2)
    public void closeClicked() {
        finish();
    }
}
