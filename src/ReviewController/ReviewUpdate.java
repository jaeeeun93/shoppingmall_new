package ReviewController;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.ReviewDAO;
import Model.ReviewBean;

@WebServlet("/campReview/ReviewUpdate.do")
public class ReviewUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ReviewUpdate() {
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
		
		ReviewBean bean = new ReviewBean();
		
		bean.setSubject(request.getParameter("subject"));
		bean.setComment(request.getParameter("comment"));
		bean.setUid(Integer.parseInt(request.getParameter("uid")));
		
		ReviewDAO dao = new ReviewDAO();
		
		dao.reviewupdate(bean);
		
		response.sendRedirect("/campReview/View.do?uid="+bean.getUid()+"");
	}
}
