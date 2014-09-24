AndroidViewModel
================

Separating data and state handling from Fragments or Activities without lots of boilerplate-code. Reducing them to simple <i>dumb views</i>.

<b>Basic idea behind this library</b>

An instance of a Model class is assigned to your Fragment or Activity during the first creation and is kept during it's life cycle, even between display orientation changes. The Model instance is removed after the Fragment or Activity is completely gone (finished, poppped from backstack, replaced without keeping it in backstack).

You can execute asynchronous tasks in this Model instance and this class is not destroyed during orientation change. All data handling and state logic should be placed inside this class. The Fragment or Activity is just a "dumb" view.

<b>How does it work?</b> </p>
A unique global ID is generated for the first time your Fragment or Activity is shown. This ID is passed on during orientation changes. Openning another instance of the same Fragment or Activity will result in a different ID. The ID is unique screen identifier. A ViewModel class is created and bound to this ID. The corresponding ViewModel instance is attached to your Fragment or Activity after an orientation change or if you return to the fragment in the back stack.
The ViewModel is discarded once the Fragment/Activity is not reachaeable anymore (activity is finished or fragment permanently removed).

<b>Sample Workflow</b>:
1. Fragment is show to user. A ViewModel is assigned.
2. Fragment notifies the View that it's ready. 
3. ViewModel starts the async task to load data. Tells the view to show progress.
4. User rotates the display. The ViewModel continues with the loading part.
5. The Fragment is recreated after the orientation change is assigned the same ViewModel instance.
6. Recreated Fragment tells the ViewModel that it's ready. ViewModel tells the UI to show loading, because it's still loading the data.
7. ViewModel finishes the async task and tells the Fragment to show the data.
8. User leaves the Activity, the Fragment is destroyed and the ViewModel is removed.


<b>STATUS:</b> Under heavy development, don't use in real projects yet.
