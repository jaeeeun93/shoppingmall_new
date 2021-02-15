package AdminController;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.ItemDAO;

@WebServlet("/admin/item/itemDelete.do")
public class ItemDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ItemDelete() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAll(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAll(request, response);
	}
	
	protected void doAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ItemDAO dao = new ItemDAO();
		
		int uid = Integer.parseInt(request.getParameter("uid"));
		
		dao.itemDelete(uid);
		
		response.sendRedirect("/admin/item/adminItemList.do");
	}
	
	
}
