package Controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.ItemDAO;
import Model.ItemBean;


@WebServlet("/jjim.do")
public class Jjim extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public Jjim() {
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
		
		ItemBean bean = new ItemBean();
		ItemDAO dao = new ItemDAO();

		String id = request.getParameter("id");
		String search = request.getParameter("search");
		int jjim = Integer.parseInt(request.getParameter("jjim"));
		int itemUid = Integer.parseInt(request.getParameter("itemUid"));
		
		dao.jjimY(id,itemUid,jjim);
		
		
		RequestDispatcher dis = request.getRequestDispatcher("/admin/item/list.do");
		dis.forward(request, response);
	}
}
