package eu.inloop.viewmodel;


import android.content.pm.ActivityInfo;
import androidx.test.InstrumentationRegistry;
import androidx.test.filters.MediumTest;
import androidx.test.filters.SmallTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Map;

import eu.inloop.viewmodel.fixture.activity.VMTestActivity;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;


@RunWith(AndroidJUnit4.class)
public final class ViewModelActivityTest {

    @Rule
    public final ActivityTestRule<VMTestActivity> mActivityTestRule =
            new ActivityTestRule<>(VMTestActivity.class, false, false);

    @SmallTest
    @Test
    public void viewModelActivity_onBindView_test() {
        mActivityTestRule.launchActivity(VMTestActivity.makeIntent(InstrumentationRegistry.getContext(), true));

        assertThat(mActivityTestRule.getActivityResult().getResultCode(), is(VMTestActivity.RESULT_CODE_OK));
    }

    @SmallTest
    @Test
    public void viewModelActivity_getViewModel_getView_test() {
        mActivityTestRule.launchActivity(VMTestActivity.makeIntent(InstrumentationRegistry.getContext(), false));
        mActivityTestRule.getActivity().getViewModel().loadData();

        assertThat(mActivityTestRule.getActivityResult().getResultCode(), is(VMTestActivity.RESULT_CODE_OK));
    }

    @SmallTest
    @Test
    public void viewModelActivity_getViewModel_getViewOptional_test() {
        mActivityTestRule.launchActivity(VMTestActivity.makeIntent(InstrumentationRegistry.getContext(), false));
        mActivityTestRule.getActivity().getViewModel().loadDataOptional();

        assertThat(mActivityTestRule.getActivityResult().getResultCode(), is(VMTestActivity.RESULT_CODE_OK));
    }

    @SmallTest
    @Test
    public void viewModelActivity_clearView_test() {
        mActivityTestRule.launchActivity(VMTestActivity.makeIntent(InstrumentationRegistry.getContext(), false));
        mActivityTestRule.getActivity().getViewModel().clearView();

        assertThat(mActivityTestRule.getActivity().getViewModel().getView(), is(nullValue()));
    }

    @SmallTest
    @Test
    public void viewModelActivity_uniqueIdentifier_test() {
        mActivityTestRule.launchActivity(VMTestActivity.makeIntent(InstrumentationRegistry.getContext(), false));
        String uniqueIdentifier = mActivityTestRule.getActivity().getViewModel().getUniqueIdentifier();

        assertThat(uniqueIdentifier, is(notNullValue()));
    }

    @SmallTest
    @Test
    public void viewModelActivity_fragment_getView_test() {
        mActivityTestRule.launchActivity(VMTestActivity.makeIntent(InstrumentationRegistry.getContext(), false));

        mActivityTestRule.getActivity().getTestFragment().getViewModel().loadData();

        assertThat(mActivityTestRule.getActivityResult().getResultCode(), is(VMTestActivity.RESULT_CODE_OK));
    }

    @SmallTest
    @Test
    public void viewModelActivity_fragment_remove_test() {
        mActivityTestRule.launchActivity(VMTestActivity.makeIntent(InstrumentationRegistry.getContext(), false));

        String uniqueIdentifierActivity = mActivityTestRule.getActivity().getViewModel().getUniqueIdentifier();
        String uniqueIdentifierFragment = mActivityTestRule.getActivity().getTestFragment().getViewModel().getUniqueIdentifier();

        Map<String, AbstractViewModel<? extends IView>> viewModels =
                mActivityTestRule.getActivity().getViewModelProvider().getViewModels();

        assertThat(viewModels.containsKey(uniqueIdentifierActivity), is(true));
        assertThat(viewModels.containsKey(uniqueIdentifierFragment), is(true));

        InstrumentationRegistry.getInstrumentation().runOnMainSync(new Runnable() {
            @Override
            public void run() {
                mActivityTestRule.getActivity().removeTestFragment();
            }
        });

        //Check If ViewModel is removed after removing fragment
        viewModels = mActivityTestRule.getActivity().getViewModelProvider().getViewModels();

        assertThat(viewModels.containsKey(uniqueIdentifierActivity), is(true));
        assertThat(viewModels.containsKey(uniqueIdentifierFragment), is(false));
    }

    @SmallTest
    @Test
    public void viewModelActivity_fragment_model_state_test() {
        mActivityTestRule.launchActivity(VMTestActivity.makeIntent(InstrumentationRegistry.getContext(), false));

        final int stateValue = 1;
        mActivityTestRule.getActivity().getTestFragment().getViewModel().setStateValue(stateValue);

        rotateScreen(1);

        int actualStateValue = mActivityTestRule.getActivity().getTestFragment().getViewModel().getStateValue();

        assertThat(stateValue, is(actualStateValue));
    }

    @MediumTest
    @Test
    public void viewModelActivity_instance_count_test() {
        mActivityTestRule.launchActivity(VMTestActivity.makeIntent(InstrumentationRegistry.getContext(), false));

        String uniqueIdentifierActivity = mActivityTestRule.getActivity().getViewModel().getUniqueIdentifier();
        String uniqueIdentifierFragment = mActivityTestRule.getActivity().getTestFragment().getViewModel().getUniqueIdentifier();

        rotateScreen(5);

        Map<String, AbstractViewModel<? extends IView>> viewModels =
                mActivityTestRule.getActivity().getViewModelProvider().getViewModels();

        assertThat(viewModels.size(), is(2)); //activity + fragment

        assertThat(viewModels.containsKey(uniqueIdentifierActivity), is(true));
        assertThat(viewModels.containsKey(uniqueIdentifierFragment), is(true));
    }

    private void rotateScreen(int numOfTimes) {
        for (int i = 0; i < numOfTimes; i++) {
            mActivityTestRule.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
            InstrumentationRegistry.getInstrumentation().waitForIdleSync();
            mActivityTestRule.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            InstrumentationRegistry.getInstrumentation().waitForIdleSync();
        }
    }

}
