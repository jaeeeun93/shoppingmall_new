package Controller;

import java.io.IOException;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.ItemDAO;
import DAO.MemberDAO;
import DAO.OrderDAO;
import Model.ItemBean;
import Model.MemberBean;
import Model.OrderBean;


@WebServlet("/order/orderDetail.do")
public class OrderDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public OrderDetail() {
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
		String orderId = request.getParameter("orderId");
		
		
		MemberBean bean = new MemberBean();
		MemberDAO dao = new MemberDAO();
		
		bean = dao.viewMember(session_id);
		
		ItemBean bean2 = new ItemBean();
		ItemDAO dao2 = new ItemDAO();
		
		OrderDAO dao3 = new OrderDAO();
		OrderBean bean3 = new OrderBean();
		
		Vector<OrderBean> v = new Vector<OrderBean>();
		bean3 = dao3.viewOrderDetail(orderId);
		
		v = dao3.ViewTempOrder2(orderId,bean3);
		
		request.setAttribute("customer", bean);
		request.setAttribute("d", bean3);
		request.setAttribute("v", v);
		
		RequestDispatcher dis = request.getRequestDispatcher("/order/orderDetail.jsp");
		dis.forward(request, response);
	}

}
