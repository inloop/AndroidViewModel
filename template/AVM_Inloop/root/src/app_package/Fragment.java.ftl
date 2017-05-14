package ${packageName}<#if scrPackage != "">.${scrPackage}</#if>;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
<#if vPackage != "">
	import ${packageName}<#if vPackage != "">.${vPackage?replace('/','.')}</#if>.I${viewModelClass?replace('ViewModel', 'View')};
<#else>
	import eu.inloop.viewmodel.IView;
</#if>
import eu.inloop.viewmodel.base.ViewModelBaseFragment;
import ${packageName}.R;
import ${packageName}<#if vmPackage != "">.${vmPackage?replace('/','.')}</#if>.${viewModelClass};

<#if vPackage != "">
	public class ${screenClass} 
	extends ViewModelBase${screenType}<I${viewModelClass?replace('ViewModel', 'View')}, ${viewModelClass}> 
	implements I${viewModelClass?replace('ViewModel', 'View')} {
<#else>
	public class ${screenClass} extends ViewModelBase${screenType}<IView, ${viewModelClass}> {
</#if>
	
	public static ${screenClass} newInstance() {
		final Bundle bundle = new Bundle();
		// set arguments
		final ${screenClass} fragment = new ${screenClass}();
		fragment.setArguments(bundle);
		return fragment;
	}

	@Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.${layoutName}, container, false);
    }

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    	super.onViewCreated(view, savedInstanceState);
    	
    	setModelView(this);
	}

}