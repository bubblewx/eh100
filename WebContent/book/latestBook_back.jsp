<%@ page language="java" contentType="text/html; charset=GB18030"
    pageEncoding="GB18030"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GB18030">
<title></title>
</head>
<body>
<%@ include file="/pagination.jsp"%>

    <table>
    <tr>
    <td>bookName</td>
    <td>author</td>
    <td>chapterName</td>
    <td>createDate</td>
    <td>chapterUrl</td>
    </tr>
    <s:iterator value="bookVos">
    <tr>
    <td><s:property value="bookName" /></td>
    <td><s:property value="author"/></td>
    <td><s:property value="chapterName"/></td>
    <td><s:property value="createDate" /></td>
    <td><s:property value="chapterUrl"/></td>
    </tr>
    </s:iterator>
    </table>
</body>
</html>