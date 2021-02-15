<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String session_id = (String) session.getAttribute("id");
	String session_name = (String) session.getAttribute("name");
	String session_level = (String) session.getAttribute("level");
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
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form id="frm" action="/campReview/ReviewUpdate.do" method="post">
		<input type="hidden" name="uid" value="${view.uid}">
		<table width=100% border=1>
			<tr>
				<td>제목</td>
				<td><input name="subject" value="${view.subject }"></td>
			</tr>
			<tr>
				<td>작성자</td>
				<td>${view.id }</td>
			</tr>
			<tr>
				<td>작성일</td>
				<td>${view.signdate }</td>
			</tr>
			<tr>
				<td>조회수</td>
				<td>${view.ref }</td>
			</tr>
			<tr>
				<td>내용</td>
				<td><textarea id="ir1" name="comment">${view.comment }</textarea></td>
			</tr>
			<tr>
				<td></td>
				<td><input type="button" id="save" value="전송하기"></td>
			</tr>
		</table>
	</form>
</body>
</html>