<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"><head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
    <title>易华读书</title>
    <link href="../css/style_sosu.css" rel="stylesheet" type="text/css">

   
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


<div class="top_nav" style="text-align: right;" id="topdiv"><a href="http://me.qidian.com/Login.aspx?returnurl=http%3A%2F%2Fsosu.qidian.com%2Fdefault.shtml">登陆</a> | <a href="http://www.qidian.com/reg/regpre.aspx">注册</a> | <a href="http://pwd.sdo.com/PTInfo/SafeCenter/GetPwd/ChgPwdStepInputAcc.aspx?pwdchoose=findpwd">找回密码</a> </div>
        <div style="width: 100%; margin-bottom: 20px;">
            <table width="100%" border="0" cellpadding="0" cellspacing="0">
                <tbody><tr>
                    <td align="center" height="80">
                        <img src="../image/logo2.gif" alt="易华读书" width="175" height="51">
                    </td>
                </tr>
                <tr>
                    <td align="center">
                        <table border="0" cellpadding="0" cellspacing="0">
                            <tbody><tr>
                                <td align="left" height="30" colspan =2>
                                	
                                	欢迎来到易华读书网
                                 </td>
                            </tr>
                            <tr>
                                <td align="left" colspan =2>
                                    <form action="LatestBook.action"  autocomplete="off">


 <s:textfield  theme="simple" name="bookQuery.keyword"  size="42"  cssClass="search ac_input"  maxlength="100" />
        

                                    <input class="keyword" value="快速搜书" type="submit"><br>        
                                   
									
						<s:radio name="bookQuery.searchtype"
							list="#{'bookName':'书名','author':'作者','chapterName':'章节名'}" />   
                                 </form>
</td>
                            </tr>
                        </tbody></table>
                    </td>
                </tr>

<tr>

<td align = "center">

<div >
          

<%@ include file="/pagination.jsp"%>

      <table width="100%" border="0" cellpadding="0" cellspacing="0">
    
    <tr>
    <th  class="bt">书名</th>
    <th class="bt">作者名</th>
    <th class="bt">章节名</th>
    <th class="bt">更新日期</th>
    <th class="bt">更新站点</th>
    </tr>
    <s:iterator value="bookVos">
    <tr>
    <td class="xt"><s:property value="bookName" /></td>
    <td class="xt"><s:property value="author"/></td>
    <td class="xt">
<a href="<s:property value="chapterUrl"/>"><s:property value="chapterName"/></a>
</td>

    <td class="xt"><s:property value="createDate" /></td>
	<td class="xt"><s:property value="targetSiteName" /></td>
    </tr>
    </s:iterator>
    </table>



</div>

</td>

</tr>


<tr>
<td align = "center" style="height:100px;">

       <div >
            <div >
                一周热词：</div>
            <div style=" margin-left: 10px; width: 800px;" id="topsegmentdiv"><a href="LatestBook.action?bookQuery.keyword=天珠变&bookQuery.searchtype=bookName" class="a02">天珠变</a> <a href="http://sosu.qidian.com/searchresult.aspx?searchkey=%E5%BC%82%E4%B8%96%E9%82%AA%E5%90%9B&amp;searchtype=%E7%BB%BC%E5%90%88" class="a02">异世邪君</a> <a href="http://sosu.qidian.com/searchresult.aspx?searchkey=%E6%88%98%E5%A4%A9&amp;searchtype=%E7%BB%BC%E5%90%88" class="a02">战天</a> <a href="http://sosu.qidian.com/searchresult.aspx?searchkey=%E9%BB%84%E9%87%91%E7%9E%B3&amp;searchtype=%E7%BB%BC%E5%90%88" class="a02">黄金瞳</a> <a href="http://sosu.qidian.com/searchresult.aspx?searchkey=%E5%90%9E%E5%99%AC%E6%98%9F%E7%A9%BA&amp;searchtype=%E7%BB%BC%E5%90%88" class="a02">吞噬星空</a> <a href="http://sosu.qidian.com/searchresult.aspx?searchkey=%E6%96%97%E7%A0%B4%E8%8B%8D%E7%A9%B9&amp;searchtype=%E7%BB%BC%E5%90%88" class="a02">斗破苍穹</a> <a href="http://sosu.qidian.com/searchresult.aspx?searchkey=%E9%81%AE%E5%A4%A9&amp;searchtype=%E7%BB%BC%E5%90%88" class="a02">遮天</a> <a href="http://sosu.qidian.com/searchresult.aspx?searchkey=%E5%8F%B1%E5%92%A4%E9%A3%8E%E4%BA%91&amp;searchtype=%E7%BB%BC%E5%90%88" class="a02">叱咤风云</a> <a href="http://sosu.qidian.com/searchresult.aspx?searchkey=%E5%A4%A7%E5%91%A8%E7%9A%87%E6%97%8F&amp;searchtype=%E7%BB%BC%E5%90%88" class="a02">大周皇族</a> <a href="http://sosu.qidian.com/searchresult.aspx?searchkey=%E5%8F%AC%E5%94%A4&amp;searchtype=%E7%BB%BC%E5%90%88" class="a02">召唤</a> <a href="http://sosu.qidian.com/searchresult.aspx?searchkey=%E9%87%8D%E7%94%9F%E4%B9%8B%E8%A1%99%E5%86%85&amp;searchtype=%E7%BB%BC%E5%90%88" class="a02">重生之衙内</a> <a href="http://sosu.qidian.com/searchresult.aspx?searchkey=%E6%B0%94%E5%86%B2%E6%98%9F%E6%B2%B3&amp;searchtype=%E7%BB%BC%E5%90%88" class="a02">气冲星河</a> <a href="http://sosu.qidian.com/searchresult.aspx?searchkey=%E6%AD%A5%E6%AD%A5%E7%94%9F%E8%8E%B2&amp;searchtype=%E7%BB%BC%E5%90%88" class="a02">步步生莲</a> <a href="http://sosu.qidian.com/searchresult.aspx?searchkey=%E6%B4%AA%E8%8D%92&amp;searchtype=%E7%BB%BC%E5%90%88" class="a02">洪荒</a> <a href="http://sosu.qidian.com/searchresult.aspx?searchkey=%E4%B8%89%E5%9B%BD&amp;searchtype=%E7%BB%BC%E5%90%88" class="a02">三国</a> <a href="http://sosu.qidian.com/searchresult.aspx?searchkey=%E9%AC%BC%E5%90%B9%E7%81%AF&amp;searchtype=%E7%BB%BC%E5%90%88" class="a02">鬼吹灯</a> <a href="http://sosu.qidian.com/searchresult.aspx?searchkey=%E5%90%8E%E5%AE%AB&amp;searchtype=%E7%BB%BC%E5%90%88" class="a02">后宫</a> </div>
        </div>
</td>
</tr>

<tr>

<td>
    <div id="foot">
        Copyright (C) 2002-2008 qidian <a href="#" onclick="this.style.behavior='url(#default#homepage)';this.setHomePage('http://sosu.qidian.com');">
            设搜书为首页</a> | <a href="http://top.qidian.com/">小说搜索榜</a> | <a href="http://www.qidian.com/aboutus/aboutus.aspx">
                关于起点</a>
     </div>

</td>

</tr>
            </tbody></table>

        



</body></html>