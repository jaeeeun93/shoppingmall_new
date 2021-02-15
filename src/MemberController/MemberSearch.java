package MemberController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.MemberDAO;
import Model.MemberBean;

@WebServlet("/admin/member/search.do")
public class MemberSearch extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public MemberSearch() {
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
			
		MemberDAO dao = new MemberDAO();
		
		int pageSize = 5;
		int pageNum = 1;
		
		if(request.getParameter("pageNum") != null) {
			pageNum = Integer.parseInt(request.getParameter("pageNum"));
		}
		
		int count = 0;
		int number = 0;
		
		count = dao.getAllcount();
		
		int startRow = (pageNum -1)*pageSize;
		int endRow = pageSize;
		
		number = count-(pageNum-1)*pageSize;
		
		List<MemberBean> v = new ArrayList<>();
		
		String field = request.getParameter("field");
		String search = request.getParameter("search");
		
		if(search != null && search != "") {
			v = dao.searchMem(field , search);
		}else {
			v = dao.getAllmember(startRow,endRow);
		}
		
		request.setAttribute("v", v);
		request.setAttribute("count", count);
		request.setAttribute("number", number);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("pageNum", pageNum);
		RequestDispatcher dis = request.getRequestDispatcher("/admin/member/admin_MemberList.jsp");
		dis.forward(request, response);
	}

}
