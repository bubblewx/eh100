<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ include file="/common/header.jsp"%>
<%@ include file="/book/searchMenuPart.jsp"%>


<%@ include file="/book/latestBookMenuReaded.jsp"%>
<tr>
</tr>

<tr>

<td align = "center">

<div >
          

<%@ include file="/common/pagination.jsp"%>

      <table width="100%" border="0" cellpadding="0" cellspacing="0">
    
    <tr>
    <th  class="bt">书名</th>
    <th class="bt">作者</th>
    <th class="bt">最新章节</th>
    <th class="bt">更新日期</th>

    </tr>
    <s:iterator value="bookVos">
    <tr>
    <td class="xt">

	<s:if
		test="hotBookId !=null">

<a href ="<%=request.getContextPath() %>/book/hotBookId_<s:property value="hotBookId"/>.html">
<s:property value="bookName"/>
</a>
</s:if>
<s:else>
    	<s:url action="LatestBook.action" id="bookNameUrl">
		<s:param name="bookQuery.keyword" value="%{bookName}"></s:param>
       <s:param name="bookQuery.searchtype" value="'bookName'"></s:param>
	</s:url>

     <s:a href="%{bookNameUrl}"><s:property value="bookName"/></s:a>
</s:else>
</td>
    <td class="xt"><s:property value="author"/></td>
    <td class="xt">
<a href ="<%=request.getContextPath() %>/book/bookMenuId_<s:property value="id"/>.html">
<s:property value="chapterName"/></a>
</td>

    <td class="xt"><s:date name="createDate" format="yyyy-MM-dd" /></td>

    </tr>
    </s:iterator>
    </table>



</div>

</td>

</tr>

<!--  
<tr>
<td align = "center" style="height:100px;">

       <div >
            <div >
                上周热词：</div>
            <div style=" margin-left: 10px; width: 800px;" id="topsegmentdiv">

	<s:url action="LatestBook.action" id="bookUrl">
		<s:param name="bookQuery.keyword" value="'异世邪君'"></s:param>
       <s:param name="bookQuery.searchtype" value="'chapterName'"></s:param>
	</s:url>

     <s:a href="%{bookUrl}">异世邪君</s:a>


	<s:url action="LatestBook.action" id="bookUrl2">
		<s:param name="bookQuery.keyword" value="'战天'"></s:param>
       <s:param name="bookQuery.searchtype" value="'chapterName'"></s:param>
	</s:url>

     <s:a href="%{bookUrl2}">战天</s:a>

        </div>
</td>
</tr>
-->
<%@ include file="/common/footer.jsp"%>
