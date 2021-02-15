package DAO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import Model.ListBean;

public class ListDAO {
	Statement stmt;
	PreparedStatement pstmt;
	ResultSet rs;
	
	DAO dao = new DAO();
	
	//게시글 보기
	public ListBean listVeiw(int uid) {
		dao.getCon();
		
		ListBean bean = new ListBean();
		
		try{
			String sql = "select * from list where uid=?";
			
			pstmt =dao.conn.prepareStatement(sql);
			
			pstmt.setInt(1, uid);
			
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				bean.setUid(rs.getInt("uid"));//rs에서 하니까db에 있는 거 가져와 bean에넣음
				bean.setId(rs.getString("id"));
				bean.setSubject(rs.getString("subject"));
				bean.setRef(rs.getInt("ref"));
				bean.setSigndate(rs.getString("signdate"));
				//2020.11.27 수정!
				bean.setComment(rs.getString("comment"));
				//2020.12.02 수정!
				bean.setFile(rs.getString("file"));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return bean;
	}
	//조회수 상승
	public void listref(int uid) {
		dao.getCon();
		
		try {
			String sql = "update list set ref = ref+1 where uid=?";
			
			pstmt= dao.conn.prepareStatement(sql);
			pstmt.setInt(1, uid);
			
			pstmt.executeUpdate();
			
			pstmt.close();
			dao.conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	//게시글 작성<2020.11.30 첨부파일추가>
	public void insertList(ListBean bean) {
		dao.getCon();
		
		try {//input으로 받아오는 값이랑 여기에 넣는 값이랑 매칭되야함!안그럼 여긴null인데 db는 ""이라 오류
			String sql= "insert into list (id,subject,comment,signdate,file,file_s) values(?,?,?,?,?,?)";
			
			pstmt= dao.conn.prepareStatement(sql);
			
			pstmt.setString(1, bean.getId());
			pstmt.setString(2, bean.getSubject());
			pstmt.setString(3, bean.getComment());
			pstmt.setString(4, bean.getSigndate());
			pstmt.setString(5,bean.getFile());
			//2020.12.03추가
			pstmt.setString(6, bean.getFile_s());
			
			pstmt.executeUpdate();
			
			pstmt.close();
			dao.conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	//전체글 불러오기 2020.12 03 파일,썸네일 추가
	public Vector<ListBean> getAllBoard(int startRow, int endRow){
		dao.getCon();
		
		Vector<ListBean> v = new Vector<ListBean>();
		
		try {
			String sql = "select * from list order by uid desc limit ?,?";//반대로 정렬해서 uid랑 number 맞게
			//?,? - ?어디서 시작  ?표시할 갯수
			pstmt=dao.conn.prepareStatement(sql);
			
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				ListBean bean = new ListBean();
				
				bean.setUid(rs.getInt("uid"));
				bean.setId(rs.getString("id"));
				bean.setSubject(rs.getString("subject"));
				bean.setComment(rs.getString("comment"));
				bean.setSigndate(rs.getString("signdate"));
				bean.setRef(rs.getInt("ref"));
				bean.setFile(rs.getString("file"));
				//2020.12.03추가
				bean.setFile_s(rs.getString("file_s"));
				
				v.add(bean);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return v;
	}
	
	//총 게시글의 갯수
	public int getAllcount() {
		dao.getCon();
		int count = 0;
		
		try {
			String sql = "select count(*) from list";
			
			pstmt = dao.conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {//db안에 있는걸 다 찾아보는
				
				count = rs.getInt(1);//count 컬럼이 1개니까 count 칼럼의 결과값을 count변수에 넣음
				/* count = rs.getInt("count(*)"); */
			}
			
			pstmt.close();
			dao.conn.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return count;
	}
	//list게시글 삭제 2020.11.27 만듦
	public void listDelete(int uid) {
		dao.getCon();
		
		try {
			String sql = "delete from list where uid=?";
			
			pstmt= dao.conn.prepareStatement(sql);
			
			pstmt.setInt(1, uid);
			
			pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	//게시글 수정 업데이트
	public void listupdate(ListBean bean) {
		dao.getCon();
		
		try {
			String  sql = "update list set subject=? , comment=? where uid=?";
			
			pstmt= dao.conn.prepareStatement(sql);
			
			pstmt.setString(1, bean.getSubject());
			pstmt.setString(2, bean.getComment());
			pstmt.setInt(3, bean.getUid());
			
			pstmt.executeUpdate();
			
			pstmt.close();
			dao.conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	//글 검색
	public Vector<ListBean> searchList(String field,String search,int startRow,int endRow){
		dao.getCon();
		
		Vector<ListBean> v = new Vector<ListBean>();
		
		try {
			String sql = "select * from list where "+field+" like '%"+search+"%'order by uid desc limit ?,? ";
			//?,? - ?어디서 시작  ?표시할 갯수
			pstmt=dao.conn.prepareStatement(sql);
			
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				ListBean bean = new ListBean();
				
				bean.setUid(rs.getInt("uid"));
				bean.setId(rs.getString("id"));
				bean.setSubject(rs.getString("subject"));
				bean.setComment(rs.getString("comment"));
				bean.setSigndate(rs.getString("signdate"));
				bean.setRef(rs.getInt("ref"));
				bean.setFile(rs.getString("file"));
				//2020.12.03추가
				bean.setFile_s(rs.getString("file_s"));
			
				v.add(bean);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return v;
	}
}
