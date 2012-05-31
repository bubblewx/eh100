<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/common/head.jsp"%>
<title>教学资源共享系统登录</title>
<script type="text/javascript">
//if (self.location != top.location) {
    top.location.href = '<s:property value="ssoLoginUrl"/>';
//}
</script>
</head>
<body>
转向中央认证系统...
</body>
</html>