"masters": [
    <#list SearchMastersResponse as searchMaster>
        <#if searchMaster.ORMSEARCH.Master?has_content>
            "master": "${searchMaster.ORMSEARCH.Master}"<#if searchMaster?index < SearchMastersResponse?size - 1>,</#if>
        </#if>
    </#list>
]