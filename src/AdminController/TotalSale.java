package AdminController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.OrderDAO;
import Model.ItemBean;
import Model.OrderBean;

@WebServlet("/admin/sales/TotalSale.do")
public class TotalSale extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public TotalSale() {
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
		
		OrderDAO dao = new OrderDAO();
		OrderBean bean = new OrderBean();
		
		//주문의 총 갯수
		int orderCount = 0;
		orderCount = dao.AllcountOrder();
		
		Vector<OrderBean> v = new Vector<OrderBean>();
		
		ArrayList list = new ArrayList();
		//총 매출액
		int totalSale = 0;
		totalSale = dao.totalsales();
		
		request.setAttribute("orderCount", orderCount);
		request.setAttribute("totalSale", totalSale);
		request.setAttribute("list", list);
		
		RequestDispatcher dis = request.getRequestDispatcher("/admin/sales/TotalSales.jsp");
		dis.forward(request, response);
	}

}
