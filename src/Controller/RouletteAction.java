package Controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.CouponDAO;
import DAO.MemberDAO;
import Model.CouponBean;
import Model.MemberBean;


@WebServlet("/RouletteAction.do")
public class RouletteAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public RouletteAction() {
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
		String coupon = request.getParameter("point");
		String signdate = "";
		String validate = "";
		
		java.util.Date today = new java.util.Date();
		SimpleDateFormat cal = new SimpleDateFormat("yyyyMMddhhmmss");
		signdate = cal.format(today);
		
		Calendar cal2 = Calendar.getInstance();
		cal2.add(Calendar.MONTH, 1);
		
		validate = cal.format(cal2.getTime());
		
		MemberBean bean = new MemberBean();
		MemberDAO dao = new MemberDAO();
		
		CouponBean bean2 = new CouponBean();
		CouponDAO dao2 = new CouponDAO();
		
		dao.minusPoint(session_id);
		dao2.makeCoupon(coupon,session_id, signdate, validate);
		
		RequestDispatcher dis = request.getRequestDispatcher("Roulette.do");
		dis.forward(request, response);
	}
}
