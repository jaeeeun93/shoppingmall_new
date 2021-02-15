package TradeController;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.CommentDAO;
import Model.CommentBean;

@WebServlet("/trade/commentModify.do")
public class CommentModify extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CommentModify() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAll(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAll(request, response);
	}

	protected void doAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		//댓글 uid
		int cuid = Integer.parseInt(request.getParameter("commentuid"));
		int uid = Integer.parseInt(request.getParameter("uid"));
		
		CommentBean bean = new CommentBean();
		CommentDAO dao = new CommentDAO();
		
		bean = dao.modifyComment(cuid);
		
		request.setAttribute("comment", bean);
		request.setAttribute("uid", uid);
		RequestDispatcher dis = request.getRequestDispatcher("/trade/CommentModify.jsp");
		dis.forward(request, response);
	}
	
	
}
