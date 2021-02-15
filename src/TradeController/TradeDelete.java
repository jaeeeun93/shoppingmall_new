package TradeController;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.CommentDAO;
import DAO.TradeDAO;

@WebServlet("/trade/delete.do")
public class TradeDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public TradeDelete() {
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
		
		int uid = Integer.parseInt(request.getParameter("uid"));
		
		CommentDAO cdao = new CommentDAO();
		cdao.viewcommentDelete(uid);
		
		TradeDAO dao = new TradeDAO();
		dao.TradeDelete(uid);
		
		response.sendRedirect("/trade/List.do");
		
	}

}
