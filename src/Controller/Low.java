package Controller;

import java.io.IOException;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.ItemDAO;
import Model.ItemBean;

@WebServlet("/admin/item/low.do")
public class Low extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Low() {
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
		
		HttpSession session = request.getSession();
		String session_id = (String)session.getAttribute("id");
		String flag = request.getParameter("flag");
		
		ItemBean bean = new ItemBean();
		ItemDAO dao = new ItemDAO();
		
		
		
		if (session_id != null) {
			int item_count = dao.checkItem();
			int check_count = dao.checkId(session_id);
			int sum = (item_count-check_count);
			
			if (check_count == 0) {
				dao.insertJjim(session_id);
			}else if(check_count < item_count){
				dao.updateJjim(session_id, check_count, sum);
			}else {
				
			}
		}
		//한페이지에서 보여질 물품 수
		int pageSize = 10;
		
		//현재 보여지는 페이지의 값
		int pageNum = 1;
		if(request.getParameter("pageNum") != null) {
			pageNum = Integer.parseInt(request.getParameter("pageNum"));
		}
		
		//전체 물품 총 갯수 초기화
		int count = 0;
		
		int viewCount = 0;
		
		//페이지 내에서 보여질 넘버링숫자 초기화
		int number = 0;
		double starPoint = 0;
		
		//전체 물품 총 갯수
		count = dao.getAllItemCount();
		
		viewCount = dao.getViewCount();
		
		//현재 페이지 limit값 
		int startRow = (pageNum -1)*pageSize;
		int endRow = pageSize;
		
		Vector<ItemBean> v = new Vector<ItemBean>();
		Vector<ItemBean> v2 = new Vector<ItemBean>();
		Vector<ItemBean> v3 = new Vector<ItemBean>();
		Vector<ItemBean> v4 = new Vector<ItemBean>();
		Vector<ItemBean> v5 = new Vector<ItemBean>();
		Vector<ItemBean> v6 = new Vector<ItemBean>();
		
		v4 = dao.getAllItemFlag(startRow,endRow);
		v5 = dao.getAllItemFlag2(startRow,endRow);
		v6 = dao.getAllItemFlag3(startRow,endRow);
		
		if (flag.equals("1")) {
			v = v4;
		}else if (flag.equals("2")) {
			v = v5;
		}else {
			v = v6;
		}
		
		
		v2 = dao.jjimView(session_id);
		v3 = dao.getAllViewItem();
		
		number = count-(pageNum-1)*pageSize;
		
		request.setAttribute("v", v);
		request.setAttribute("v2", v2);
		request.setAttribute("v3", v3);
		request.setAttribute("count", count);
		request.setAttribute("view", viewCount);
		request.setAttribute("number", number);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("pageNum", pageNum);
		
		RequestDispatcher dis = request.getRequestDispatcher("/admin/item/low.jsp");
		dis.forward(request, response);
	}

}
