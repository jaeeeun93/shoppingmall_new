package MemberController;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.MemberDAO;
import Model.MemberBean;

@WebServlet("/admin/member/update.do")
public class AdminUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AdminUpdate() {
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
		
		MemberBean bean = new MemberBean();
		
		bean.setId(request.getParameter("id"));
		bean.setPass(request.getParameter("pass"));
		bean.setName(request.getParameter("name"));
		bean.setEmail(request.getParameter("email"));
		bean.setLevel(request.getParameter("level"));
		bean.setZipcode(request.getParameter("zipcode"));
		bean.setZip1(request.getParameter("zip1"));
		bean.setZip2(request.getParameter("zip2"));
		bean.setZip3(request.getParameter("zip3"));
		bean.setPhone(request.getParameter("phone"));
		bean.setPoint(request.getParameter("point"));
		bean.setUserDel(request.getParameter("userDel"));
		
		MemberDAO dao = new MemberDAO();
		dao.adminmodifyMember(bean);
		
		response.sendRedirect("/admin/member/list.do");
	}

}
