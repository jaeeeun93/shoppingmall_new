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

import DAO.ItemReviewDAO;
import Model.OrderBean;
import Model.ItemReviewBean;


@WebServlet("/review/wroteReview.do")
public class ItemWroteReview extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public ItemWroteReview() {
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
		
		ItemReviewBean bean = new ItemReviewBean();
		ItemReviewDAO dao = new ItemReviewDAO();
		
		Vector<ItemReviewBean> v = new Vector<ItemReviewBean>();
		
		v = dao.viewWroteReview(session_id,bean);
		request.setAttribute("v", v);
		
		RequestDispatcher dis = request.getRequestDispatcher("/review/wroteReview.jsp");
		dis.forward(request, response);
		
	}
}
