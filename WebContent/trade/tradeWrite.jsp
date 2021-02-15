<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@include file="/include/head.jsp"%>
<%
    String ctx = request.getContextPath();    //콘텍스트명 얻어오기.
%>

<style>
div.tradewrite{
}
</style>

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
              oEditors.getById["ir1"].exec("PASTE_HTML", ["사진은 아래의 첨부파일 기능을 사용해주세요"]);
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
<div class="tradewrite">
<form id="frm" action="/trade/write.do" method="post" enctype="multipart/form-data">
<table width=80% align=center border=1>
<input type="hidden" name="id" value="<%=session_id%>">
	<tr>
		<td align=center>판매글,구매글</td>
		<td align=center>
			<input type="radio" name="deal" value="1" checked>판매글
			<input type="radio" name="deal" value="2">구매글
			<input type="radio" name="deal" value="3">거래완료
		</td>
	</tr>
	<tr>
		<td align=center>제목</td>
		<td align=center><input name="subject" style="width:800px;"></td>
	</tr>
	<tr>
		<td align=center>가격</td>
		<td align=center>
			<input onKeyup="this.value=this.value.replace(/[^0-9]/g,'');" onKeydown="this.value=this.value.replace(/[^0-9]/g,'');"
			name="price" style="width:800px;" placeholder="Ex)10000 (숫자만 기입해주시기 바랍니다.)">
		</td>
	</tr>
	<tr>
		<td align=center>내용</td>
		<td><textarea id="ir1" name="comment"></textarea></td>
	</tr>
	<tr>
		<td align=center>첨부파일</td>
		<td align=center><input type="file" name="file1"></td>
	</tr>
	<tr>
		<td align=center>첨부파일2</td>
		<td align=center><input type="file" name="file2"></td>
	</tr>
	<tr>
		<td align=center>첨부파일3</td>
		<td align=center><input type="file" name="file3"></td>
	</tr>
	<tr>
		<td align=center>첨부파일4</td>
		<td align=center><input type="file" name="file4"></td>
	</tr>
	<tr>
		<td align=center colspan=2><input type="button" id="save" value="작성"></td>
	</tr>
</table>
</form>
</div>
<%@include file="/include/foot.jsp"%>