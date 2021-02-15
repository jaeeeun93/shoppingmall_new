package AdminController;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.OrderDAO;
import Model.OrderBean;

@WebServlet("/admin/order/deliveryUndo.do")
public class DeliveryUndo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DeliveryUndo() {
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
		
		dao.orderDeliveryUndo(uid);
		dao.temporderDeliveryUndo(id);
		
		response.sendRedirect("/admin/OrderControll.do");
	}
	

}
