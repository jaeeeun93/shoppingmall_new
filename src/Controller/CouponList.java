package Controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.CouponDAO;
import DAO.ItemDAO;
import Model.CouponBean;
import Model.ItemBean;


@WebServlet("/couponList.do")
public class CouponList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public CouponList() {
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
		
		HttpSession session = request.getSession();
		String session_id = (String)session.getAttribute("id");
		int count = 0;
		String signdate = null;
		
		CouponDAO dao = new CouponDAO();
		CouponBean bean = new CouponBean();
		
		java.util.Date today = new java.util.Date();
		SimpleDateFormat cal = new SimpleDateFormat("yyyyMMddhhmmss");
		signdate = cal.format(today);
		
		Vector<CouponBean> v = new Vector<CouponBean>();
		count = dao.couponCount2(session_id);
		
		v = dao.viewCouponList(session_id);
	
		request.setAttribute("v", v);
		request.setAttribute("count", count);
		request.setAttribute("date", signdate);
		
		RequestDispatcher dis = request.getRequestDispatcher("/admin/coupon/mycoupon.jsp?id="+request.getParameter("id")+"");
		dis.forward(request, response);
		
	}

}
