package ReviewController;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.ReviewDAO;
import Model.ReviewBean;

@WebServlet("/campReview/View.do")
public class View extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public View() {
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
		
		int uid=Integer.parseInt(request.getParameter("uid"));
		
		ReviewBean bean = new ReviewBean();
		ReviewDAO dao = new ReviewDAO();
		
		bean=dao.reviewView(uid);
		
		//조회수증가
		dao.reviewref(uid);
		bean.setUid(Integer.parseInt(request.getParameter("uid")));
	
		request.setAttribute("view", bean);
		RequestDispatcher dis = request.getRequestDispatcher("/campReview/View.jsp");
		dis.forward(request, response);
	}
}
