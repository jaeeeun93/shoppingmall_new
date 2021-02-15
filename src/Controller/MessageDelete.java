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
import Model.ItemBean;
import Model.MessageBean;


@WebServlet("/message/messageDelete.do")
public class MessageDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MessageDelete() {
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
		String flag = request.getParameter("flag");
		
		Vector<MessageBean> v = new Vector<MessageBean>();
		Vector<MessageBean> v2 = new Vector<MessageBean>();
	
		MessageDAO dao = new MessageDAO();
		
		String str = request.getParameter("str");

		String[] array = str.split("-");
		
		if (flag.equals("1")) {
			for(String s : array) {
				dao.deleteCheckRecv(s);
				
				RequestDispatcher dis = request.getRequestDispatcher("/message/messageReceive.do");
				dis.forward(request, response);
			}
		}else if(flag.equals("2")) {
			for(String s : array) {
				dao.deleteCheckSend(s);
				
				RequestDispatcher dis = request.getRequestDispatcher("/message/messageSend.do");
				dis.forward(request, response);
			}
		}else {
			
		}
		
		
	}
}
