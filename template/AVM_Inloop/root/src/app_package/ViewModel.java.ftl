
package ${packageName}<#if vmPackage != "">.${vmPackage}</#if>;

import android.os.Bundle;
import android.support.annotation.Nullable;
import eu.inloop.viewmodel.AbstractViewModel;
<#if vPackage != "">
	import ${packageName}<#if vPackage != "">.${vPackage?replace('/','.')}</#if>.I${viewModelClass?replace('ViewModel', 'View')};
<#else>	
	import eu.inloop.viewmodel.IView;
</#if>

<#if vPackage != "">
	public class ${viewModelClass} extends AbstractViewModel<I${viewModelClass?replace('ViewModel', 'View')}> {
<#else>
	public class ${viewModelClass} extends AbstractViewModel<IView> {
</#if>

	@Override
    public void onCreate(@Nullable Bundle arguments, @Nullable Bundle savedInstanceState) {
        super.onCreate(arguments, savedInstanceState);
        
    }
}