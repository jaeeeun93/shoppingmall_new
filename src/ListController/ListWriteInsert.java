package ListController;

import java.awt.Image;
import java.io.IOException;
import java.lang.ProcessBuilder.Redirect;
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

import DAO.ListDAO;
import Model.ListBean;

@WebServlet("/list/write_insert.do")
@MultipartConfig(
		fileSizeThreshold = 0,
		location = "C:\\jsp\\Project02\\WebContent\\upload"
)
public class ListWriteInsert extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	public ListWriteInsert() {
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
		
		//파일업로드
		String uploadFileName= "";
		String thumbFile= "";
		//part는 다른 파일을 올렸을때 엎어써서 사진수정가능
		//multi part는 업로드시 1.jpg가 11.jpg로 올라가고
		//다른걸 올려도 12.jpg 로 올라가서 중복사진 올라가게됨
		Part part = request.getPart("file");
		
		//list 테이블에 Signdate에 값을 넣기위한 Date함수이다.(글 쓴 날짜)
		Date lday =new Date();
		SimpleDateFormat cal1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String ldate = cal1.format(lday);
		
		//사진을 첨부했을때만 사진이 저장 되게 이걸 안넣으면
		//첨부를 안해도 날짜가 계속 올라감
		//uploadfilename 변수 적는거외엔 다른거 바꿀 필요 x
		//사진첨부하면 1 이되고 기본 0
		
		if(part.getSize() > 0) {
			//헤더정보를 가져오는데 이정보로 파일이름을 얻어와서
			String contentDisposition = part.getHeader("content-disposition");
			//파일 이름을 변수에 담는다.
			uploadFileName= getUploadFileName(contentDisposition);
			
			//오늘 날짜 구하기 날짜 넣어서 중복되는 것 없게
			Date today = new Date();
			SimpleDateFormat cal2 = new SimpleDateFormat("yyyyMMddhhmmss");
			String thumbDate = cal2.format(today);

			//오늘 날짜를 구한것으로 중복 파일 피하기 위한 처리 
			//날짜_파일이름->uploadfilename
			uploadFileName = thumbDate+"_"+uploadFileName;
			
			//사진 업로드
			part.write(uploadFileName);
			
			//썸네일 2020.12.03추가
			String filePath="C:\\jsp\\Project02\\WebContent\\upload\\";
			
			String orglmg = filePath + uploadFileName;//원본파일
			thumbFile = "thumb_" + uploadFileName;//썸네일 파일이름
			String thumblmg = filePath+thumbFile;//파일경로+파일명
			
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
		
		
		
		
		ListBean bean = new ListBean();
		
		bean.setId(request.getParameter("id"));
		bean.setSubject(request.getParameter("subject"));
		bean.setComment(request.getParameter("comment"));
		bean.setSigndate(ldate);
		bean.setFile(uploadFileName);
		//2020.12.03추가
		bean.setFile_s(thumbFile);
		
		ListDAO dao = new ListDAO();
		dao.insertList(bean);
		
		response.sendRedirect("/list/List.do");
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
