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


@WebServlet("/Sale3.do")
public class Sale3 extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public Sale3() {
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
		
		String date = request.getParameter("date5");
		int count = 0;
		int sum = 0;
		
		date = date.replaceAll("-","");
		date = date.substring(0,6);
		
		OrderDAO dao = new OrderDAO();
		OrderBean bean = new OrderBean();
		Vector<OrderBean> v = new Vector<OrderBean>();
		
		v = dao.viewDay3Sale(date, bean);
		count = dao.countDay3Sale(date);
		sum = dao.totalsale3(date);
		
		request.setAttribute("day", v);
		request.setAttribute("date", date);
		request.setAttribute("count", count);
		request.setAttribute("sum", sum);
		
		RequestDispatcher dis = request.getRequestDispatcher("/admin/sales/sale3Info.jsp");
		dis.forward(request, response);
	}

}
