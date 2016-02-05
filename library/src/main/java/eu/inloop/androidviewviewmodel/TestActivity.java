package eu.inloop.androidviewviewmodel;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import eu.inloop.androidviewviewmodel.viewpresenter.PresenterView;
import eu.inloop.androidviewviewmodel.viewpresenter.ViewLifecycleManager;
import eu.inloop.viewmodel.R;

public class TestActivity extends AppCompatActivity {

    private ViewLifecycleManager viewViewModelManager = new ViewLifecycleManager();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        PresenterView view1 = (PresenterView) findViewById(R.id.test_view);

        viewViewModelManager.onPresentedViewCreated(view1);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        viewViewModelManager.onPresenterViewActivated();
    }

    @Override
    protected void onPause() {
        super.onPause();
        viewViewModelManager.onPresenterViewDeactivated();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        boolean permanent = isFinishing();
        viewViewModelManager.onDestroy(permanent);
    }

}
