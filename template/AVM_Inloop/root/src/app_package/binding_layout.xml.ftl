
<layout xmlns:android="http://schemas.android.com/apk/res/android"
		xmlns:tools="http://schemas.android.com/tools"
		xmlns:app="http://schemas.android.com/apk/res-auto"
    	tools:context="<#if scrPackage != "">.${scrPackage?replace('/','.')}</#if>.${screenClass}">

	<data>
		<variable
			name="viewModel"
			type="${packageName}.<#if vmPackage != "">${vmPackage?replace('/','.')}.</#if>${viewModelClass}"/>
	</data>

	<FrameLayout
		android:layout_width="match_parent"
		android:layout_height="match_parent">

	</FrameLayout>
	
</layout>