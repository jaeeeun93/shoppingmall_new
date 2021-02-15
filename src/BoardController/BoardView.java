package BoardController;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.BoardDAO;
import Model.BoardBean;

@WebServlet("/board/View.do")
public class BoardView extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public BoardView() {
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
		
		int uid = Integer.parseInt(request.getParameter("uid"));
				
		BoardBean bean = new BoardBean();
		BoardDAO dao = new BoardDAO();
		
		bean = dao.boardView(uid);
		
		//ref 증가
		dao.boardref(uid);
		bean.setUid(Integer.parseInt(request.getParameter("uid")));
		
		request.setAttribute("view", bean);
		RequestDispatcher dis = request.getRequestDispatcher("/board/boardView.jsp");
		dis.forward(request, response);
	}
}
