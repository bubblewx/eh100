<#include "headerMin.html">
<#setting number_format="#">
<tr>

<td align = "center">
<div >      
<table width="100%" border="0" cellpadding="0" cellspacing="0">   
    <tr>
    <th  class="bt">书名</th>
    <th class="bt">作者</th>
    <th class="bt">最新章节</th>
    <th class="bt">更新日期</th>

    </tr>
  <#list bookVos as e>


    <tr>

    <td class="xt">
    	
		
       
	<a href ="../book/hotBookId_${e.hotBookId?int}.html">${e.bookName}</a>
	
</td>
    <td class="xt">${e.author}</td>
    <td class="xt">
     <a href="../book/bookMenuId_${e.id?int}.html">${e.chapterName}</a>

</td>

    <td class="xt">${e.createDate?string('yyyy-MM-dd')}</td>

    </tr>
    
</#list>
    
    </table>

</div>
</td>
</tr>
<tr>
<td >
<div align = "center" style ="margin-top:10px">
最热小说排行榜:&nbsp;&nbsp;&nbsp;
<a href="../latestUpdate/top_1.html">第一页</a>&nbsp;|&nbsp;

    <a href="../latestUpdate/top_2.html">第二页</a>&nbsp;|&nbsp;
            
    <a href="../latestUpdate/top_3.html">第三页</a>&nbsp;|&nbsp;
            
    <a href="../latestUpdate/top_4.html">第四页</a>&nbsp;|&nbsp;
            
    <a href="../latestUpdate/top_5.html">第五页</a>&nbsp;|&nbsp;
            


</div>
</td>

</tr>

<#include "footer.html">
