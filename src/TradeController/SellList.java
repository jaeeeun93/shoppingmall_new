package TradeController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.TradeDAO;
import Model.TradeBean;

@WebServlet("/trade/Sell.do")
public class SellList extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public SellList() {
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
		
		TradeDAO dao = new TradeDAO();
		
		int pageSize = 9;
		int pageNum = 1;
		
		if(request.getParameter("pageNum") != null) {
			pageNum = Integer.parseInt(request.getParameter("pageNum"));
		}
		
		int count = 0;
		int number = 0;
		
		count = dao.getSellcount();
		
		int startRow = (pageNum -1)*pageSize;
		int endRow = pageSize;
		
		number = count-(pageNum-1)*pageSize;
		
		List<TradeBean> v = new ArrayList<>();
		
		String field = request.getParameter("field");
		String search = request.getParameter("search");
		
		if(search != null && search != "") {
			v = dao.searchTrade(field , search);
		}else {
			v = dao.SellTrade(startRow,endRow);
		}
		
		request.setAttribute("v", v);
		request.setAttribute("count", count);
		request.setAttribute("number", number);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("pageNum", pageNum);
		RequestDispatcher dis = request.getRequestDispatcher("/trade/Sell.jsp");
		dis.forward(request, response);
		
	}
	
	
}
