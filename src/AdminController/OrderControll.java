package AdminController;

import java.io.IOException;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.BoardDAO;
import DAO.OrderDAO;
import Model.BoardBean;
import Model.OrderBean;

@WebServlet("/admin/OrderControll.do")
public class OrderControll extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public OrderControll() {
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
		
		OrderBean bean = new OrderBean();
		OrderDAO dao = new OrderDAO();
		
		int pageSize = 15;
		
		//현재 보여지는 페이지의 값
		int pageNum = 1;
		if(request.getParameter("pageNum") != null) {
			pageNum = Integer.parseInt(request.getParameter("pageNum"));
		}
		
		//전체 게시글 총 갯수 초기화
		int count = 0;
		
		//페이지 내에서 보여질 넘버링숫자 초기화
		int number = 0;
		
		//전체 게시글 총 갯수
		count = dao.AllcountOrder();
		
		//현재 페이지 limit값 
		int startRow = (pageNum -1)*pageSize;
		int endRow = pageSize;
		
		number = count-(pageNum-1)*pageSize;
		
		Vector<OrderBean> v = new Vector<OrderBean>();
		v = dao.getAllOrder(startRow,endRow);
		
		request.setAttribute("v", v);
		request.setAttribute("count", count);
		request.setAttribute("number", number);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("pageNum", pageNum);
		RequestDispatcher dis = request.getRequestDispatcher("/admin/adminOrder/orderManage.jsp");
		dis.forward(request, response);
	}
	
	
}
