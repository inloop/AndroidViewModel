package eu.inloop.viewmodel.sample.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import butterknife.ButterKnife;
import butterknife.OnClick;
import eu.inloop.viewmodel.sample.R;


/**
 * Created by losonczitamas on 10/9/16.
 */
public class HeaderInfoView extends LinearLayout {

    public interface Callback {

        void onOpenNextFragmentButtonClicked();

        void onRestartActivityButtonClicked();

        void onOpenFragmentPagerButtonClicked();
    }


    private Callback mCallback;


    public HeaderInfoView(Context context) {
        this(context, null);
    }


    public HeaderInfoView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }


    public HeaderInfoView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inflate(context, R.layout.view_header_info, this);
        ButterKnife.inject(this);
        init();
    }


    public void setCallback(Callback callback) {
        mCallback = callback;
    }


    private void init() {
        setOrientation(VERTICAL);
        int padding = getResources().getDimensionPixelSize(R.dimen.header_view_padding);
        setPadding(padding, padding, padding, padding);
        setBackgroundColor(getResources().getColor(R.color.header_view_background));
    }


    @OnClick({R.id.button1, R.id.button2, R.id.button3})
    public void onOpenNextFragmentButtonClicked(View view) {
        if (mCallback == null) return;

        switch (view.getId()) {
            case R.id.button1:
                mCallback.onOpenNextFragmentButtonClicked();
                break;
            case R.id.button2:
                mCallback.onRestartActivityButtonClicked();
                break;
            case R.id.button3:
                mCallback.onOpenFragmentPagerButtonClicked();
                break;
            default:
                break;
        }
    }
}
