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
import Model.OrderBean;


@WebServlet("/Sale.do")
public class Sale extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public Sale() {
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
		
		String date2 = request.getParameter("date2");
		int count = 0;
		int sum = 0;
		
		date2 = date2.replaceAll("-","");
		
		OrderDAO dao = new OrderDAO();
		OrderBean bean = new OrderBean();
		Vector<OrderBean> v = new Vector<OrderBean>();
		
		v = dao.viewDaySale(date2, bean);
		count = dao.countDaySale(date2);
		sum = dao.totalsale(date2);
		
		request.setAttribute("day", v);
		request.setAttribute("date", date2);
		request.setAttribute("count", count);
		request.setAttribute("sum", sum);
		
		RequestDispatcher dis = request.getRequestDispatcher("/admin/sales/saleInfo.jsp");
		dis.forward(request, response);
	}

}
