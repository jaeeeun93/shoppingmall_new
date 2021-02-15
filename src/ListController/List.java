package ListController;

import java.io.IOException;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.ListDAO;
import Model.ListBean;

@WebServlet("/list/List.do")
public class List extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public List() {
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
		//한페이지에서 보여질 게시글 수
		int pageSize=5;
		
		//현재 보여지는 페이지의 값
		int pageNum =1;
		if(request.getParameter("pageNum") != null) {
			pageNum = Integer.parseInt(request.getParameter("pageNum"));
		}
		
		//전체 게시글 총 갯수 초기화
		int count=0;
		
		//페이지 내에서 보여질 넘버링 숫자 초기화
		int number=0;
		
		ListDAO dao = new ListDAO();
		
		//전체게시글 총 수
		count = dao.getAllcount();
		
		//현재 페이지 limit 값
		int startRow  =(pageNum -1)* pageSize;
		int endRow = pageSize;
		
		Vector<ListBean> v = new Vector<ListBean>();
		String field=request.getParameter("field");
		String search = request.getParameter("search");
		
		if(search != null && search != "") {
			v= dao.searchList(field , search,startRow,endRow);
		}else {
			v= dao.getAllBoard(startRow,endRow);
		}
		
		
		number = count-(pageNum-1)*pageSize;
		
		request.setAttribute("v", v);
		request.setAttribute("count", count);
		request.setAttribute("number", number);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("pageNum", pageNum);

		RequestDispatcher dis = request.getRequestDispatcher("list.jsp");
		dis.forward(request, response);
	}
}
