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
import Model.ItemBean;


@WebServlet("/jjimList.do")
public class JjimList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public JjimList() {
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
		
		HttpSession session = request.getSession();
		String session_id = (String)session.getAttribute("id");
		int count = 0;
		int totalPrice = 0;
		
		ItemBean bean = new ItemBean();
		ItemDAO dao = new ItemDAO();
		
		Vector<ItemBean> v = new Vector<ItemBean>();
		Vector<ItemBean> v2 = new Vector<ItemBean>();
		Vector<ItemBean> v3 = new Vector<ItemBean>();
		
		v = dao.viewJjimList(session_id);
		count = dao.getJjimCount(session_id);
		
		for (ItemBean a : v) {
			int itemUid = a.getItemUid();
			v2 = dao.getJjimItem(itemUid);
			
			v3.addAll(v2);
			totalPrice += dao.getJjimPrice(itemUid);
		}
	
		
		
		request.setAttribute("v", v);
		request.setAttribute("v2", v2);
		request.setAttribute("v3", v3);
		request.setAttribute("count", count);
		request.setAttribute("totalPrice", totalPrice);
		
		RequestDispatcher dis = request.getRequestDispatcher("/admin/item/jjimList.jsp?id="+request.getParameter("id")+"");
		dis.forward(request, response);
		
	}

}
