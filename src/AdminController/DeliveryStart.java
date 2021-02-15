package AdminController;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.OrderDAO;
import Model.OrderBean;

@WebServlet("/admin/order/deliveryStart.do")
public class DeliveryStart extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DeliveryStart() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAll(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAll(request, response);
	}
	
	protected void doAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		OrderDAO dao = new OrderDAO();
		
		int uid = Integer.parseInt(request.getParameter("uid"));
		String id = request.getParameter("id");
		String signdate = "";
		String deliverydate = "";
		
		java.util.Date today = new java.util.Date();
		SimpleDateFormat cal = new SimpleDateFormat("yyyyMMddhhmmss");
		signdate = cal.format(today);

		Calendar cal2 = Calendar.getInstance();
		cal2.add(Calendar.DATE, 3);
		
		deliverydate = cal.format(cal2.getTime());
		
		
		dao.orderDeliveryStart(uid);
		dao.temporderDeliveryStart(id,deliverydate);
		
		response.sendRedirect("/admin/OrderControll.do");
	}
	

}
