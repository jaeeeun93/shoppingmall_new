package Controller;

import java.awt.Image;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

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

import DAO.CouponDAO;
import DAO.ItemDAO;
import Model.CouponBean;
import Model.ItemBean;

@WebServlet("/admin/coupon/couponMake.do")
public class CouponMake extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CouponMake() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doAll(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doAll(request, response);
	}

	protected void doAll(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");

		HttpSession session = request.getSession();
		String session_id = (String)session.getAttribute("id");
		String coupon = request.getParameter("coupon");
		int count = 0;
		String signdate = "";
		String validate = "";
		
		java.util.Date today = new java.util.Date();
		SimpleDateFormat cal = new SimpleDateFormat("yyyyMMddhhmmss");
		signdate = cal.format(today);

		Calendar cal2 = Calendar.getInstance();
		cal2.add(Calendar.MONTH, 1);
		
		validate = cal.format(cal2.getTime());
		
		
		CouponDAO dao = new CouponDAO();
		CouponBean bean = new CouponBean();
		
		count = dao.couponCount(session_id, coupon);

		if (count == 0) {
		
		dao.makeCoupon(coupon,session_id, signdate, validate);
		
		RequestDispatcher dis = request.getRequestDispatcher("/");
		dis.forward(request, response);
		}else{
			response.setContentType("text/html; charset=UTF-8");
			
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('쿠폰 발급 실패 : 이미 가지고 있는 쿠폰입니다.');");
			out.println("history.back();");
			out.println("</script>");
		}
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
