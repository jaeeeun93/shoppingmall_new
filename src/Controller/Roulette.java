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

import DAO.CouponDAO;
import DAO.MemberDAO;
import Model.CouponBean;
import Model.MemberBean;


@WebServlet("/Roulette.do")
public class Roulette extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public Roulette() {
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
	  
		if(session_id == null) {
			request.setAttribute("msg", "비회원은 이용하실 수 없습니다.");
			request.setAttribute("url", "login.jsp");
	  
			RequestDispatcher dis = request.getRequestDispatcher("/error.jsp");
			dis.forward(request, response);
			}
		 
		
		MemberBean bean = new MemberBean();
		MemberDAO dao = new MemberDAO();
		CouponBean bean2 = new CouponBean();
		CouponDAO dao2 = new CouponDAO();
		
		
		bean = dao.viewMember(session_id);
		
		request.setAttribute("member", bean);
		
		RequestDispatcher dis = request.getRequestDispatcher("roulette.jsp");
		dis.forward(request, response);
	}
}
