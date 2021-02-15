package MemberController;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.MemberDAO;
import Model.MemberBean;

@WebServlet("/member/member_insert.do")

public class MemberInsert extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MemberInsert() {
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
		
		//Board Signdate용 Date함수이다.
		Date today = new Date();
		SimpleDateFormat cal = new SimpleDateFormat("yyyy-MM-dd-hh:mm:ss");
		String joinday = cal.format(today);
		
		MemberBean bean = new MemberBean();
		
		bean.setId(request.getParameter("userid"));//bean에 id 값받은걸 set한다.
		bean.setPass(request.getParameter("userpw"));
		bean.setName(request.getParameter("username"));
		bean.setEmail(request.getParameter("email"));
		bean.setLevel(request.getParameter("level"));
		/*
		 * bean.setZipcode(request.getParameter("zipcode"));
		 * bean.setZip1(request.getParameter("zip1"));
		 * bean.setZip2(request.getParameter("zip2"));
		 * bean.setZip3(request.getParameter("zip3"));
		 */
		bean.setPhone(request.getParameter("mobile"));
		bean.setJoinDate(joinday);
		
		MemberDAO dao = new MemberDAO();//db연결
		
		dao.insertMember(bean);//bean을 insertMember에 담아서 dao에 보냄 dao이름은 insertmember(Member bean)이됨
		
		response.sendRedirect("/");//다 실행한뒤 메인페이지로 간다.
	}
	
}
