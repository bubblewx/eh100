<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<meta name="keywords" content="<s:property value="metaContent" escape="false"/>">

<%@ include file="/common/header.jsp"%>
<%@ include file="/book/searchMenuPart.jsp"%>

<tr>

<td align = "center">

<div >
          

<%@ include file="/common/pagination.jsp"%>

      <table width="100%" border="0" cellpadding="0" cellspacing="0">
    
    <tr>
    <th  class="bt">书名</th>
    <th class="bt">作者</th>
    <th class="bt">章节</th>
    <th class="bt">更新日期</th>
    <th class="bt">更新站点</th>
    </tr>
    <s:iterator value="bookVos">
    <tr>
    <td class="xt"><s:property value="bookName" /></td>
    <td class="xt"><s:property value="author"/></td>
    <td class="xt">
<a href="../common/go.action?target=<s:property value="chapterUrl"/>"><s:property value="chapterName"/></a>
</td>

    <td class="xt"><s:date name="createDate" format="yyyy-MM-dd" /></td>
	<td class="xt">
<s:property value="targetSiteName" />|
<a href ="<%=request.getContextPath() %>/book/bookId_<s:property value="id"/>.html">
点击显示该书目录</a>
	</td>
    </tr>
    </s:iterator>
    </table>



</div>

</td>

</tr>



<%@ include file="/common/footer.jsp"%>
