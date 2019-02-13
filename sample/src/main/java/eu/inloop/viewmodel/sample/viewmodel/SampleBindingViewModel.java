package eu.inloop.viewmodel.sample.viewmodel;

import androidx.databinding.ObservableField;
import android.os.Bundle;
import androidx.annotation.Nullable;

import eu.inloop.viewmodel.AbstractViewModel;
import eu.inloop.viewmodel.sample.viewmodel.view.ISampleBindingView;

/**
 * Created by stepansanda on 21/11/2016.
 */

public class SampleBindingViewModel extends AbstractViewModel<ISampleBindingView> {

    public final ObservableField<String> text = new ObservableField<>();
    private int mButtonClickedCounter = 0;

    @Override
    public void onCreate(@Nullable Bundle arguments, @Nullable Bundle savedInstanceState) {
        super.onCreate(arguments, savedInstanceState);
        setButtonClickedText();
    }

    public void onButtonClick() {
        mButtonClickedCounter++;
        setButtonClickedText();
    }

    private void setButtonClickedText() {
        text.set("Button Clicked: " + mButtonClickedCounter + " times");
    }
}
