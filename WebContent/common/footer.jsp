<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<tr>

    <script>

             

 

    function CreateBookmarkLink() {

     

     title = "易华搜书"; 

     url = "http://www.eh100.com";

     

       if (window.sidebar) { // Mozilla Firefox Bookmark

            window.sidebar.addPanel(title, url,"");

       } else if( window.external ) { // IE Favorite

           window.external.AddFavorite( url, title); }

       else if(window.opera && window.print) { // Opera Hotlist

           return true; }

   }

 



 

              </script>


<td>
    <div id="foot">
        Copyright (C) 2002-2011 eh100 <a href="#" onclick="CreateBookmarkLink();">
            设搜书为首页</a> | <a href="http://top.qidian.com/">小说排行榜</a> | <a href="mailto:xing.wang@eh100.com">
     E-mail：xing.wang@eh100.com</a>
     </div>

</td>

</tr>
            </tbody></table>

        



</body></html>