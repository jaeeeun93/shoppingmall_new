//2020.11.27 만듦!
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

@WebServlet("/list/ListModify.do")
public class ListModify extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ListModify() {
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
		
		int uid= Integer.parseInt(request.getParameter("uid"));
		
		ListDAO dao = new ListDAO();
		ListBean bean = new ListBean();
		
		bean= dao.listVeiw(uid);
		
		request.setAttribute("view", bean);
		RequestDispatcher dis = request.getRequestDispatcher("/list/listModify.jsp");
		dis.forward(request, response);
	}

}
