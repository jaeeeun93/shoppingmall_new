//2020.12.01
package ReviewController;

import java.io.IOException;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.ReviewDAO;
import Model.ReviewBean;

@WebServlet("/campReview/ReviewList.do")
public class ReviewList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ReviewList() {
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
		
		//게시판들어갔을때 uid수 첫번째 
		int pageNum=1;
		//2번째 페이지들어가면 uid=2됨
		if(request.getParameter("pageNum")!=null){
			pageNum = Integer.parseInt(request.getParameter("pageNum"));
		}
		//전체 게시글 수
		int count=0;
		
		//페이지 수 [1][2][3]클릭하는거 
		int number=0;
		
		ReviewDAO dao =  new ReviewDAO();
		
		//전체 게시글 수 count에 담음
		count = dao.getAllcount();
		
		//현재 페이지 limit값
		//배열은 0부터 시작해서
		int startRow = (pageNum -1) * pageSize;
		int endRow = pageSize;
		
		Vector<ReviewBean> v = new Vector<ReviewBean>();
		
		String search = request.getParameter("search");
		String field =request.getParameter("field");
		
		if(search != null && search != "") {
			v = dao.searchList(field , search,startRow,endRow);
		}else {
			v = dao.getAllBoard(startRow,endRow);
		}
		
		number= count-(pageNum-1)*pageSize;
		
		request.setAttribute("v", v);
		request.setAttribute("count", count);
		request.setAttribute("number", number);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("pageNum", pageNum);
		
		RequestDispatcher dis = request.getRequestDispatcher("/campReview/reviewList.jsp");
		dis.forward(request, response);
	}

}
