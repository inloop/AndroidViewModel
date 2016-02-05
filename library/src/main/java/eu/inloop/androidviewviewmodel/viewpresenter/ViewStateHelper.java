package eu.inloop.androidviewviewmodel.viewpresenter;

import android.os.Bundle;
import android.os.Parcelable;

import java.util.UUID;

public class ViewStateHelper {

    private static final String SUPER_STATE = "superState";
    private static final String PRESENTER_ID = "presenterId";

    public static Parcelable saveState(PresenterView presenterView, Parcelable superState) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(SUPER_STATE, superState);

        UUID presenterId = presenterView.getPresenterId();
        bundle.putSerializable(PRESENTER_ID, presenterId);

        return bundle;
    }

    public static Parcelable restoreState(PresenterView presenterView, Parcelable state) {
        Bundle bundle = (Bundle) state;

        UUID presenterId = (UUID) bundle.getSerializable(PRESENTER_ID);
        presenterView.setPresenterId(presenterId);

        return bundle.getParcelable(SUPER_STATE);
    }

}
