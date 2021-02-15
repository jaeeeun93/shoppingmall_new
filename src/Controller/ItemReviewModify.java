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


@WebServlet("/review/reviewModify.do")
public class ItemReviewModify extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public ItemReviewModify() {
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
		String session_id = request.getParameter("id");
		int reviewUid = Integer.parseInt(request.getParameter("uid"));
		double starPoint = Double.parseDouble(request.getParameter("starPoint"));
		
		ItemReviewBean bean = new ItemReviewBean();
		ItemReviewDAO dao = new ItemReviewDAO();
		
		Vector<ItemReviewBean> v = new Vector<ItemReviewBean>();
		
		bean.setReviewUid(Integer.parseInt(request.getParameter("uid")));
		bean.setItemCode(request.getParameter("code"));
		bean.setStarPoint(Double.parseDouble(request.getParameter("starPoint")));
		
		bean = dao.viewWroteReview(reviewUid);
		request.setAttribute("review", bean);
		request.setAttribute("reviewUid", reviewUid);
		request.setAttribute("starPoint", starPoint);
		
		RequestDispatcher dis = request.getRequestDispatcher("/review/reviewModify.jsp");
		dis.forward(request, response);
		
	}
}
