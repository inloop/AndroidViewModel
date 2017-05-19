package ${packageName}.ui.activity;

import eu.inloop.viewmodel.base.ViewModelBaseActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;

import ${packageName}.R;
import ${packageName}.viewmodel.view.${viewClass};
import ${packageName}.viewmodel.${modelClass};


public class ${screenClass} extends ViewModelBaseActivity<${viewClass}, ${modelClass}>
    implements ${viewClass}{

    @CheckResult
    @NonNull
    public static Intent createIntent(@NonNull final Context context){
        final Intent intent = new Intent(context, ${screenClass}.class);
        //add stuff
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.${layoutName});
        setModelView(this);
    }

    @Override
    public Class<${modelClass}> getViewModelClass() {
        return ${modelClass}.class;
    }
}