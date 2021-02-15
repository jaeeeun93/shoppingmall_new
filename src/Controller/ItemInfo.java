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
import DAO.ItemReviewDAO;
import Model.ItemBean;
import Model.ItemReviewBean;

@WebServlet("/admin/item/info.do")
public class ItemInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ItemInfo() {
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
		
		String code = request.getParameter("code");
		
		HttpSession session = request.getSession();
		String session_id =(String)session.getAttribute("id");
		
		ItemBean bean = new ItemBean();
		ItemDAO dao = new ItemDAO();
		
		ItemReviewBean bean2 = new ItemReviewBean();
		ItemReviewDAO dao2 = new ItemReviewDAO();
		
		bean = dao.viewItem(code);
		dao.checkViewItem(code);
		int count = dao2.countReview(code);
		double avg = dao2.avgReview(code);
		
		Vector<ItemReviewBean> v = new Vector<ItemReviewBean>();
		
		v = dao2.viewItemReview(code,bean2);
		
		request.setAttribute("v", v);
		request.setAttribute("info", bean);
		request.setAttribute("count", count);
		request.setAttribute("avg", avg);
		
		RequestDispatcher dis = request.getRequestDispatcher("itemInfo.jsp");
		dis.forward(request, response);
	}

}
