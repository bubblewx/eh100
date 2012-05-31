<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<SCRIPT type="text/javascript">
	function trim(str) {
		return str.replace(/(^\s*)|(\s*$)/g, "");
	}

	function selectPage(input) {

		var value = trim(input.value);
		if (value == "") {
			return;
		}

		if (/\d+/.test(value)) {

			input.form.submit();
			return;
		}
		alert("请输入正确的页数");
		input.focus();

	}
</SCRIPT>
<div class="pagech"><s:if test="pagination.totalPage != 0">
	<s:url action="%{#request.url}" id="first">
		<s:param name="pagination.currentPage" value="1"></s:param>
		<s:param name="bookQuery.keyword" value="%{bookQuery.keyword}"></s:param>
	</s:url>
	<s:url action="%{#request.url}" id="next">
		<s:param name="pagination.currentPage"
			value="pagination.currentPage+1">
		</s:param>
		<s:param name="bookQuery.keyword" value="%{bookQuery.keyword}"></s:param>
	</s:url>
	<s:url action="%{#request.url}" id="prior">
		<s:param name="pagination.currentPage"
			value="pagination.currentPage-1"></s:param>
		<s:param name="bookQuery.keyword" value="%{bookQuery.keyword}"></s:param>
	</s:url>
	<s:url action="%{#request.url}" id="last">
		<s:param name="pagination.currentPage" value="pagination.totalPage"></s:param>
		<s:param name="bookQuery.keyword" value="%{bookQuery.keyword}"></s:param>
	</s:url>
	<s:if test="pagination.currentPage == 1">
		<span class="current">首页</span>
		<span class="current">上一页</span>
	</s:if>
	<s:else>
		<s:a href="%{first}">首页</s:a>
		<s:a href="%{prior}">上一页</s:a>
	</s:else>
	<s:if
		test="pagination.currentPage == pagination.totalPage || pagination.totalPage == 0">
			欢迎<s:property value="#session.mySessionPropKey"/>
	</s:if>
	<s:else>
		<s:a href="%{next}">下一页</s:a>&nbsp;&nbsp;
                  <s:a href="%{last}">末页</s:a>
	</s:else>
	<span class="jumplabel">跳转到</span>
	<s:form action="%{#request.url}" theme="simple"
		cssStyle="display:inline">
		<s:hidden name="pagination.totalPage" value="%{pagination.totalPage}"></s:hidden>
		<input type="text" name="pagination.currentPage" size="2"
			onblur="selectPage(this)" />
		<s:hidden name="bookQuery.keyword" value="%{bookQuery.keyword}"></s:hidden>
	</s:form>

	<span class="jumplabel">页</span>
	<span class="jumplabel">共<s:property
		value="pagination.totalRecord" />条</span>
	<span class="jumplabel">当前是第<s:property
		value="pagination.currentPage" />/<s:property
		value="pagination.totalPage" />页</span>


</s:if></div>
