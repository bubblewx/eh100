<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>

	<s:if
		test="bookVosReaded != null && bookVosReaded.size()> 0">
<script src="../js/jquery.js" type="text/javascript"></script>			


<script>

function selectAll(item,isSelect){
    var obj = document.getElementsByName(item);
    for(var i=0; i<obj.length; i++){
        obj[i].checked = isSelect;
    }
}




function addBooksFromDirectory() { 
	 
	 
    if(confirm('确认要将其上架么?'))
  {
   var selected;
  var sid="?";
  
  $("#listTable tr:gt(0)").each(
      function callback(index, domElement) {           
           if($(this).children().eq(0).children().eq(0).attr("checked")==true){                         
          sid = sid + "addIds="+$(this).children().eq(0).children().eq(0).val()+"&";
        }
        
 
      }
  );
  sid = sid.substr(0,sid.length-1);
  if(sid==""){

      alert('请选择上架的书');
      
      return;
  }

  $("#formForadd").attr("action", "BookDirectoryAddSave.action"+sid);
  $("#formForadd").submit();
    }    
  
}     



</script>

<tr>

<td align = "center">

<div >
          
      <table id ="listTable" width="100%" border="0" cellpadding="0" cellspacing="0">
    
    <tr>
     <th style="text-align: center" class="countColumn"><input type="checkbox"
            onclick="selectAll('selectItems',this.checked)" /></th>
    <th  class="bt">书名</th>
    <th class="bt">作者</th>
    <th class="bt">章节</th>
    <th class="bt">更新日期</th>

    </tr>
    <s:iterator value="bookVosReaded">
    <tr>
    <td align="center">
    <input name="selectItems" type="checkbox"  value='<s:property value="id"/>'/>
     </td>
    <td class="xt">
    	<s:url action="LatestBookMenu.action" id="bookNameUrl">
		<s:param name="bookQuery.keyword" value="%{bookName}"></s:param>
       <s:param name="bookQuery.searchtype" value="'bookName'"></s:param>
	</s:url>

     <s:a href="%{bookNameUrl}"><s:property value="bookName"/></s:a>

</td>
    <td class="xt"><s:property value="author"/></td>
    <td class="xt">
<a href ="<%=request.getContextPath() %>/book/bookMenuId_<s:property value="id"/>.html">
<s:property value="chapterName"/></a>
</td>

    <td class="xt"><s:date name="createDate" format="yyyy-MM-dd" /></td>

    </tr>
    </s:iterator>
    </table>



</div>

</td>

</tr>
<tr>
<td>
<div align="center"  style="margin-top:20px; margin-bottom:10px">
	<form id = "formForadd" action="BookDirectoryAddSave.action" method="post" >
	<input  class ="keyword"  type ="button" id ="addBook" value ="将选中的书上架" onclick = "addBooksFromDirectory()">


  </form>
</div>
</td>

</tr>
</s:if>


