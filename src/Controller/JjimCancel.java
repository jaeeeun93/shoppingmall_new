package Controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.ItemDAO;
import Model.ItemBean;


@WebServlet("/jjimCancel.do")
public class JjimCancel extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public JjimCancel() {
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
		
		ItemBean bean = new ItemBean();
		ItemDAO dao = new ItemDAO();

		String id = request.getParameter("id");
		String jjim = request.getParameter("jjim");
		String flag = request.getParameter("flag");
		
		int itemUid = Integer.parseInt(request.getParameter("itemUid"));
		
		dao.jjimN(id,itemUid);
		
		if (flag.equals("1")) {
			RequestDispatcher dis = request.getRequestDispatcher("/jjimList.do?id="+session_id+"");
			dis.forward(request, response);
		}else {
		
			RequestDispatcher dis = request.getRequestDispatcher("/admin/item/list.do");
			dis.forward(request, response);
		}
	}
}
