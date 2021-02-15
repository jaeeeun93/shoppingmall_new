package ReviewController;

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

import DAO.ReviewDAO;
import Model.ReviewBean;

@WebServlet("/campReview/write_insert.do")
@MultipartConfig(
		//파일이 디스크에 쓰여지는 사이즈를 지정 할수있음
		fileSizeThreshold = 0,
		location = "C:\\jsp\\Project02\\WebContent\\upload"
		)
public class ReviewWriteInsert extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ReviewWriteInsert() {
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
		
		String uploadFileName="";
		String thumbFile="";
		
		Part part = request.getPart("file");
		
		Date Rday = new Date();
		SimpleDateFormat cal1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String Rdate = cal1.format(Rday);
		
		if(part.getSize()>0) {
			String contentDisposition = part.getHeader("content-disposition");
			//파일 이름을 변수에 담는다.
			uploadFileName= getUploadFileName(contentDisposition);
			
			Date today =new Date();
			SimpleDateFormat cal2 = new SimpleDateFormat("yyyyMMddhhmmss");
			String thumbDate = cal2.format(today);
			
			uploadFileName = thumbDate+"_"+uploadFileName;
			
			part.write(uploadFileName);
			
			String filePath="C:\\jsp\\Project02\\WebContent\\upload\\";
			
			String orglmg = filePath + uploadFileName;//경로+원본파일이름- 원본파일
			thumbFile = "thumb_" + uploadFileName;//썸네일 파일이름
			String thumblmg = filePath+thumbFile;//파일경로+썸네일파일명
			
			int thumbWidth = 200;
			int thumbHeight = 200;
			
			try {
				//Image image = JimiUtils.getThumbnail("이미지경로/이미지파일명", "넓이", "높이", Jimi.IN_MEMORY);
				//Jimi.putImage(image, "썸네일경로/썸네일파일명");

				Image thumbnail = JimiUtils.getThumbnail(orglmg, thumbWidth, thumbHeight, Jimi.IN_MEMORY);// 썸네일 설정
				Jimi.putImage(thumbnail, thumblmg);// 썸네일 생성
				
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		ReviewBean bean = new ReviewBean();
		bean.setId(request.getParameter("id"));
		bean.setSubject(request.getParameter("subject"));
		bean.setComment(request.getParameter("comment"));
		bean.setSigndate(Rdate);
		bean.setFile(uploadFileName);
		bean.setFile_s(thumbFile);
		
		ReviewDAO dao = new ReviewDAO();
		dao.insertReview(bean);
		
		response.sendRedirect("/campReview/ReviewList.do");
	}
	//사용브라우저가 chrome인 경우
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
