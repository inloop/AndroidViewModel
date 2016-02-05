package eu.inloop.androidviewviewmodel;

import android.content.Context;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.TextView;

import java.util.UUID;

import eu.inloop.androidviewviewmodel.TestViewPresenter;
import eu.inloop.androidviewviewmodel.TestViewView;
import eu.inloop.androidviewviewmodel.viewpresenter.ViewStateHelper;

public class TestView extends TextView implements TestViewView {

    private UUID presenterId;
    private TestViewPresenter presenter;

    public TestView(Context context) {
        super(context);
    }

    public TestView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TestView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public boolean hasPresenterId() {
        return presenterId != null;
    }

    @Nullable
    @Override
    public UUID getPresenterId() {
        return presenterId;
    }

    @Override
    public void setPresenterId(UUID presenterId) {
        this.presenterId = presenterId;
    }

    @Override
    public TestViewPresenter createPresenter() {
        return new TestViewPresenter();
    }

    @Override
    public void setPresenter(TestViewPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public TestViewPresenter getPresenter() {
        return presenter;
    }

    @Override
    public Parcelable onSaveInstanceState() {
        Parcelable state = super.onSaveInstanceState();
        return ViewStateHelper.saveState(this, state);
    }

    @Override
    public void onRestoreInstanceState(Parcelable state) {
        Parcelable superState = ViewStateHelper.restoreState(this, state);
        super.onRestoreInstanceState(superState);
    }

    @Override
    public void setText(String text) {
        super.setText(text);
    }
}
