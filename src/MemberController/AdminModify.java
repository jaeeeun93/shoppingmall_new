package MemberController;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.MemberDAO;
import Model.MemberBean;

@WebServlet("/admin/member/modify.do")
public class AdminModify extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AdminModify() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAll(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAll(request,response);
	}
	
	protected void doAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String id = request.getParameter("id");
		
		if(id == null) {
			request.setAttribute("msg", "수정할놈이없다");
			request.setAttribute("url", "/admin/member/list.do");
			
			RequestDispatcher dis = request.getRequestDispatcher("/error.jsp");
			dis.forward(request, response);
		}
		
		MemberBean bean = new MemberBean();
		MemberDAO dao = new MemberDAO();
		
		bean = dao.adminviewMember(id);
		
		request.setAttribute("modify", bean);
		RequestDispatcher dis = request.getRequestDispatcher("admin_member_modify.jsp");
		dis.forward(request, response);
	}

}
