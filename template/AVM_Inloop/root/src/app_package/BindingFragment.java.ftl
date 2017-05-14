package ${packageName}<#if scrPackage != "">.${scrPackage}</#if>;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
<#if vPackage != "">
	import ${packageName}<#if vPackage != "">.${vPackage?replace('/','.')}</#if>.I${viewModelClass?replace('ViewModel', 'View')};
<#else>
	import eu.inloop.viewmodel.IView;
</#if>
import eu.inloop.viewmodel.binding.ViewModelBaseBindingFragment;
import eu.inloop.viewmodel.binding.ViewModelBindingConfig;
import ${packageName}.databinding.${underscoreToCamelCase(layoutName)}Binding;
import ${packageName}.R;
import ${packageName}<#if vmPackage != "">.${vmPackage?replace('/','.')}</#if>.${viewModelClass};

<#if vPackage != "">
	public class ${screenClass} 
	extends ViewModelBase${screenType}<I${viewModelClass?replace('ViewModel', 'View')}, ${viewModelClass}, ${underscoreToCamelCase(layoutName)}Binding>
	implements I${viewModelClass?replace('ViewModel', 'View')} {
<#else>
	public class ${screenClass} extends ViewModelBase${screenType}<IView, ${viewModelClass}, ${underscoreToCamelCase(layoutName)}Binding> {
</#if>

	
	public static ${screenClass} newInstance() {
		final Bundle bundle = new Bundle();
		// set arguments
		final ${screenClass} fragment = new ${screenClass}();
		fragment.setArguments(bundle);
		return fragment;
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    	super.onViewCreated(view, savedInstanceState);
    	setModelView(this);
	}

	@Override
    public ViewModelBindingConfig getViewModelBindingConfig() {
        return new ViewModelBindingConfig(R.layout.${layoutName}, getActivity());
    }

}