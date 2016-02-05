package eu.inloop.androidviewviewmodel;

import eu.inloop.androidviewviewmodel.viewpresenter.PresenterView;

public interface TestViewView extends PresenterView<TestViewPresenter> {

    void setText(String text);

}
