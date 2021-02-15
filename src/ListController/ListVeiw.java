package ListController;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.ListDAO;
import Model.ListBean;

@WebServlet("/list/View.do")
public class ListVeiw extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ListVeiw() {
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
		
		int uid = Integer.parseInt(request.getParameter("uid"));
		
		ListBean bean = new ListBean();
		ListDAO dao = new ListDAO();
		
		bean= dao.listVeiw(uid);
		
		//ref증가
		dao.listref(uid);
		bean.setUid(Integer.parseInt(request.getParameter("uid")));
		
		request.setAttribute("view", bean);
		RequestDispatcher dis = request.getRequestDispatcher("/list/listView.jsp");
		dis.forward(request, response);
		
	}
}
