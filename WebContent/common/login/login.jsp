<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ include file="/common/header.jsp"%>

<TR>
<TD align ="center">
<s:actionerror />
<s:form action="/common/LoginSystem.action" theme="simple" method="post" id="queryForm">
	<table class="clsSearchTbl">
<tr>
<td class="clsSearchViewCaption" >
用户名:
</td>
<td>
<s:textfield  theme="simple" name="userId"  size="20"  cssClass="textinput"  maxlength="20" />
</td>
</tr>

		<tr>
			<td class="clsSearchViewCaption" style="width:30% ;" align="center">
				 密码：
			</td>
			<td >
                <s:password name="passWord" cssClass="textinput"></s:password>
			</td> 
</tr>
<tr>
			<td colspan =2 align ="center" >
				<input type="submit" id="searchBu" class="button" value="登录" />
			</td>

		</tr>
	</table>
</s:form>
</TD>
</TR>

<%@ include file="/common/footer.jsp"%>