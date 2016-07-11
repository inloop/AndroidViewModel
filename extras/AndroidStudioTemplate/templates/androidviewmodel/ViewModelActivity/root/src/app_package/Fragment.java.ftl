package ${packageName}.ui.fragment;

import android.os.Bundle;
import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ${packageName}.R;
import eu.inloop.viewmodel.base.ViewModelBaseFragment;
import ${packageName}.viewmodel.view.${viewClass};
import ${packageName}.viewmodel.${modelClass};

public class ${screenClass} extends ViewModelBaseFragment<${viewClass}, ${modelClass}> {

    @CheckResult
	@NonNull
    public static ${screenClass} newInstance() {
        final Bundle bundle = new Bundle();
        //add stuff to bundle
        final ${screenClass} fragment = new ${screenClass}();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.${layoutName}, container, false);
    }

    @Override
    public Class<${modelClass}> getViewModelClass() {
        return ${modelClass}.class;
    }

}
