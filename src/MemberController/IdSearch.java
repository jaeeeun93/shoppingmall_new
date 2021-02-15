package MemberController;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.MemberDAO;
import Model.MemberBean;

@WebServlet("/member/IdSearch.do")
public class IdSearch extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public IdSearch() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAll(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAll(request, response);
	}
	protected void doAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pass= request.getParameter("pass");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		
		MemberBean bean= new MemberBean();
		
		bean.setPass(pass);
		bean.setEmail(email);
		bean.setPhone(phone);
		
		MemberDAO dao= new MemberDAO();
		
		int num=dao.idsearchNum(bean);
		
		String id= dao.idsearch(bean);
		
		if(num== 0) {
			request.setAttribute("msg", "아이디를 찾을 수 없습니다.");
			request.setAttribute("url", "idSearch.jsp");

			RequestDispatcher dis = request.getRequestDispatcher("/error.jsp");
			dis.forward(request, response);
		}else {
			request.setAttribute("id", id);
			RequestDispatcher dis = request.getRequestDispatcher("id_ok.jsp");
			dis.forward(request, response);
		}
	}
}