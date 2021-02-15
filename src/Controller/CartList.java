package Controller;

import java.io.IOException;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.ItemDAO;
import Model.ItemBean;

@WebServlet("/admin/item/cartlist.do")
public class CartList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CartList() {
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
		//한페이지에서 보여질 물품 수
		int pageSize = 5;
		
		//현재 보여지는 페이지의 값
		int pageNum = 1;
		
		if(request.getParameter("pageNum") != null) {
			pageNum = Integer.parseInt(request.getParameter("pageNum"));
		}
		
		//전체 물품 총 갯수 초기화
		int count = 0;
		int price = 0;
		
		//페이지 내에서 보여질 넘버링숫자 초기화
		int number = 0;
		
		ItemDAO dao = new ItemDAO();
		ItemBean bean = new ItemBean();
		bean.setId(request.getParameter("id"));
		
		//전체 물품 총 갯수
		count = dao.getAllCartCount(bean);
		
		//현재 페이지 limit값 
		int startRow = (pageNum -1)*pageSize;
		int endRow = pageSize;
		
		Vector<ItemBean> v = new Vector<ItemBean>();
		v = dao.getAllCart(bean, startRow,endRow);

		price = dao.getAllCartPrice(bean);
		
		number = count-(pageNum-1)*pageSize;
		
		request.setAttribute("v", v);
		request.setAttribute("count", count);
		request.setAttribute("price", price);
		request.setAttribute("number", number);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("pageNum", pageNum);
		
		RequestDispatcher dis = request.getRequestDispatcher("/admin/item/cart.jsp?id="+request.getParameter("id")+"");
		dis.forward(request, response);
	}

}
