package Controller;

import java.awt.Image;
import java.io.IOException;
import java.text.SimpleDateFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.sun.jimi.core.Jimi;
import com.sun.jimi.core.JimiUtils;

import DAO.ItemDAO;
import Model.ItemBean;


@WebServlet("/admin/item/cart_insert.do")
@MultipartConfig(
		fileSizeThreshold=0,
		location = "C:\\jsp\\Project02\\WebContent\\upload"
		)
	public class CartInsert extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CartInsert() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAll(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAll(request,response);
	}
	
	protected void doAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
					
		ItemBean bean = new ItemBean();
		
		bean.setItemCode(request.getParameter("itemCode"));
		bean.setItemName(request.getParameter("itemName"));
		bean.setItemPrice(Integer.parseInt(request.getParameter("itemPrice")));
		bean.setItemPoint(Integer.parseInt(request.getParameter("itemPoint")));
		bean.setStock(Integer.parseInt(request.getParameter("stock")));
		bean.setId(request.getParameter("id"));
		bean.setCartFile_s(request.getParameter("cartFile_s"));
		bean.setOrderId(request.getParameter("session"));
		
		ItemDAO dao = new ItemDAO();
		dao.insertCart(bean);
		
		response.sendRedirect("cartlist.do?id="+request.getParameter("id")+"");
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