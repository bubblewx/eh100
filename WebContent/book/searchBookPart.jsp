<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<tr>
	<td align="center">
	<form action="<%=request.getContextPath() %>/book/LatestBook.action" autocomplete="off"><s:textfield
		theme="simple" name="bookQuery.keyword" size="42"
		cssClass="search ac_input" maxlength="100" /> <input class="keyword"
		value="快速搜索" type="submit"><br>

<!--  
	<s:if test="bookQuery.searchtype!= null ">
		<s:radio theme="simple" name="bookQuery.searchtype"
			list="#{'bookName':'小说','author':'作者','chapterName':'章节'}" />
	</s:if> <s:else>
		<s:radio theme="simple" name="bookQuery.searchtype" value="'bookName'"
			list="#{'bookName':'小说','author':'作者','chapterName':'章节'}" />
	</s:else>
-->	
	</form>

	</td>
</tr>
</tbody>
</table>
</td>
</tr>