package BoardController;

import java.awt.Image;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.sun.jimi.core.Jimi;
import com.sun.jimi.core.JimiUtils;

import DAO.BoardDAO;
import Model.BoardBean;

@WebServlet("/board/write_insert.do")
@MultipartConfig(
		fileSizeThreshold = 0,
		location = "C:\\jsp\\Project02\\WebContent\\upload"
)					
public class BoardWriteInsert extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public BoardWriteInsert() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAll(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAll(request, response);
	}
	
	protected void doAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
				
		//파일 업로드 
		String uploadFileName = "";
		String thumbFile = "";
		
		Part part = request.getPart("file");
		
		//Board 테이블에 Signdate에 값을 넣기위한 Date함수이다.
		Date bday = new Date();
		SimpleDateFormat cal1 = new SimpleDateFormat("yyyy-MM-dd-hh:mm:ss");
		String bdate = cal1.format(bday);
		
		if(part.getSize() > 0) {
			String contentDisposition = part.getHeader("content-disposition");
			uploadFileName = getUploadFileName(contentDisposition);
	
			//오늘 날짜 구하기
			Date today = new Date();
			SimpleDateFormat cal2 = new SimpleDateFormat("yyyyMMddhhmmss");
			String thumbDate = cal2.format(today);

			//오늘 날짜를 구한것으로 중복 파일 피하기 위한 처리 
			uploadFileName = thumbDate+"_"+uploadFileName;
			
			part.write(uploadFileName);
			
			//썸네일
			String filePath = "C:\\jsp\\Project02\\WebContent\\upload\\";
			
			String orgImg = filePath + uploadFileName; //원본파일
			thumbFile = "thumb_" + uploadFileName; //썸네일 파일이름
			String thumbImg = filePath + thumbFile; //파일경로 + 파일명
			
			int thumbWidth = 200; //가로
			int thumbHeight = 200; //세로
			
			try {
				
			Image thumbnail = JimiUtils.getThumbnail(orgImg, thumbWidth, thumbHeight, Jimi.IN_MEMORY);// 썸네일 설정
			Jimi.putImage(thumbnail, thumbImg);// 썸네일 생성
			
			}catch(Exception e){
				e.printStackTrace();
			}
			
		}
		
		BoardBean bean = new BoardBean();
		
		bean.setId(request.getParameter("id"));
		bean.setSubject(request.getParameter("subject"));
		bean.setComment(request.getParameter("comment"));
		bean.setSigndate(bdate);
		bean.setGongji(request.getParameter("gongji"));
		bean.setFile(uploadFileName);
		bean.setFile_s(thumbFile);
		
		BoardDAO dao = new BoardDAO();
		dao.insertBoard(bean);
		
		response.sendRedirect("/board/boardList.do");
	}
	
	private String getUploadFileName(String contentDisposition) {

		// TODO Auto-generated method stub

		String uploadFileName = null;

		String[] contentSplitStr = contentDisposition.split(";");

		int firstQutosIndex = contentSplitStr[2].indexOf("\"");

		int lastQutosIndex = contentSplitStr[2].lastIndexOf("\"");

		uploadFileName = contentSplitStr[2].substring(firstQutosIndex + 1, lastQutosIndex);

		return uploadFileName;
	}
}
