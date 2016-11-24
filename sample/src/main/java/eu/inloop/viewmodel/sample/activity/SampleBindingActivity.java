package eu.inloop.viewmodel.sample.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import butterknife.ButterKnife;
import eu.inloop.viewmodel.base.ViewModelBaseEmptyActivity;
import eu.inloop.viewmodel.sample.R;
import eu.inloop.viewmodel.sample.fragment.SampleBindingFragment;

public class SampleBindingActivity extends ViewModelBaseEmptyActivity {

    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, SampleBindingActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.root_content, new SampleBindingFragment(), "sample-binding-fragment").commit();
        }
    }
}
