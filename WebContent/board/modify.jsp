<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@include file="/include/head.jsp"%>
<%
    String ctx = request.getContextPath();    //콘텍스트명 얻어오기.
%>

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
              oEditors.getById["ir1"].exec("PASTE_HTML", [""]);
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

<div style="height:150px;"></div>
<form id="frm" action="/board/update.do" method="post" enctype="multipart/form-data">
<table width=80% align=center border=1>
<input type="hidden" name="uid" value="${view.uid}">
	<tr>
		<td>제목</td>
		<td><input name="subject" value="${view.subject}" style="width:800px;"></td>
 	</tr>
	<tr>
		<td>작성자</td>
		<td>${view.id}</td>
	</tr>
	<tr>
		<td>작성일</td>
		<td>${view.signdate}</td>
	</tr>
	<tr>
		<td>조회수</td>
		<td>${view.ref}</td>
	</tr>
	<tr>
		<td>내용</td>
		<td>
			<textarea id="ir1" name="comment">${view.comment}</textarea>
		</td>
	</tr>
	<tr>
		<td></td>
		<td><input type="submit" value="수정하기"></td>
	</tr>
</table>
</form>

<%@include file="/include/foot.jsp"%>