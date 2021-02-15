package TradeController;

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

import DAO.TradeDAO;
import Model.TradeBean;

@WebServlet("/trade/tradeUpdate.do")
@MultipartConfig(
		fileSizeThreshold = 0,
		location = "C:\\jsp\\Project02\\WebContent\\upload"
)
public class TradeUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public TradeUpdate() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAll(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAll(request, response);
	}

	
	protected void doAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		Date date = new Date();
		SimpleDateFormat cal1 = new SimpleDateFormat("yyyy-MM-dd-hh:mm:ss");
		String reWrite = cal1.format(date);
		
		String tradeUpThumb = "";
		String file1 = "";
		String file2 = "";
		String file3 = "";
		String file4 = "";
		
		for(Part part: request.getParts()){
			if((part.getName().equals("file1") || part.getName().equals("file2") || 
				part.getName().equals("file3") || part.getName().equals("file4")) && part.getSize()>0){
				
				String contentDisposition = part.getHeader("content-disposition");
				System.out.println("contentDisposition = " + contentDisposition);
				String uploadFileName = getUploadFileName(contentDisposition);

				//오늘 날짜 구하기
				Date today = new Date();
				SimpleDateFormat cal = new SimpleDateFormat("yyyyMMddhhmmss");
				String signdate = cal.format(today);

				uploadFileName = signdate+"_"+uploadFileName;
				part.write(uploadFileName);

				if(part.getName().equals("file1")) {
					file1 = uploadFileName;
				}

				if(part.getName().equals("file2")) {
					file2 = uploadFileName;
				}
				
				if(part.getName().equals("file3")) {
					file3 = uploadFileName;
				}
				
				if(part.getName().equals("file4")) {
					file4 = uploadFileName;
				}
				
				//썸네일
				String filePath = "C:\\jsp\\Project02\\WebContent\\upload\\";
				
				String orgImg = filePath + file1;
				tradeUpThumb = "thumb_" + file1;
				String thumbImg = filePath + tradeUpThumb;
				
				int thumbWidth = 300; 
				int thumbHeight = 300;
				
				try {
					
				Image thumbnail = JimiUtils.getThumbnail(orgImg, thumbWidth, thumbHeight, Jimi.IN_MEMORY);
				Jimi.putImage(thumbnail, thumbImg);
				
				}catch(Exception e){
					e.printStackTrace();
				}
				

			}

		}
		
		TradeBean bean = new TradeBean();
		
		bean.setSubject(request.getParameter("subject"));
		bean.setComment(request.getParameter("comment"));
		bean.setPrice(request.getParameter("price"));
		bean.setDeal(request.getParameter("deal"));
		bean.setUid(Integer.parseInt(request.getParameter("uid")));
		bean.setSigndate(reWrite);
		bean.setFile(file1);
		bean.setFile2(file2);
		bean.setFile3(file3);
		bean.setFile4(file4);
		bean.setFile_s(tradeUpThumb);
		
		TradeDAO dao = new TradeDAO();
		dao.tradeUpdate(bean);
		
		response.sendRedirect("/trade/List.do");
		
		
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
