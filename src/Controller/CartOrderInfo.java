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

@WebServlet("/admin/item/cartOrderInfo.do")
public class CartOrderInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CartOrderInfo() {
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
		
		java.util.Date today = new java.util.Date();
		SimpleDateFormat cal = new SimpleDateFormat("yyyyMMddhhmmss");
		signdate = cal.format(today);
		
		int price=0;
		int point=0;
		
		String orderId=null;
		
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
		
		
		bean = dao.viewMember(session_id);
		bean2.setId(session_id);
		bean3.setOrderDate(signdate);
		orderId = dao2.getCartOrderId(bean2);
		
		Vector<ItemBean> v = new Vector<ItemBean>();
		Vector<ItemBean> v2 = new Vector<ItemBean>();
		Vector<ItemBean> v3 = new Vector<ItemBean>();
		
		Vector<CouponBean> v4 = new Vector<CouponBean>();
		
		ArrayList<ItemBean> list = new ArrayList<ItemBean>();
		
		v4 = dao4.viewCoupon(session_id);
		
		String str = request.getParameter("str");

		String[] array = str.split("-");
		String b = null;
		
		for(String s : array) {
			v2 = dao2.viewCheckCartOrder(s);
			v.addAll(v2);
			for(ItemBean se : v2) {
				String itemName = se.getItemName();
				String cartFile_s = se.getCartFile_s();
				String itemCode = se.getItemCode();
				int itemPrice = se.getItemPrice();
				dao3.insertTempOrder(bean2, itemName, cartFile_s, itemPrice, session_id, bean3, itemCode, orderId);
			}
		}
		
		for(String s : array) {
			price += dao2.getCheckCartPrice(s);
			point += dao2.getCheckCartPoint(s);
		}
		
		
		
		request.setAttribute("v", v);
		request.setAttribute("customer", bean);
		request.setAttribute("coupon", v4);
		request.setAttribute("price", price);
		request.setAttribute("point", point);
		request.setAttribute("orderId", orderId);
		
		RequestDispatcher dis = request.getRequestDispatcher("/order/cartOrderInfo.jsp");
		dis.forward(request, response);
	}
}