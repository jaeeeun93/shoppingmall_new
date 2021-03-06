package Controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

@WebServlet("/order/OrderBuy.do")
public class OrderBuy extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public OrderBuy() {
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
		
		int price = 0;
		String signdate = "";
		String coupon = request.getParameter("coupon");
		String itemPoint = request.getParameter("itemPoint");
		
		OrderBean bean = new OrderBean();
		OrderDAO dao = new OrderDAO();
		
		ItemDAO dao2 = new ItemDAO();
		ItemBean bean2 = new ItemBean();
		
		CouponDAO dao3 = new CouponDAO();
		CouponBean bean3 = new CouponBean();
		
		MemberDAO dao4 = new MemberDAO();
		MemberBean bean4 = new MemberBean();
		
		java.util.Date today = new java.util.Date();
		SimpleDateFormat cal = new SimpleDateFormat("yyyyMMddhhmmss");
		signdate = cal.format(today);
		
		bean.setId(request.getParameter("id"));
		bean.setName(request.getParameter("name"));
		bean.setEmail(request.getParameter("email"));
		bean.setPhone(request.getParameter("phone"));
		bean.setCname(request.getParameter("cname"));
		bean.setZipcode(request.getParameter("zipcode"));
		bean.setZip1(request.getParameter("zip1"));
		bean.setZip2(request.getParameter("zip2"));
		bean.setZip3(request.getParameter("zip3"));
		bean.setCphone(request.getParameter("cphone"));
		bean.setPick(request.getParameter("pick"));
		bean.setTotalPrice(Integer.parseInt(request.getParameter("totalPrice")));
		bean.setPoint(request.getParameter("itemPoint"));
		bean.setOrderId(request.getParameter("orderId"));
		bean.setOrderDate(signdate);
		bean.setCoupon(coupon);
		
		ArrayList<ItemBean> list = new ArrayList<ItemBean>();
		
		list = dao2.viewItemCode(session_id);
		
		
		int total = list.size();
		
		String b = request.getParameter("itemCode");

		dao.minusStock(bean, b);
		dao3.minusCoupon(coupon,session_id, signdate);
		
		Vector<ItemBean> v = new Vector<ItemBean>();
		
		dao.insertPoint(session_id, itemPoint);
		dao.insertOrderBuy(bean);
		
		/* request.setAttribute("v", v); */
	
		/*
		 * RequestDispatcher dis =
		 * request.getRequestDispatcher("/order/orderSuccess.jsp");
		 */
		
		request.setAttribute("msg", "주문 완료");
		request.setAttribute("url", "/order/orderSuccess.jsp");
		
		session.removeAttribute("session_guest");
		
		RequestDispatcher dis = request.getRequestDispatcher("/order/orderlist.jsp");
		dis.forward(request, response);
		
	
	}
}
