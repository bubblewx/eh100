<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ include file="/common/header.jsp"%>
                           

<tr>

<td align = "center">

<div >
   
   <s:if test="bookVos.size">

         <table width="75%" align="center">

              <s:iterator value="bookVos" id="vo" status="count">

                   <s:if test="#count.index % 6 == 0">

                       <tr>

                   </s:if>



                   <td  align="center">

    <td class="xt">
<a href="../common/go.action?target=<s:property value="chapterUrl"/>"><s:property value="chapterName"/></a>
</td>

                   </td>



                   <s:if test="(#count.index + 1) % 6 == 0">

                       </tr>

                   </s:if>

              </s:iterator>

         </table>

     </s:if>

       

     </div>
</td>
</tr>

<%@ include file="/common/footer.jsp"%>
