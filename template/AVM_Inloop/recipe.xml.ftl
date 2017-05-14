<?xml version="1.0"?>
<recipe>

    <#if appCompat && !(hasDependency('eu.inloop:androidviewmodel'))>
    	<dependency mavenUrl="eu.inloop:androidviewmodel:1.3.1"/>
	</#if>

    <#if screenType == "Fragment">
    <instantiate from="src/app_package/layout.xml.ftl"
                 to="${escapeXmlAttribute(resOut)}/layout/${layoutName}.xml" />
    <instantiate from="src/app_package/Fragment.java.ftl"
                   to="${escapeXmlAttribute(srcOut)}/${scrPackage}/${screenClass}.java" />
    </#if>
    <#if screenType == "BindingFragment">
    <instantiate from="src/app_package/binding_layout.xml.ftl"
                 to="${escapeXmlAttribute(resOut)}/layout/${layoutName}.xml" />
    <instantiate from="src/app_package/BindingFragment.java.ftl"
                   to="${escapeXmlAttribute(srcOut)}/${scrPackage}/${screenClass}.java" />
    </#if>
    <instantiate from="src/app_package/ViewModel.java.ftl"
                   to="${escapeXmlAttribute(srcOut)}/${vmPackage}/${viewModelClass}.java" />
    <#if generateViewInterface>
        <instantiate from="src/app_package/ViewInterface.java.ftl"
                   to="${escapeXmlAttribute(srcOut)}/${vPackage}/I${viewModelClass?replace('ViewModel', 'View')}.java" />
    </#if>


    <#if scrPackage != "">
        <open file="${escapeXmlAttribute(srcOut)}/${scrPackage}/${screenClass}.java" />
    <#else>
        <open file="${escapeXmlAttribute(srcOut)}/${screenClass}.java" />
    </#if>

    <#if vmPackage != "">
        <open file="${escapeXmlAttribute(srcOut)}/${vmPackage}/${viewModelClass}.java" />
    <#else>
        <open file="${escapeXmlAttribute(srcOut)}/${viewModelClass}.java" />
    </#if>

    <#if vPackage != "">
        <open file="${escapeXmlAttribute(srcOut)}/${vPackage}/I${viewModelClass?replace('ViewModel', 'View')}.java" />
    <#else>
        <open file="${escapeXmlAttribute(srcOut)}/I${viewModelClass?replace('ViewModel', 'View')}.java" />
    </#if>
    
    <open file="${escapeXmlAttribute(resOut)}/layout/${layoutName}.xml" />
    
</recipe>