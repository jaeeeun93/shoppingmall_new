package AdminController;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.ItemDAO;
import Model.ItemBean;

@WebServlet("/admin/item/Modify.do")
public class ItemModify extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ItemModify() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAll(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAll(request, response);
	}
	
	protected void doAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		int uid = Integer.parseInt(request.getParameter("uid"));
		
		ItemDAO dao = new ItemDAO();
		ItemBean bean = new ItemBean();
		
		bean = dao.itemModify(uid);
		
		request.setAttribute("itemup", bean);
		RequestDispatcher dis = request.getRequestDispatcher("/admin/item/itemModify.jsp");
		dis.forward(request, response);
	}

	
}
