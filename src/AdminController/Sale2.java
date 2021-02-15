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


@WebServlet("/Sale2.do")
public class Sale2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public Sale2() {
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
		
		String date3 = request.getParameter("date3");
		String date4 = request.getParameter("date4");
		int count = 0;
		int sum = 0;
		
		date3 = date3.replaceAll("-","");
		date4 = date4.replaceAll("-","");
		
		OrderDAO dao = new OrderDAO();
		OrderBean bean = new OrderBean();
		Vector<OrderBean> v = new Vector<OrderBean>();
		
		v = dao.viewDay2Sale(date3,date4,bean);
		count = dao.countDay2Sale(date3,date4);
		sum = dao.totalsale2(date3,date4);
		
		request.setAttribute("day", v);
		request.setAttribute("date", date3);
		request.setAttribute("date2", date4);
		request.setAttribute("count", count);
		request.setAttribute("sum", sum);
		
		RequestDispatcher dis = request.getRequestDispatcher("/admin/sales/sale2Info.jsp");
		dis.forward(request, response);
	}

}
