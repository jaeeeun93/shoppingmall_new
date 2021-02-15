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

import DAO.OrderDAO;
import Model.OrderBean;


@WebServlet("/review/reviewList.do")
public class ItemReviewList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public ItemReviewList() {
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
		
		OrderBean bean = new OrderBean();
		OrderDAO dao = new OrderDAO();
		
		Vector<OrderBean> v = new Vector<OrderBean>();
		Vector<OrderBean> v2 = new Vector<OrderBean>();
		
		v = dao.viewOrderList(session_id,bean);
		
		v2 = dao.viewTempOrder2(session_id,bean);
	
	
		request.setAttribute("v", v);
		request.setAttribute("p", v2);
		
		RequestDispatcher dis = request.getRequestDispatcher("/review/reviewList.jsp");
		dis.forward(request, response);
		
	}
}
