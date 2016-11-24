package eu.inloop.viewmodel.sample.viewmodel;

import android.os.Bundle;
import android.support.annotation.Nullable;

import eu.inloop.viewmodel.AbstractViewModel;
import eu.inloop.viewmodel.sample.viewmodel.view.IPageView;

public class PageModel extends AbstractViewModel<IPageView> {

    @Override
    public void onCreate(@Nullable Bundle arguments, @Nullable Bundle savedInstanceState) {
        super.onCreate(arguments, savedInstanceState);
    }
}
