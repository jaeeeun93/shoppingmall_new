<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
String session_id = (String)session.getAttribute("id");
String session_name = (String)session.getAttribute("name");
String session_level = (String)session.getAttribute("level");
String ctx = request.getContextPath();    //콘텍스트명 얻어오기.
%>
<!-- 2020.12.03 추가 -->
<script>
function comment1(){
	if(co.subject.value == ""){
		alert("제목을 입력해주세요");
		co.subject.focus();
		return false;
	}
	if(co.comment.value==""){
		alert("내용을 입력해주세요");
		co.comment.focus();
		return false;
	}
	alert("게시글이 입력되었습니다");
}
</script>
<script type="text/javascript" src="<%=ctx %>/SE2/js/HuskyEZCreator.js" charset="utf-8"></script>
<!-- jQuery를 사용하기위해 jQuery라이브러리 추가 -->
<script type="text/javascript" src="http://code.jquery.com/jquery-1.9.0.min.js"></script>
<script type="text/javascript">
var oEditors = [];
$(function(){
      nhn.husky.EZCreator.createInIFrame({
          oAppRef: oEditors,
          elPlaceHolder: "ir1", //textarea에서 지정한 id와 일치해야 합니다. 
          //SmartEditor2Skin.html 파일이 존재하는 경로
          sSkinURI: "/SE2/SmartEditor2Skin.html",  
          htParams : {
              // 툴바 사용 여부 (true:사용/ false:사용하지 않음)
              bUseToolbar : true,             
              // 입력창 크기 조절바 사용 여부 (true:사용/ false:사용하지 않음)
              bUseVerticalResizer : true,     
              // 모드 탭(Editor | HTML | TEXT) 사용 여부 (true:사용/ false:사용하지 않음)
              bUseModeChanger : true,         
              fOnBeforeUnload : function(){
                   
              }
          }, 
          fOnAppLoad : function(){
              //기존 저장된 내용의 text 내용을 에디터상에 뿌려주고자 할때 사용
              oEditors.getById["ir1"].exec("PASTE_HTML", ["파일 업로드는 아래 파일 선택을 눌러주세요"]);
          },
          fCreator: "createSEditor2"
      });
      
      //저장버튼 클릭시 form 전송
      $("#save").click(function(){
          oEditors.getById["ir1"].exec("UPDATE_CONTENTS_FIELD", []);
          $("#frm").submit();
      });    
});
 
</script>

<form id="frm" name="co" action="/campReview/write_insert.do" method="post" enctype="multipart/form-data" onsubmit="return comment1();">
<input type="hidden" name="id" value="<%=session_id %>">
<table border=1 width=100%>
	<tr>
		<td>제목</td>
		<td>
			<input name="subject">
		</td>
	</tr>
	<tr>
		<td>내용</td>
		<td><textarea id="ir1" name="comment"></textarea></td>
	</tr>
	<tr>
		<td>첨부파일</td>
		<td>
			<input type="file" name="file">
		</td>
	</tr>
	<tr>
		<td></td>
		<td>
			<input type="button" id="save" value="작성">
		</td>
	</tr>
</table>
</form>