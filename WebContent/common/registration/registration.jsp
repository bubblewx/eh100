<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ include file="/common/header.jsp"%>


<link rel="stylesheet" type="text/css" media="screen" href="../css/cmxform.css" />
<link rel="stylesheet" type="text/css" media="screen" href="../css/screen.css" />

<script src="../js/jquery.js" type="text/javascript"></script>
<script src="../js/jquery.validate.js" type="text/javascript"></script>

<script src="../js/cmxforms.js" type="text/javascript"></script>
<script type="text/javascript">


$().ready(function() {
	
	// validate signup form on keyup and submit
	$("#signupForm").validate({
		rules: {



		"userVo.userId": {
				required: true,
						minlength: 2,

						 remote: "checkName.action" 
					},
					
					"userVo.passWord": {
				required: true,
				minlength: 5
			},
			"userVo.confirmPassword": {
				required: true,
				minlength: 5,
				equalTo: "#passWord"
			},
			"userVo.email": {
				required: true,
				email: true
			}
		},
		messages: {

			"userVo.userId": {
				required: "请输入用户名",
				minlength: "用户名长度要大于2位",
				remote: "该用户已经存在"
			},
			"userVo.passWord": {
				required: "请输入密码",
				minlength: "密码长度需要大于5位"
			},
			"userVo.confirmPassword": {
				required: "请输入验证密码",
				minlength: "验证密码长度需要大于5位",
				equalTo: "密码需要一致"
			},
			"userVo.email": "请输入有效邮件"
		}
	});
	

	
	// check if confirm password is still valid after password changed
	$("#password").blur(function() {
		$("#confirm_password").valid();
	});
	

});
</script>

<style type="text/css">
#commentForm { width: 500px; }
#commentForm label { width: 250px; }
#commentForm label.error, #commentForm input.submit { margin-left: 253px; }
#signupForm { width: 670px; }
#signupForm label.error {
	margin-left: 10px;
	width: auto;
	display: inline;
}
#newsletter_topics label.error {
	display: none;
	margin-left: 103px;
}
</style>


<tr>
<td align ="center">


<s:form  cssClass ="cmxform" action="/common/registration.action" theme="simple" method="post" id="signupForm">


	<fieldset>


		<p>
			<label for="username">用户名</label>
			<input id="username" name="userVo.userId" />
		</p>
		<p>
			<label for="password">密码</label>
			<input id="passWord" name="userVo.passWord" type="password" />
		</p>
		<p>
			<label for="confirm_password">再输一次密码</label>
			<input id="confirmPassword" name="userVo.confirmPassword" type="password" />
		</p>
		<p>
			<label for="email">邮件地址</label>
			<input id="email" name="userVo.email" />
		</p>

		<p>
			<input class="submit" type="submit" value="提交"/>
		</p>
	</fieldset>
</s:form>
</td>
</tr>
<%@ include file="/common/footer.jsp"%>