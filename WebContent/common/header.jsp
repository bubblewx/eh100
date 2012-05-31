<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"><head>
   <title>
        易华读书--最好最干净的小说搜索网站</title>
    <meta name="keywords" content="eh100 更新  网络小说 玄幻小说 小说搜索  搜索阅读网 小说阅读网 最新章节">
    <meta name="description" content="eh100 专注于网络热门小说搜索，方便大家舒服愉快地查找阅读玄幻小说，本站干净无广告">

    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
    <link href="<%=request.getContextPath()%>/css/style_sosu.css" rel="stylesheet" type="text/css">

   <script type="text/javascript">

  var _gaq = _gaq || [];
  _gaq.push(['_setAccount', 'UA-23618333-1']);
  _gaq.push(['_trackPageview']);

  (function() {
    var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;
    ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
    var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);
  })();

</script>
<!-- JiaThis Button BEGIN -->
<script type="text/javascript" src="http://v1.jiathis.com/code_mini/jiathis_r.js?move=0&amp;btn=r2.gif&amp;uid=1515944" charset="utf-8"></script>
<!-- JiaThis Button END -->
    <style type="text/css">
    
    td {font-size:10pt; line-height:180%; }
.xt{font-size:10pt;line-height:180%; text-align:center; border-bottom:1px #bbbbbb dashed;font-family:"宋体", "arial", "ms sans serif";}
.bt{font-size:10pt;line-height:180%; border-bottom:1px #bbbbbb solid;font-family:"宋体", "arial", "ms sans serif";}
.inp{font-size:11pt; height: 30px;}
.bd{border:1px solid #bbbbbb;}
a:link {color: #004d00; text-decoration: none}
a:active {color: blue; text-decoration: underline}
a:visited {color: #004d00; text-decoration: none}
a:hover {color: blue; text-decoration: underline;position:relative;left:1px;top:1px}
a.tt:link {color: black; text-decoration: none}
a.tt:active {color: blue; text-decoration: underline}
a.tt:visited {color: black; text-decoration: none;}
a.tt:hover {color: blue; text-decoration: underline;position:relative;left:1px;top:1px}
a.bl:link {color: blue; text-decoration: none}
a.bl:active {color: red; text-decoration: none}
a.bl:visited {color: blue; text-decoration: none}
a.bl:hover {color: red; text-decoration: underline;position:relative;left:1px;top:1px}
a.tl:link {color: #123b8d; text-decoration: none}
a.tl:active {color: blue; text-decoration: none}
a.tl:visited {color: #123b8d; text-decoration: none}
a.tl:hover {color: blue; text-decoration: underline;position:relative;left:1px;top:1px}
body {margin-top:0px;margin-right:0px;margin-bottom:0px;}
h1 {font-size:22px;line-height:180%;margin:0px;padding: 0px;color: #cc0000;}
p {font-size:10.5pt;line-height:180%;}

div,form,img,ul,li,img{margin:0; padding:0; border:0;}
table{background-color:#ffffff;} 

     </style>
</head><body>
    <div id="wrap" style="margin-left: auto; margin-right: auto;">
        ﻿<style type="text/css">
    #popUrl
    {
        top: 100px;
        border: 0;
	z-index: 999;
	margin:0 auto;
    }
    #loginDiv
    {
	position:fixed;
	_position:absolute;
        top:100px;
	z-index: 999;
	width:100%;
	height:100%;
	text-align:center;
	margin:0;
	padding:0;
	border:0;
    }
    #layerMask
    {
	position:fixed;
	_position:absolute;
	z-index: 100;
        opacity:0.3;
        filter: alpha(opacity=30);
	background:#000;
        top:0;
	width:100%;
	height:100%;
    }
</style>


<div class="top_nav" style="text-align: right;" id="topdiv">


<a href="<%= request.getContextPath()%>/index.jsp">回到主页</a>|<a href="<%= request.getContextPath()%>/book/LatestBookReadedMenu.action">最近看过的书</a>  |
	<s:if
		test="#session.userId !=null">
	欢迎<s:property value="#session.userId"/>,<a href="<%= request.getContextPath()%>/book/BookDirectory.action">请点击您的书架</a> 
	</s:if>
	<s:else>
您尚未登陆
	</s:else>

  <a href="<%= request.getContextPath()%>/common/ShowLogin.action">登陆</a> 

 | <a href="<%= request.getContextPath()%>/common/ShowRegistration.action">注册</a>  </div>
        <div style="width: 100%; margin-bottom: 20px;">
            <table width="100%" border="0" cellpadding="0" cellspacing="0">
                <tbody><tr>
                    <td align="center" height="80">

                        <img src="../image/logo2.gif" alt="易华读书" width="175" height="51">

                    </td>
                </tr>
   <tr>
<td align="center" >


</td>
   </tr>