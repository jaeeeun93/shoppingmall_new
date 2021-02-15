package AdminController;

import java.io.IOException;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sun.org.apache.xerces.internal.parsers.IntegratedParserConfiguration;

import DAO.MessageDAO;
import Model.MessageBean;

@WebServlet("/admin/Inquire/messageDetail.do")
public class InquireDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public InquireDetail() {
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
		int uid = Integer.parseInt(request.getParameter("uid"));
		
		MessageDAO dao = new MessageDAO();
		MessageBean bean = new MessageBean();
		
		Vector<MessageBean> v = new Vector<MessageBean>();
		v = dao.viewMessageDetail(session_id, uid);
		dao.MessageRead(uid);
		
		request.setAttribute("msg", v);
		RequestDispatcher dis = request.getRequestDispatcher("/admin/Inquire/InquireDetail.jsp");
		dis.forward(request, response);
	}	
}
