package Controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.ItemDAO;
import DAO.MemberDAO;
import Model.ItemBean;


@WebServlet("/admin/item/cart_delete.do")
public class CartDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public CartDelete() {
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
		
		bean.setCartUid(Integer.parseInt(request.getParameter("uid")));
		
		ItemDAO dao = new ItemDAO();
		dao.deleteCart(bean);
		
		response.sendRedirect("/admin/item/cartlist.do?id="+request.getParameter("id")+"");
	}

}
