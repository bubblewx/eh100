<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ include file="/common/header.jsp"%>
<script src="../js/jquery.js" type="text/javascript"></script>
<tr>
<td>
<script>

function selectAll(item,isSelect){
    var obj = document.getElementsByName(item);
    for(var i=0; i<obj.length; i++){
        obj[i].checked = isSelect;
    }
}




function deleteBooksFromDirectory() { 	 
	 
    if(confirm('确认要将其下架么?'))
  {
   var selected;
  var sid="?";
  
  $("#listTable tr:gt(0)").each(
      function callback(index, domElement) {           
           if($(this).children().eq(0).children().eq(0).attr("checked")==true){                         
          sid = sid + "deletedIds="+$(this).children().eq(0).children().eq(0).val()+"&";
        }
        
 
      }
  );
  sid = sid.substr(0,sid.length-1);
  if(sid==""){

      alert('请选择下架的书');
      
      return;
  }
  $("#formForDeleted").attr("action", "BookDirectoryDeleteSave.action"+sid);
  $("#formForDeleted").submit();
    }    
  
}     



</script>
<div >
<fieldset><legend>管理您的书架</legend>


<table id ="listTable" width="100%" border="0" cellpadding="0" cellspacing="0">
 
    <tr>

         <th style="text-align: center" class="bt"><input type="checkbox"
            onclick="selectAll('selectItems',this.checked)" /></th>
        <th style="text-align: center" class="bt">书名</th>
        <th style="text-align: center" class="bt">章节名</th>
        <th style="text-align: center" class="bt">作者</th>
        <th style="text-align: center" class="bt">点击数</th>
		<th style="text-align: center" class="bt">最新章节</th>

    </tr>
    <s:iterator value="userVo.bookMenuVos"  id="entity">
        <tr >           
            <td class="xt">
                <input name="selectItems" type="checkbox"  value='<s:property value="id"/>'/>
             </td>

            <td class="xt">
    	<s:url action="LatestBookMenu.action" id="bookNameUrl">
		<s:param name="bookQuery.keyword" value="%{bookName}"></s:param>
       <s:param name="bookQuery.searchtype" value="'bookName'"></s:param>
	</s:url>

     <s:a href="%{bookNameUrl}"><s:property value="bookName"/></s:a>

</td>
		    <td class="xt">
		<a href ="<%=request.getContextPath() %>/book/bookMenuId_<s:property value="id"/>.html">
		<s:property value="chapterName"/>
		</td>
            <td class="xt"><s:property value="author"/></td>
            <td class="xt"><s:property value="latestBookHotVo.hitCount"/></td>
		    <td class="xt">
		<a href ="<%=request.getContextPath() %>/book/bookMenuId_<s:property value="latestBookHotVo.latestMenuId"/>.html">
		<s:property value="latestBookHotVo.latestChapterName"/>
		</td>
           
        </tr>
    </s:iterator>
<tr>
<td>

	<form id = "formForDeleted" action="BookDirectoryDeleteSave.action"  method ="post">
	<input  class ="keyword"  type ="button"   value ="将选中的书下架" id ="deleteBook" onclick = "deleteBooksFromDirectory()">


  </form>
</td>
</tr>
</table>
</fieldset>
</div>
</td>
</tr>
<%@ include file="/common/footer.jsp"%>