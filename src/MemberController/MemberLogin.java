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

@WebServlet("/member/login_ok.do")
public class MemberLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public MemberLogin() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAll(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAll(request,response);
	}
	
	protected void doAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("userid");
		String pass = request.getParameter("userpw");
		String level = request.getParameter("level");
		
		int total_record = 0;//매칭 회원수

		int cnt = 0;//헷갈리수 있으니까 같은변수 선언
		
		MemberDAO dao = new MemberDAO();
		
		//탈퇴 회원 로그인 방지
		cnt = dao.withdrawId(id,pass);//withdraw를 실행하라는 문구 거기서 실행해서 return한 값을 cnt에 담음
		
		//매칭회원 존재여부
		total_record = dao.loginMember(id,pass);
		
		MemberBean bean = new MemberBean();
		
		if(total_record == 1) {//로그인 가능
			bean = dao.loginSelect(id,pass);
			
			//세선처리
			HttpSession session = request.getSession();
			
			session.setAttribute("id", bean.getId());//rs돌려서 bean.에 넣은 값을 가져와서 id에 담는다. 
			session.setAttribute("name", bean.getName());
			session.setAttribute("level", bean.getLevel());
			
			response.sendRedirect("/");
			
		}else if(total_record == 0 && cnt == 1) {
			request.setAttribute("msg", "탈퇴한 회원입니다.");
			
			request.setAttribute("url", "/");
			
			RequestDispatcher dis1 = request.getRequestDispatcher("/error.jsp");
			dis1.forward(request,response);
		}else if(total_record == 0) {
			request.setAttribute("msg", "아이디 또는 패스워드가 일치하지 않습니다.");
			request.setAttribute("url", "login.jsp");
			
			RequestDispatcher dis = request.getRequestDispatcher("/error.jsp");
			dis.forward(request, response);
		}else{
			
		}
	}
}
