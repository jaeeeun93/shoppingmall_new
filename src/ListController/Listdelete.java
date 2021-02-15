package ListController;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.ListDAO;

@WebServlet("/list/Listdelete.do")
public class Listdelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Listdelete() {
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
		
		dao.listDelete(uid);
		
		response.sendRedirect("/list/List.do");//삭제하고 list페이지로
	}
}
