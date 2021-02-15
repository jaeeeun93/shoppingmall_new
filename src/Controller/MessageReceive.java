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

import DAO.MessageDAO;
import Model.MessageBean;

@WebServlet("/message/messageReceive.do")
public class MessageReceive extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MessageReceive() {
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
		int count = 0;
		
		MessageDAO dao = new MessageDAO();
		MessageBean bean = new MessageBean();
		
		Vector<MessageBean> v = new Vector<MessageBean>();
		v = dao.viewMessage(session_id);
		count = dao.msgCountRecv(session_id);
		
		request.setAttribute("msg", v);
		request.setAttribute("count", count);
		
		RequestDispatcher dis = request.getRequestDispatcher("/message/messageReceive.jsp");
		dis.forward(request, response);
	}	
}
