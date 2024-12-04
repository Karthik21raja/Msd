{
  "success": "<#if json?contains('<success>true</success>')>true<#else>false</#if>",
  "name": "${json?substring(json?index_of('<name>') + 6, json?index_of('</name>'))?trim}"
}