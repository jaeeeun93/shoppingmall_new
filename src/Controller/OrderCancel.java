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
import java.util.Date;

import DAO.ItemDAO;
import DAO.OrderDAO;
import Model.ItemBean;
import Model.OrderBean;

@WebServlet("/order/OrderCancel.do")
public class OrderCancel extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public OrderCancel() {
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
		
		OrderDAO dao = new OrderDAO();
		OrderBean bean = new OrderBean();
		
		dao.deleteTempOrder(orderId);
		
		RequestDispatcher dis = request.getRequestDispatcher("/admin/item/list.do");
		dis.forward(request, response);
	
	}
}
