package TradeController;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.CommentDAO;
import DAO.TradeDAO;
import Model.CommentBean;

@WebServlet("/trade/reComment.do")
public class ReComment extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ReComment() {
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
		
		CommentDAO dao = new CommentDAO();
		CommentBean bean = new CommentBean();

		int uid = Integer.parseInt(request.getParameter("uid"));
		
		Date date = new Date();
		SimpleDateFormat cal1 = new SimpleDateFormat("yyyy-MM-dd");
		String commentDate = cal1.format(date);
		
		int fid = Integer.parseInt(request.getParameter("fid").trim());
		
		char thread = dao.commentThread(fid);
		thread++;
		
		bean.setView_uid(Integer.parseInt(request.getParameter("uid")));
		bean.setReplyid(request.getParameter("id"));
		bean.setReplycomment(request.getParameter("comment"));
		bean.setReplydate(commentDate);
		bean.setFid(fid);
		
		dao.insertRecomment(bean, thread);
		
		//trade 테이블의 댓글 카운트 + 처리
		TradeDAO tdao = new TradeDAO();
		tdao.commentcntup(uid);
		
		response.sendRedirect("/trade/View.do?uid="+uid+"");
		
	}
	
	
}
