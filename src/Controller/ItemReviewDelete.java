package Controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.ItemDAO;
import DAO.ItemReviewDAO;
import Model.ItemReviewBean;


@WebServlet("/review/reviewDelete.do")
public class ItemReviewDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public ItemReviewDelete() {
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
		
		String code = request.getParameter("code");
		
		
		ItemReviewBean bean = new ItemReviewBean();
		ItemReviewDAO dao = new ItemReviewDAO();
		
		bean.setReviewUid(Integer.parseInt(request.getParameter("uid")));
		
		double minus = Double.parseDouble(request.getParameter("starPoint"));
		ItemDAO dao3 = new ItemDAO();
		dao3.minusStarPoint(bean, code, minus);
		
		dao.deleteReview(bean);
		
		response.sendRedirect("/review/wroteReview.do?id="+request.getParameter("id")+"");
	}
}
