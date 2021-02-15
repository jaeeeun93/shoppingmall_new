package DAO;

import java.sql.*;
import java.util.Vector;

import Model.BoardBean;

public class BoardDAO {
	Statement stmt;
	PreparedStatement pstmt;
	ResultSet rs;
	
	DAO dao = new DAO();
	
	//전체 글 불러오기
	public Vector<BoardBean> getAllBoard(int startRow, int endRow) {
		dao.getCon();
			
		Vector<BoardBean> v = new Vector<BoardBean>();
		
		try {
			String sql = "select * from board order by uid desc limit ?,?";
			
			pstmt = dao.conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				BoardBean bean = new BoardBean();
				
				bean.setUid(rs.getInt("uid"));
				bean.setId(rs.getString("id"));
				bean.setSubject(rs.getString("subject"));
				bean.setComment(rs.getString("comment"));
				bean.setSigndate(rs.getString("signdate"));
				bean.setFile(rs.getString("file"));
				bean.setFile_s(rs.getString("file_s"));
				bean.setGongji(rs.getString("gongji"));
				bean.setFid(rs.getInt("fid"));
				bean.setRef(rs.getInt("ref"));
				bean.setThread(rs.getString("thread"));
				
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
			String sql = "select count(*) from board";
			pstmt = dao.conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				count = rs.getInt(1);
			}
			
			pstmt.close();
			dao.conn.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return count;
	}
	
	//게시글작성
	public void insertBoard(BoardBean bean) {
		dao.getCon();
		
		try {
			String sql = "insert into board (id,subject,ref,comment,signdate,file,file_s,gongji) values(?,?,?,?,?,?,?,?)";
			
			pstmt = dao.conn.prepareStatement(sql);
			
			pstmt.setString(1, bean.getId());
			pstmt.setString(2, bean.getSubject());
			pstmt.setInt(3, bean.getRef());
			pstmt.setString(4, bean.getComment());
			pstmt.setString(5, bean.getSigndate());
			pstmt.setString(6, bean.getFile());
			pstmt.setString(7, bean.getFile_s());
			pstmt.setString(8, bean.getGongji());
			
			pstmt.executeUpdate();
			
			pstmt.close();
			dao.conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//게시글 보기
	public BoardBean boardView(int uid) {
		dao.getCon();
		
		BoardBean bean = new BoardBean();
		
		try {
			String sql = "select * from board where uid=?";
			
			pstmt = dao.conn.prepareStatement(sql);
			
			pstmt.setInt(1, uid);
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				bean.setUid(rs.getInt("uid"));
				bean.setId(rs.getString("id"));
				bean.setSubject(rs.getString("subject"));
				bean.setRef(rs.getInt("ref"));
				bean.setComment(rs.getString("comment"));
				bean.setSigndate(rs.getString("signdate"));
				bean.setFile(rs.getString("file"));
				bean.setGongji(rs.getString("gongji"));
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return bean;
	}
	
	//게시글 조회수 상승
	public void boardref(int uid) {
		dao.getCon();
		
		try {
			String sql = "update board set ref = ref+1 where uid=?";
			pstmt = dao.conn.prepareStatement(sql);
			
			pstmt.setInt(1, uid);
		
			pstmt.executeUpdate();
			
			pstmt.close();
			dao.conn.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	//게시글 삭제
	public void boardDelete(int uid) {
		dao.getCon();
		
		try {
			String sql = "delete from board where uid=?";
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
	public void boardUpdate(BoardBean bean) {
		dao.getCon();
		
		try {
			String sql = "update board set subject=?,comment=? where uid=? ";
			
			pstmt = dao.conn.prepareStatement(sql);
			
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
}
