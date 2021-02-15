package TradeController;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.CommentDAO;
import DAO.TradeDAO;
import Model.CommentBean;
import Model.TradeBean;

@WebServlet("/trade/View.do")
public class TradeView extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public TradeView() {
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
		
		TradeBean bean = new TradeBean();
		TradeDAO dao = new TradeDAO();
		
		int uid = Integer.parseInt(request.getParameter("uid"));
	
		//비회원 비교를 위한 세션값
		HttpSession session = request.getSession();
		String session_id = (String)session.getAttribute("id");
		
		//비회원은 열람X
		if(session_id == null) {
			request.setAttribute("msg", "회원만 열람가능합니다.");
			request.setAttribute("url", "/trade/List.do");
			
			RequestDispatcher dis = request.getRequestDispatcher("/error.jsp");
			dis.forward(request, response);
		}
		
		bean = dao.tradeView(uid);
		
		//ref 증가
		dao.traderef(uid);
		
		CommentDAO viewdao = new CommentDAO(); 
		ArrayList<CommentBean> v = new ArrayList<>();
		
		v = viewdao.commentview(uid);
		 
		request.setAttribute("v", v);
		request.setAttribute("view", bean);
		RequestDispatcher dis = request.getRequestDispatcher("/trade/tradeView.jsp");
		dis.forward(request, response);
	}

}
