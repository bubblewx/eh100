<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<script>

            function disableFunc(el, flag) {      
          try {               
     el.disabled = flag;         
       }            
    catch(E){}          
                    
  if (el.childNodes && el.childNodes.length > 0) {    
    for (var x = 0; x < el.childNodes.length; x++) {              
          disableFunc(el.childNodes[x],flag);         
           }         
       }    
}

function hideSearchType(){


if(document.getElementById("fuzzySearch").checked){
disableFunc(document.getElementById("searchType"),true);
document.getElementById("searchType").style.display='none';
}
else{
disableFunc(document.getElementById("searchType"),false);
document.getElementById("searchType").style.display='block';
}

}
</script>

<tr>
	<td align="center">
	<s:if
		test="warningMsg!=null ">
			<s:property value="%{warningMsg}"/>
	</s:if>
	<form action="<%=request.getContextPath() %>/book/LatestBookMenu.action" autocomplete="off"><s:textfield
		theme="simple" name="bookQuery.keyword" size="42"
		cssClass="search ac_input" maxlength="100" /> <input class="keyword"
		value="搜索" type="submit">

<s:if test="bookQuery!= null ">
<input type="radio" name="bookQuery.searchWay" onclick="hideSearchType()" <s:if test="bookQuery.exactSearchChecked"> checked </s:if> value="exact">精确搜索<input type="radio" onclick="hideSearchType()" id="fuzzySearch"  <s:if test="bookQuery.fuzzySearchChecked"> checked </s:if>  name="bookQuery.searchWay"  value="fuzzy">模糊搜索
</s:if> <s:else>
<input type="radio" name="bookQuery.searchWay" onclick="hideSearchType()" checked  value="exact">精确搜索<input type="radio" onclick="hideSearchType()" id="fuzzySearch"     name="bookQuery.searchWay"  value="fuzzy">模糊搜索

</s:else>
<br>

<div id ="searchType" <s:if test="bookQuery.fuzzySearchChecked">  style='display:none;'</s:if><s:else>style='display:block;' </s:else>>
	<s:if test="bookQuery.searchtype!= null ">
		<s:radio theme="simple" name="bookQuery.searchtype" disabled =  "%{bookQuery.disableSearchTypDiv}"    
			list="#{'bookName':'小说','author':'作者','chapterName':'章节'}" />
	</s:if> <s:else>
		<s:radio theme="simple" name="bookQuery.searchtype" disabled =  "%{bookQuery.disableSearchTypDiv}"  value="'bookName'"
			list="#{'bookName':'小说','author':'作者','chapterName':'章节'}" />
	</s:else>

</div>
		
<div style="font-size: 10.5pt;">
搜索提示：使用精确搜索， 请输入准确的关键字（请保证关键字开始部分的正确， 如:"天天向上"，请使用 "天天"或者"天天向"，勿使用"天向"或者"天向上"）并选择要搜的类别（书名，作者 或者章节）。 如果不记得关键字的开始部分，请使用模糊搜索 ，模糊搜索使用了中文分词，请输入有意义词，宁可少词也不要错词。
	</div>
	</form>
	
</td>

</tr>
</tbody>
</table>
</td>
</tr>