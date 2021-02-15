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

@WebServlet("/member/member_modify.do")
public class MemberModify extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MemberModify() {
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
		
		HttpSession session = request.getSession();
		String session_id = (String)session.getAttribute("id");
		
		if(session_id == null) {
			request.setAttribute("msg", "옳지 않은 경로입니다. 저리가!");
			request.setAttribute("url", "login.jsp");
			
			RequestDispatcher dis = request.getRequestDispatcher("/error.jsp");
			dis.forward(request, response);
		}
		
		MemberBean bean = new MemberBean();
		MemberDAO dao = new MemberDAO();
		
		bean = dao.viewMember(session_id);//여기서 받는 게 int 인지 string 인지만 구별해서 dao에 적으면 됨
		
		request.setAttribute("modify", bean);
		RequestDispatcher dis = request.getRequestDispatcher("member_modify.jsp");
		dis.forward(request, response);
	}
}
