package AdminController;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.ItemDAO;

@WebServlet("/admin/item/soldout.do")
public class ItemSoldOut extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ItemSoldOut() {
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
		
		dao.itemSoldOut(uid);
		
		response.sendRedirect("/admin/item/adminItemList.do");
	}

}
