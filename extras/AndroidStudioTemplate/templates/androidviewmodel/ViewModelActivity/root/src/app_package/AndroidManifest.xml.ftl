<manifest xmlns:android="http://schemas.android.com/apk/res/android" >

    <application>
            <activity android:name="${relativePackage}.ui.activity.${screenClass}"
                <#if isNewProject>
                    android:label="@string/app_name"
                <#else>
                    android:label="@string/title_${classToResource(screenClass)}"
                </#if>
                />
    </application>
</manifest>
