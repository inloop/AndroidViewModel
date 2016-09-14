## 1.0.1(2016-9-14)
  
  - Updated dependencies (gradle, build tools, support library). 

## 1.0.0(2016-05-02)

  - We decided it's time for 1.0.0 release after a lot of use in production projects.
  - Made getViewModel() as NonNull to prevent a lot of unncessary null checks. It will now throw an IllegalStateException in case it's null (should not happen under normal conditions. Only if you call it too soon - before Activity.onCreate or Fragment.onCreate).
  - <b>Breaking change</b>: AbstractViewModel method saveState was renamed to onSaveInstanceState, bindView to onBindView and onModelRemoved to onDestroy. You may need to update your Models if you are overriding those methods.
  
## 0.4.1(2016-01-22)

  - Added ViewModelBaseEmptyActivity - which you can extend in case you don't need a ViewModel in your activity (but your fragments have ViewModels).
  - Added a sanity check if setModelView() was called - an error will be logged in case you forget to call it.
  
## 0.4.0(2016-01-19)

  - The ViewModel instances are now kept within the Activity (onRetainCustomNonConfigurationInstance). All ViewModels in the activity (including fragment ViewModels) will be cleared if you leave the activity.
  - If you are not extending ViewModelBaseActivity and instead you are using your own implementation, then you need to update your base Activity. The ViewModelBaseActivity.onCreate() changed and the activity now implements IViewModelProver interface.
  
## 0.3.2(2015-05-18)

Breaking changes:

  - Add setModelView() method which has to be called in every Fragment/Activity after the view was initialized. This is usually on the end of onCreate / onViewCreated - [commit](https://github.com/inloop/AndroidViewModel/commit/54a7d1a96d38d1a17c8bc7c81b081d52064bde28)

## 0.3.1(2015-05-14)

Bugfixes:

  - Fix ViewModel ID clashes after the application was restored due to low memory - [commit](https://github.com/inloop/AndroidViewModel/commit/cecfd54d3008c07c19ad7685b97e9fe2acb5c369)
