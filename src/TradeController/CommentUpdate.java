package TradeController;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.CommentDAO;
import Model.CommentBean;

@WebServlet("/trade/commentUpdate.do")
public class CommentUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CommentUpdate() {
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
		
		int cuid = Integer.parseInt(request.getParameter("cuid"));
		int uid = Integer.parseInt(request.getParameter("uid"));
		
		CommentBean bean = new CommentBean();
		
		bean.setReplycomment(request.getParameter("comment"));
		bean.setReplyid(request.getParameter("id"));
		bean.setUid(cuid);
		
		CommentDAO dao = new CommentDAO();
		dao.CommentUpdate(bean);
		
		response.sendRedirect("/trade/View.do?uid="+uid+"");
	}

}
