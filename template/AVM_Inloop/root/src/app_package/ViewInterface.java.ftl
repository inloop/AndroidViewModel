
package ${packageName}<#if vmPackage != "">.${vPackage?replace('/','.')}</#if>;

import eu.inloop.viewmodel.IView;

public interface I${viewModelClass?replace('ViewModel', 'View')} extends IView {

}