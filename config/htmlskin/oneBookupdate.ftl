<#include "headerMin.html">
<#setting number_format="#">
<tr>

<td align = "center">
<div >      
<table width="100%" border="0" cellpadding="0" cellspacing="0">   
    <tr>
    <th  class="bt">书名</th>
    <th class="bt">作者</th>
    <th class="bt">章节</th>
    <th class="bt">更新日期</th>
  <th class="bt">更新站点</th>

    </tr>
  <#list bookVos as e>


    <tr>

    <td class="xt">
${e.bookName}
</td>
    <td class="xt">${e.author}</td>
    <td class="xt">
<a target="_blank" href="/common/go.action?target=${e.chapterUrl}">${e.chapterName}</a>


</td>

    <td class="xt">${e.createDate?string('yyyy-MM-dd')}</td>
  <td class="xt">${e.targetSiteName}|<a href="../book/bookId_${e.id?int}.html">点击显示该书目录</a></td>

    </tr>
    
</#list>
    
    </table>

</div>
</td>
</tr>
<tr>
<td >

</td>

</tr>

<#include "footer.html">
