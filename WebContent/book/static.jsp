<html>
<head>
<%@ taglib prefix="s" uri="/struts-tags"%>
<meta name="keywords" content="<s:property value="metaContent" escape="false"/>">

<meta http-equiv="Content-Language" content="UTF-8">
<meta HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=UTF-8">
<title></title>
</head>
<body>
<%@ include file="/common/header.jsp"%>
<%@ include file="/book/searchMenuPart.jsp"%>
<%
String hotpage = request.getParameter("hotPage");

if(hotpage!=null)
		{
	String topStaticContext = com.eh100.cache.common.EH100CacherManager.getInstance().getStaticHtml(hotpage);
		
		
%>
<%=topStaticContext %>
<%
		}
else{
%>
<s:property value="staticContext" escape="false"/>

<%} %>
<%@ include file="/common/footer.jsp"%>
</body>
</html> 