package eu.inloop.viewmodel.sample.viewmodel.view;

import eu.inloop.viewmodel.IView;
import eu.inloop.viewmodel.binding.ViewModelBindingConfig;

public interface IPageView extends IView {
    ViewModelBindingConfig getViewModelBindingConfig();
}
