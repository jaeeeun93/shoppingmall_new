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


@WebServlet("/review/reviewForm.do")
public class ItemReviewForm extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public ItemReviewForm() {
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
		String itemCode = request.getParameter("code");
		
		OrderBean bean = new OrderBean();
		OrderDAO dao = new OrderDAO();
		
		Vector<OrderBean> v = new Vector<OrderBean>();
		
		v = dao.viewReviewItem(session_id,bean, itemCode);
	
		request.setAttribute("p", v);
		request.setAttribute("code", itemCode);
		
		RequestDispatcher dis = request.getRequestDispatcher("/review/reviewForm.jsp");
		dis.forward(request, response);
		
	}
}
