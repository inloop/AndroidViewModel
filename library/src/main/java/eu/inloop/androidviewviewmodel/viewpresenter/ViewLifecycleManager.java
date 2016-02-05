package eu.inloop.androidviewviewmodel.viewpresenter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ViewLifecycleManager {

    private static PresenterProvider sPresenterProvider = PresenterProvider.getInstance();

    private List<PresenterView> presentersViewsList = new ArrayList<>();

    public ViewLifecycleManager onPresentedViewCreated(PresenterView presenterView) {
        presentersViewsList.add(presenterView);
        return this;
    }

    public void onPresenterViewActivated() {
        for (PresenterView presenterView : presentersViewsList) {
            if (!presenterView.hasPresenterId()) {
                createPresenterForView(presenterView);
            }

            UUID id = presenterView.getPresenterId();
            Presenter presenter = sPresenterProvider.retrievePresenter(id);

            presenterView.setPresenter(presenter);
            presenter.setPresenterView(presenterView);

            presenter.onStart();
        }
    }

    private void createPresenterForView(PresenterView presenterView) {
        UUID id = generatePresenterId();
        presenterView.setPresenterId(id);
        Presenter presenter = presenterView.createPresenter();
        sPresenterProvider.storePresenter(id, presenter);

        presenter.onCreate();
    }

    private UUID generatePresenterId() {
        UUID id;
        do {
            id = UUID.randomUUID();
        } while (sPresenterProvider.usesId(id));

        return id;
    }

    public void onPresenterViewDeactivated() {
        for (PresenterView presenterView : presentersViewsList) {
            Presenter presenter = presenterView.getPresenter();
            presenter.onStop();
        }
    }

    public <T extends Presenter> void onPresenterViewDestroyed(PresenterView<T> presenterView) {
        UUID presenterId = presenterView.getPresenterId();
        Presenter presenter = sPresenterProvider.retrievePresenter(presenterId);
        presenter.setPresenterView(null);
    }

    public void onDestroy(boolean permanent) {
        if (permanent) {
            for (PresenterView presenterView : presentersViewsList) {
                UUID presenterId = presenterView.getPresenterId();
                Presenter presenter = sPresenterProvider.removePresenter(presenterId);
                presenter.onDestroy();
            }
        }

        presentersViewsList.clear();
    }

}
