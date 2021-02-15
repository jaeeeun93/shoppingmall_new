package MemberController;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.MemberDAO;
import Model.MemberBean;

@WebServlet("/admin/member/Withdraw.do")
public class AdminWithdraw extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AdminWithdraw() {
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
		
		MemberDAO dao = new MemberDAO();
		dao.WithdrawMember(bean);
		
		response.sendRedirect("/admin/member/list.do");
	}

}
