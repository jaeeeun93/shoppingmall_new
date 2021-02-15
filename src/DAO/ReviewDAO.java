//2020.12.01 추가
package DAO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import Model.ReviewBean;

public class ReviewDAO {
	Statement stmt;
	PreparedStatement pstmt;
	ResultSet rs;
	
	DAO dao = new DAO();
	
	//총게시글 수
	public int getAllcount() {
		dao.getCon();
		
		int count=0;
		
		try {
			String sql = "select count(*) from review";
			
			pstmt= dao.conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				count = rs.getInt(1);
			}
			
			pstmt.close();
			dao.conn.close();
		}catch(Exception e ) {
			e.printStackTrace();
		}
		return count;
	}
	//전체글 불러오기
	public Vector<ReviewBean> getAllBoard(int startRow, int endRow) {
		dao.getCon();
		
		Vector<ReviewBean> v = new Vector<ReviewBean>();
		
		try {
			String sql = "select * from review order by uid desc limit ?,?";
			
			pstmt= dao.conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				ReviewBean bean = new ReviewBean();
				
				bean.setUid(rs.getInt("uid"));
				bean.setId(rs.getString("id"));
				bean.setSubject(rs.getString("subject"));
				bean.setComment(rs.getString("comment"));
				bean.setRef(rs.getInt("ref"));
				//2020.12.03 추가
				bean.setFile(rs.getString("file"));
				bean.setFile_s(rs.getString("file_s"));
				
				v.add(bean);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return v;
	}
	//2020.12.02//게시글 작성
	public void insertReview(ReviewBean bean) {
		dao.getCon();
		//2020.12.03 file_S추가
		try {
		String sql="insert into review (id,subject,comment,signdate,file,file_s) values(?,?,?,?,?,?)";
		
		pstmt=dao.conn.prepareStatement(sql);
		
		pstmt.setString(1, bean.getId());
		pstmt.setString(2, bean.getSubject());
		pstmt.setString(3,bean.getComment());
		pstmt.setString(4, bean.getSigndate());
		pstmt.setString(5, bean.getFile());
		pstmt.setString(6, bean.getFile_s());
		
		pstmt.executeUpdate();
		
		pstmt.close();
		dao.conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//게시글 보기, 수정시 쓴글 보기
	public ReviewBean reviewView(int uid) {
		dao.getCon();
		
		ReviewBean bean = new ReviewBean();
		
		try {
			String sql = "select * from review where uid=?";
			
			pstmt=dao.conn.prepareStatement(sql);
			pstmt.setInt(1, uid);
			
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				bean.setUid(rs.getInt("uid"));
				bean.setId(rs.getString("id"));
				bean.setSubject(rs.getString("subject"));
				bean.setRef(rs.getInt("ref"));
				bean.setSigndate(rs.getString("signdate"));
				bean.setComment(rs.getString("comment"));
				bean.setFile(rs.getString("file"));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return bean;
	}
	//조회수 상승
	public void reviewref(int uid) {
		dao.getCon();
		
		try {
			String sql = "update review set ref = ref+1 where uid=?";
			
			pstmt = dao.conn.prepareStatement(sql);
			pstmt.setInt(1, uid);
			
			pstmt.executeUpdate();
			
			pstmt.close();
			dao.conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	//게시글 수정
	public void reviewupdate(ReviewBean bean) {
		dao.getCon();
		
		try {
			String sql = "update review set comment=?, subject=? where uid=?";
			
			pstmt = dao.conn.prepareStatement(sql);
			
			pstmt.setString(1, bean.getComment());
			pstmt.setString(2, bean.getSubject());
			pstmt.setInt(3, bean.getUid());
			
			pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	//게시글 삭제
	public void reviewdelete (int uid) {
		dao.getCon();
		
		try {
			String sql="delete from review where uid=?";
			
			pstmt = dao.conn.prepareStatement(sql);
			pstmt.setInt(1, uid);
			
			pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	//리스트 검색
		public Vector<ReviewBean> searchList(String field,String search,int startRow,int endRow) {
			dao.getCon();
			
			Vector<ReviewBean> v = new Vector<ReviewBean>();
			
			try {
				String sql = "select * from review where "+field+" like '%"+search+"%'order by uid desc limit ?,? ";
				
				pstmt= dao.conn.prepareStatement(sql);
				pstmt.setInt(1, startRow);
				pstmt.setInt(2, endRow);
				rs=pstmt.executeQuery();
				
				while(rs.next()) {
					ReviewBean bean = new ReviewBean();
					
					bean.setUid(rs.getInt("uid"));
					bean.setId(rs.getString("id"));
					bean.setSubject(rs.getString("subject"));
					bean.setComment(rs.getString("comment"));
					bean.setRef(rs.getInt("ref"));
					//2020.12.03 추가
					bean.setFile(rs.getString("file"));
					bean.setFile_s(rs.getString("file_s"));
					
					v.add(bean);
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
			return v;
		}
}
























