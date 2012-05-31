<#include "headerMin.html">
<#setting number_format="#">
<tr>

<td align = "center">

<div align="center">
   
   

         <table width="75%" align="center">

            <#list bookVos as e>

                  <#if ((e_index+1) % 6)== 0>

                       <tr>

                   </#if>



    <td class="xt">
<a target="_blank" href="../common/go.action?target=${e.chapterUrl}">${e.chapterName}</a>
</td>

                  



                <#if ((e_index+1) % 6)== 0>

                       </tr>

                   </#if>

               </#list>

         </table>

 

       

     </div>
</td>
</tr>

<#include "footer.html">
