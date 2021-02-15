//2020.11.27 만듦!
package ListController;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.ListDAO;
import Model.ListBean;

@WebServlet("/list/ListUpdate.do")
public class ListUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ListUpdate() {
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
		
		ListBean bean = new ListBean();
		
		bean.setSubject(request.getParameter("subject"));
		bean.setComment(request.getParameter("comment"));
		bean.setUid(Integer.parseInt(request.getParameter("uid")));
		
		ListDAO dao = new ListDAO();
		
		dao.listupdate(bean);
		
		//2020.11.30 경로수정
		//uid값가지고 가서 view페이지에서 보여준다.
		response.sendRedirect("/list/View.do?uid="+bean.getUid()+"");
	}

}
