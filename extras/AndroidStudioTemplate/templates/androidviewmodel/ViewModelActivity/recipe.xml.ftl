<?xml version="1.0"?>
<recipe>

<#if appCompat && !(hasDependency('eu.inloop:androidviewmodel:1.0.0'))>
    <dependency mavenUrl="eu.inloop:androidviewmodel:1.0.0"/>
</#if>

<#if screenType == "Activity">
    <merge from="root/src/app_package/AndroidManifest.xml.ftl"
           to="${escapeXmlAttribute(manifestOut)}/AndroidManifest.xml"/>
    <merge from="root/res/values/strings.xml.ftl"
           to="${escapeXmlAttribute(resOut)}/values/strings.xml"/>
    <instantiate from="root/src/app_package/Activity.java.ftl"
                 to="${escapeXmlAttribute(srcOut)}/ui/activity/${screenClass}.java"/>
<#else>
    <instantiate from="root/src/app_package/Fragment.java.ftl"
                 to="${escapeXmlAttribute(srcOut)}/ui/fragment/${screenClass}.java"/>
</#if>

    <instantiate from="root/res/layout/layout.xml.ftl"
                 to="${escapeXmlAttribute(resOut)}/layout/${layoutName}.xml"/>

    <instantiate from="root/src/app_package/Model.java.ftl"
                 to="${escapeXmlAttribute(srcOut)}/viewmodel/${modelClass}.java"/>

    <instantiate from="root/src/app_package/View.java.ftl"
                 to="${escapeXmlAttribute(srcOut)}/viewmodel/view/${viewClass}.java"/>

    <open file="${escapeXmlAttribute(srcOut)}/ui/${screenType?lower_case}/${screenClass}.java"/>
    <open file="${escapeXmlAttribute(resOut)}/layout/${layoutName}.xml"/>
    <open file="${escapeXmlAttribute(srcOut)}/viewmodel/view/${viewClass}.java"/>
    <open file="${escapeXmlAttribute(srcOut)}/viewmodel/${modelClass}.java"/>

</recipe>
