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
import DAO.MemberDAO;
import DAO.OrderDAO;
import Model.CouponBean;
import Model.ItemBean;
import Model.MemberBean;
import Model.OrderBean;

@WebServlet("/admin/item/orderInfo.do")
public class OrderInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public OrderInfo() {
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
		
		HttpSession session = request.getSession();
		String session_id = (String)session.getAttribute("id");
		String signdate = "";
		String itemName = request.getParameter("itemName");
		String itemCode = request.getParameter("itemCode");
		String itemPoint = request.getParameter("itemPoint");
		String cartFile_s = request.getParameter("cartFile_s");	
		String orderId = request.getParameter("session");
		int itemPrice = Integer.parseInt(request.getParameter("itemPrice"));
		
		
		java.util.Date today = new java.util.Date();
		SimpleDateFormat cal = new SimpleDateFormat("yyyyMMddhhmmss");
		signdate = cal.format(today);
		
		if(session_id == null) {
			request.setAttribute("msg", "비회원은 주문이 불가합니다");
			request.setAttribute("url", "/member/login.jsp");
			
			RequestDispatcher dis = request.getRequestDispatcher("/error.jsp");
			dis.forward(request, response);
		}
		
		MemberBean bean = new MemberBean();
		MemberDAO dao = new MemberDAO();
		
		ItemBean bean2 = new ItemBean();
		ItemDAO dao2 = new ItemDAO();
		
		OrderDAO dao3 = new OrderDAO();
		OrderBean bean3 = new OrderBean();
		
		CouponDAO dao4 = new CouponDAO();
		CouponBean bean4 = new CouponBean();
		
		Vector<CouponBean> v = new Vector<CouponBean>();
		v = dao4.viewCoupon(session_id);
		
		
		bean3.setOrderDate(signdate);
		
		bean = dao.viewMember(session_id);
	
		bean2.setId(session_id);
		
		dao3.insertTempOrder(bean2, itemName, cartFile_s, itemPrice, session_id, bean3, itemCode, orderId);
		
		request.setAttribute("customer", bean);
		request.setAttribute("price", itemPrice);
		request.setAttribute("orderId", orderId);
		request.setAttribute("coupon", v);
		
		request.setAttribute("itemName", itemName);
		request.setAttribute("itemCode", itemCode);
		request.setAttribute("itemPoint", itemPoint);
		request.setAttribute("cartFile_s", cartFile_s);
		
		
		RequestDispatcher dis = request.getRequestDispatcher("/order/orderInfo.jsp");
		dis.forward(request, response);
	}

}
