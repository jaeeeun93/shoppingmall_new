package AdminController;

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

import DAO.ItemDAO;
import Model.ItemBean;

@WebServlet("/admin/item/update.do")
@MultipartConfig(fileSizeThreshold = 0, location = "C:\\jsp\\Project02\\WebContent\\upload")
public class ItemUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ItemUpdate() {
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
		
		String itemFile = request.getParameter("itemFile");
		String thum_ItemFile = "";
		String signdate = "";
	
		// 파일 업로드 부분
		String uploadItemFile = "";

		Part part = request.getPart("itemFile");

		if (part.getSize() > 0) {
			String contentDisposition = part.getHeader("content-disposition");
			uploadItemFile = getUploadItemFile(contentDisposition);

			// 오늘 날짜 구하기

			Date today = new Date();
			SimpleDateFormat cal = new SimpleDateFormat("yyyyMMddhhmmss");
			signdate = cal.format(today);

			// 중복 파일 피하기 위한 처리
			uploadItemFile = signdate + "_" + uploadItemFile;

			part.write(uploadItemFile); // 경로에 파일저장 처리부분

			// 썸네일
			String filePath = "C:\\JSP\\Pro002\\WebContent\\upload\\"; // 여기다가 \\ 붙이던지 밑에 file1 앞에 \\붙이던지

			String orgImg = filePath + uploadItemFile; // 원본 파일
			thum_ItemFile = "thumb_" + uploadItemFile; // 썸네일 파일명
			String thumbImg = filePath + thum_ItemFile; // 파일경로 + 파일명

			int thumbWidth = 60;
			int thumbHeight = 60;

			try {

				Image thumbnail = JimiUtils.getThumbnail(orgImg, thumbWidth, thumbHeight, Jimi.IN_MEMORY);// 썸네일 설정
				Jimi.putImage(thumbnail, thumbImg);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		ItemBean bean = new ItemBean();

		bean.setItemCode(request.getParameter("itemCode"));
		bean.setItemName(request.getParameter("itemName"));
		bean.setItemFile(uploadItemFile);
		bean.setItemFile_s(thum_ItemFile);
		bean.setItemPrice(Integer.parseInt(request.getParameter("itemPrice")));
		bean.setItemPoint(Integer.parseInt(request.getParameter("itemPoint")));
		bean.setStock(Integer.parseInt(request.getParameter("stock")));
		bean.setCategory(request.getParameter("category"));
		bean.setItemUid(Integer.parseInt(request.getParameter("itemUid")));
		
		ItemDAO dao = new ItemDAO();
		dao.updateItem(bean);

		response.sendRedirect("/admin/item/adminItemList.do");
	}

	private String getUploadItemFile(String contentDisposition) {
		String uploadItemFile = null;
		String[] contentSplitStr = contentDisposition.split(";");

		// 사용자 브라우저가 크롬 계열일 경우
		int firstQutosIndex = contentSplitStr[2].indexOf("\"");
		int lastQutosIndex = contentSplitStr[2].lastIndexOf("\"");
		uploadItemFile = contentSplitStr[2].substring(firstQutosIndex + 1, lastQutosIndex);

		return uploadItemFile;
	}
	
	
}
