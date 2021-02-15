package MemberController;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.MemberDAO;
import Model.MemberBean;

@WebServlet("/member/Delete.do")
public class Delete extends HttpServlet {
	private static final long serialVersionUID = 1L;

	  public Delete() {
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
			
			String id = request.getParameter("id");//탈퇴시 input 입력값 가져옴
			String pass = request.getParameter("pass");
			
			MemberBean bean = new MemberBean();
			MemberDAO dao = new MemberDAO();
			
			dao.deleteMember(id,pass);//dao에 있는 deletemember bean객체씀
			
			//로그아웃 2020.11.27 수정
			HttpSession session = request.getSession();
			session.invalidate();
		
			response.sendRedirect("/");
		}
	}