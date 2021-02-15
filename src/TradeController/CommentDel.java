package TradeController;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.CommentDAO;
import DAO.TradeDAO;

@WebServlet("/trade/commentDel.do")
public class CommentDel extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CommentDel() {
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
		
		//댓글 uid
		int cuid = Integer.parseInt(request.getParameter("Commentuid"));
		
		//게시글 uid view페이지로 돌아가기 위해서 끌어옴.
		int uid = Integer.parseInt(request.getParameter("uid"));
		
		CommentDAO dao = new CommentDAO();
		dao.CommentDelete(cuid);
		
		//trade 테이블의 댓글 카운트 - 처리
		TradeDAO tdao = new TradeDAO();
		tdao.commentcntdn(uid);

		
		response.sendRedirect("/trade/View.do?uid="+uid+"");
	}
}
