package TradeController;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.CommentDAO;
import DAO.TradeDAO;
import Model.CommentBean;

@WebServlet("/trade/commentIn.do")
public class CommentInsert extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CommentInsert() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		doAll(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		doAll(request, response);
	}

	protected void doAll(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		CommentDAO dao = new CommentDAO();
		CommentBean bean = new CommentBean();

		int fid = 0;
		
		Date date = new Date();
		SimpleDateFormat cal1 = new SimpleDateFormat("yyyy-MM-dd");
		String commentDate = cal1.format(date);
		
		int uid = Integer.parseInt(request.getParameter("uid"));
		
		fid = dao.fidnumber();

		bean.setView_uid(Integer.parseInt(request.getParameter("uid")));
		bean.setReplyid(request.getParameter("id"));
		bean.setReplycomment(request.getParameter("comment"));
		bean.setReplydate(commentDate);
		bean.setFid(fid);

		dao.insertComment(bean);
		
		//trade 테이블의 댓글 카운트 + 처리
		TradeDAO tdao = new TradeDAO();
		tdao.commentcntup(uid);
		
		response.sendRedirect("/trade/View.do?uid="+uid+"");
	}

}
