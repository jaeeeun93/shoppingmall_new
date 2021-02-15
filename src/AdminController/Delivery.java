package AdminController;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.OrderDAO;
import Model.OrderBean;

@WebServlet("/admin/order/delivery.do")
public class Delivery extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Delivery() {
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
		
		dao.orderDelivery(uid);
		
		response.sendRedirect("/admin/OrderControll.do");
	}
	

}
