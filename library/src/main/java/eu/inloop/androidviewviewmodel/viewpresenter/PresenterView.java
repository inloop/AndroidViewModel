package eu.inloop.androidviewviewmodel.viewpresenter;

import android.support.annotation.Nullable;

import java.util.UUID;

public interface PresenterView<T extends Presenter> {

    boolean hasPresenterId();
    @Nullable
    UUID getPresenterId();
    void setPresenterId(UUID presenterId);

    T createPresenter();
    void setPresenter(T presenter);
    T getPresenter();

}
