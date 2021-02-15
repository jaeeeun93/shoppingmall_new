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

@WebServlet("/member/PasswordSearch.do")
public class PasswordSearch extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	public PasswordSearch() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAll(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAll(request, response);
	}
	protected void doAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String id= request.getParameter("id");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		
		MemberBean bean = new MemberBean();
		
		bean.setId(id);
		bean.setEmail(email);
		bean.setPhone(phone);
		
		MemberDAO dao = new MemberDAO();
		
		int pass2 = dao.passSearchNum(bean);
		String pass = dao.passSearch(bean);
		
		if(pass2==0) {
			request.setAttribute("msg", "비밀번호를 찾을 수 없습니다.");
			request.setAttribute("url", "passSearch.jsp");
			
			RequestDispatcher dis= request.getRequestDispatcher("/error.jsp");
			dis.forward(request, response);
		}else {
			request.setAttribute("pass", pass);
			
			RequestDispatcher dis1 = request.getRequestDispatcher("pass_ok.jsp");
			dis1.forward(request, response);
		}
	}

}
