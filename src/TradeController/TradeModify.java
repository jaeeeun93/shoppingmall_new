package TradeController;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.TradeDAO;
import Model.TradeBean;

@WebServlet("/trade/modify.do")
public class TradeModify extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public TradeModify() {
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
		
		TradeBean bean = new TradeBean();
		TradeDAO dao = new TradeDAO();
		
		bean = dao.tradeView(uid);
		
		request.setAttribute("view", bean);
		RequestDispatcher dis = request.getRequestDispatcher("/trade/tradeModify.jsp");
		dis.forward(request, response);
		
		
	}

}
