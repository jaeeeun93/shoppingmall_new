<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@include file="/include/head.jsp"%>
<%
    String ctx = request.getContextPath();    //콘텍스트명 얻어오기.
%>
<div style="height:150px;"></div>
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
              oEditors.getById["ir1"].exec("PASTE_HTML", ["내용을 작성하세요"]);
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

<form id="frm" action="/board/write_insert.do" method="post" enctype="multipart/form-data">
<input type="hidden" name="id" value="<%=session_id%>">
<table width=80% align=center border=1>
	<tr>
		<td align=center width=200>옵션</td>
		<td align=center>
			<!-- 다른게시판에서 사용할 수 있으니 남겨둔것. 나중에 지울것 -->
			<% if(session_level != null && session_level.equals("10")){ %>
				<input type="radio" name="gongji" value="1" checked>공지
				<input type="radio" name="gongji" value="2">일반
				<input type="radio" name="gongji" value="3">비밀
			<% }else{ %>
				<input type="radio" name="gongji" value="2" checked>일반
				<input type="radio" name="gongji" value="3">비밀
			<%} %>
		</td>
	</tr>
	<tr>
		<td align=center>제목</td>
		<td align=center><input name="subject" style="width:800px;"></td>
	</tr>
	<tr>
		<td align=center>내용</td>
		<td align=center><textarea id="ir1" name="comment"></textarea></td>
	</tr>
	<tr>
		<td align=center>첨부파일</td>
		<td align=center><input type="file" name="file"></td>
	</tr>
	<tr>
		<td colspan=2 align=center><input type="button" id="save" value="작성"></td>
	</tr>
</table>
</form>

<%@include file="/include/foot.jsp"%>