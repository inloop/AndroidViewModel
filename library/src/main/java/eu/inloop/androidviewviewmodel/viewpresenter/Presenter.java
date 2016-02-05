package eu.inloop.androidviewviewmodel.viewpresenter;

public interface Presenter<V extends PresenterView> {

    boolean hasPresenterView();
    V getPresenterView();
    void setPresenterView(V presenterView);

    void onCreate();
    void onStart();
    void onStop();
    void onDestroy();
}
