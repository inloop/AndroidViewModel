package eu.inloop.androidviewviewmodel;

import android.util.Log;

import java.util.Date;

import eu.inloop.androidviewviewmodel.viewpresenter.Presenter;

public class TestViewPresenter implements Presenter<TestViewView> {

    private TestViewView mTestView;

    @Override
    public boolean hasPresenterView() {
        return mTestView != null;
    }

    @Override
    public TestViewView getPresenterView() {
        return mTestView;
    }

    @Override
    public void setPresenterView(TestViewView presenterView) {
        this.mTestView = presenterView;
    }

    @Override
    public void onCreate() {
        Log.v("PRS", "Created");
    }

    @Override
    public void onStart() {
        long timestamp = System.currentTimeMillis();
        Date date = new Date(timestamp);
        mTestView.setText(date.toString());

        Log.v("PRS", "Start");
    }

    @Override
    public void onStop() {
        Log.v("PRS", "Stop");
    }

    @Override
    public void onDestroy() {
        Log.v("PRS", "Destroy");
    }
}
