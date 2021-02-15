package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.MessageDAO;
import Model.MessageBean;


@WebServlet("/message/messageWrite.do")
public class MessageWrite extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MessageWrite() {
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
		String send_id = (String)session.getAttribute("id");
		String recv_id = request.getParameter("recv_id");
		String content = request.getParameter("content");
		String signdate = "";
		int count = 0;
		
		MessageDAO dao = new MessageDAO();
		MessageBean bean = new MessageBean();
		
		java.util.Date today = new java.util.Date();
		SimpleDateFormat cal = new SimpleDateFormat("yyyyMMddhhmmss");
		signdate = cal.format(today);
		
		count = dao.recvCount(recv_id);
		
		if (count == 1) {
			
			dao.messageWrite(send_id, recv_id, content, signdate);
			
			response.setContentType("text/html; charset=UTF-8");
			
			RequestDispatcher dis = request.getRequestDispatcher("/");
			dis.forward(request, response);
			
		}else if(count == 0) {
			response.setContentType("text/html; charset=UTF-8");
			
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('쪽지 전달을 실패하였습니다. 쪽지 수신자 아이디를 확인해 주세요.');");
			out.println("history.back();");
			out.println("</script>");
			
		}else {
			RequestDispatcher dis = request.getRequestDispatcher("/");
			dis.forward(request, response);
		}
		
	}	
}
