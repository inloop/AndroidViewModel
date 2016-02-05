package eu.inloop.androidviewviewmodel.viewpresenter;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PresenterProvider {

    private static final PresenterProvider INSTANCE = new PresenterProvider();

    public static PresenterProvider getInstance() {
        return INSTANCE;
    }

    private final Map<UUID, Presenter> presentersMap = new HashMap<>();

    public boolean usesId(UUID id) {
        return presentersMap.containsKey(id);
    }

    public void storePresenter(UUID id, Presenter presenter) {
        presentersMap.put(id, presenter);
    }

    public <T extends Presenter> T retrievePresenter(UUID id) {
        return (T)presentersMap.get(id);
    }

    public <T extends Presenter> T removePresenter(UUID id) {
        return (T)presentersMap.remove(id);
    }

}
