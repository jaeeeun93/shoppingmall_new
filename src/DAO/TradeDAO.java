package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import Model.TradeBean;

public class TradeDAO {
	Statement stmt;
	PreparedStatement pstmt;
	ResultSet rs;
	
	DAO dao = new DAO();
	
	//게시글작성
	public void insertTrade(TradeBean bean) {
		dao.getCon();
		
		try {
			String sql = "insert into trade (id,subject,price,comment,signdate,file,file2,file3,file4,file_s,deal,ref) values(?,?,?,?,?,?,?,?,?,?,?,?)";
			
			pstmt = dao.conn.prepareStatement(sql);
			
			pstmt.setString(1, bean.getId());
			pstmt.setString(2, bean.getSubject());
			pstmt.setString(3, bean.getPrice());
			pstmt.setString(4, bean.getComment());
			pstmt.setString(5, bean.getSigndate());
			pstmt.setString(6, bean.getFile());
			pstmt.setString(7, bean.getFile2());
			pstmt.setString(8, bean.getFile3());
			pstmt.setString(9, bean.getFile4());
			pstmt.setString(10, bean.getFile_s());
			pstmt.setString(11, bean.getDeal());
			pstmt.setInt(12, bean.getRef());
			
			pstmt.executeUpdate();
			
			pstmt.close();
			dao.conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//총 게시글 
	public int getAllcount() {
		dao.getCon();
		int count = 0;
		
		try {
			String sql = "select count(*) from trade";
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
	
	//판매 게시글 수 
	public int getBuycount() {
		dao.getCon();
		int count = 0;
		
		try {
			String sql = "select count(deal) from trade where deal = 2";
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
	
	//구매 게시글 수
	public int getSellcount() {
		dao.getCon();
		int count = 0;
		
		try {
			String sql = "select count(deal) from trade where deal = 1";
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
	

	//최신글 리스트
	public ArrayList<TradeBean> getAllTrade(int startRow, int endRow) {
		dao.getCon();
			
		ArrayList<TradeBean> v = new ArrayList<TradeBean>();
		
		try {
			String sql = "select * from trade order by uid desc limit ?,?";
			
			pstmt = dao.conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				TradeBean bean = new TradeBean();
				
				bean.setUid(rs.getInt("uid"));
				bean.setId(rs.getString("id"));
				bean.setSubject(rs.getString("subject"));
				bean.setComment(rs.getString("comment"));
				bean.setSigndate(rs.getString("signdate"));
				bean.setFile(rs.getString("file"));
				bean.setFile_s(rs.getString("file_s"));
				bean.setFid(rs.getInt("fid"));
				bean.setRef(rs.getInt("ref"));
				bean.setThread(rs.getString("thread"));
				bean.setPrice(rs.getString("price"));
				bean.setDeal(rs.getString("deal"));
				bean.setCommentcnt(rs.getInt("commentcnt"));
				
				v.add(bean);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return v;
	}

	//조회수 순 리스트
	public ArrayList<TradeBean> highRefTrade(int startRow, int endRow) {
		dao.getCon();
			
		ArrayList<TradeBean> v = new ArrayList<TradeBean>();
		
		try {
			String sql = "select * from trade order by ref desc limit ?,?";
			
			pstmt = dao.conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				TradeBean bean = new TradeBean();
				
				bean.setUid(rs.getInt("uid"));
				bean.setId(rs.getString("id"));
				bean.setSubject(rs.getString("subject"));
				bean.setComment(rs.getString("comment"));
				bean.setSigndate(rs.getString("signdate"));
				bean.setFile(rs.getString("file"));
				bean.setFile_s(rs.getString("file_s"));
				bean.setFid(rs.getInt("fid"));
				bean.setRef(rs.getInt("ref"));
				bean.setThread(rs.getString("thread"));
				bean.setPrice(rs.getString("price"));
				bean.setDeal(rs.getString("deal"));
				bean.setCommentcnt(rs.getInt("commentcnt"));
				
				v.add(bean);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return v;
	}
	
	
	//판매글 리스트
	public ArrayList<TradeBean> BuyTrade(int startRow, int endRow) {
		dao.getCon();
		
		ArrayList<TradeBean> v = new ArrayList<TradeBean>();
		
		try {
			String sql = "select * from trade where deal=2 order by uid desc limit ?,?";
			
			pstmt = dao.conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				TradeBean bean = new TradeBean();
				
				bean.setUid(rs.getInt("uid"));
				bean.setId(rs.getString("id"));
				bean.setSubject(rs.getString("subject"));
				bean.setComment(rs.getString("comment"));
				bean.setSigndate(rs.getString("signdate"));
				bean.setFile(rs.getString("file"));
				bean.setFile_s(rs.getString("file_s"));
				bean.setFid(rs.getInt("fid"));
				bean.setRef(rs.getInt("ref"));
				bean.setThread(rs.getString("thread"));
				bean.setPrice(rs.getString("price"));
				bean.setDeal(rs.getString("deal"));
				bean.setCommentcnt(rs.getInt("commentcnt"));
				
				v.add(bean);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return v;
	}
	
	
	//구매글 리스트
	public ArrayList<TradeBean> SellTrade(int startRow, int endRow) {
		dao.getCon();
		
		ArrayList<TradeBean> v = new ArrayList<TradeBean>();
		
		try {
			String sql = "select * from trade where deal=1 order by uid desc limit ?,?";
			
			pstmt = dao.conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				TradeBean bean = new TradeBean();
				
				bean.setUid(rs.getInt("uid"));
				bean.setId(rs.getString("id"));
				bean.setSubject(rs.getString("subject"));
				bean.setComment(rs.getString("comment"));
				bean.setSigndate(rs.getString("signdate"));
				bean.setFile(rs.getString("file"));
				bean.setFile_s(rs.getString("file_s"));
				bean.setFid(rs.getInt("fid"));
				bean.setRef(rs.getInt("ref"));
				bean.setThread(rs.getString("thread"));
				bean.setPrice(rs.getString("price"));
				bean.setDeal(rs.getString("deal"));
				bean.setCommentcnt(rs.getInt("commentcnt"));
				
				v.add(bean);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return v;
	}
	
		
	//게시글 검색
	public List<TradeBean> searchTrade(String field,String search) {
		dao.getCon();
		
		List<TradeBean> v = new ArrayList<>();
		
		try {
			String sql = "select * from trade where "+field+" like '%" +search+ "%'";
			
			stmt = dao.conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				TradeBean bean = new TradeBean();
				
				bean.setUid(rs.getInt("uid"));
				bean.setId(rs.getString("id"));
				bean.setSubject(rs.getString("subject"));
				bean.setComment(rs.getString("comment"));
				bean.setSigndate(rs.getString("signdate"));
				bean.setFile(rs.getString("file"));
				bean.setFile_s(rs.getString("file_s"));
				bean.setFid(rs.getInt("fid"));
				bean.setRef(rs.getInt("ref"));
				bean.setThread(rs.getString("thread"));
				bean.setPrice(rs.getString("price"));
				bean.setDeal(rs.getString("deal"));
				bean.setCommentcnt(rs.getInt("commentcnt"));
				
				v.add(bean);
			}
			
			rs.close();
			stmt.close();
			dao.conn.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return v;
	}
	
	
	//게시글 보기
	public TradeBean tradeView(int uid) {
		dao.getCon();
		
		TradeBean bean = new TradeBean();
		
		try {
			String sql = "select * from trade where uid=?";
			
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
				bean.setPrice(rs.getString("price"));
				bean.setFile_s(rs.getString("file_s"));
				bean.setFile(rs.getString("file"));
				bean.setFile2(rs.getString("file2"));
				bean.setFile3(rs.getString("file3"));
				bean.setFile4(rs.getString("file4"));
				bean.setDeal(rs.getString("deal"));
				bean.setCommentcnt(rs.getInt("commentcnt"));
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return bean;
	}
	
	//게시글 조회수 상승
	public void traderef(int uid) {
		dao.getCon();
		
		try {
			String sql = "update trade set ref = ref+1 where uid=?";
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
	public void tradeUpdate(TradeBean bean) {
		dao.getCon();
		
		try {
			String sql = "update trade set subject=?,comment=?,price=?,signdate=?,file_s=?,file=?,file2=?,file3=?,file4=?,deal=? where uid=? ";
									
			pstmt = dao.conn.prepareStatement(sql);
			
			pstmt.setString(1, bean.getSubject());
			pstmt.setString(2, bean.getComment());
			pstmt.setString(3, bean.getPrice());
			pstmt.setString(4, bean.getSigndate());
			pstmt.setString(5, bean.getFile_s());
			pstmt.setString(6, bean.getFile());
			pstmt.setString(7, bean.getFile2());
			pstmt.setString(8, bean.getFile3());
			pstmt.setString(9, bean.getFile4());
			pstmt.setString(10, bean.getDeal());
			pstmt.setInt(11, bean.getUid());
		
			pstmt.executeUpdate();
			
			pstmt.close();
			dao.conn.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	//게시글 삭제
	public void TradeDelete(int uid) {
		dao.getCon();
		
		try {
			String sql = "delete from trade where uid=?";
			pstmt = dao.conn.prepareStatement(sql);
			
			pstmt.setInt(1, uid);
			
			pstmt.executeUpdate();
			
			pstmt.close();
			dao.conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	//총 댓글수 
	public int commentCount() {
		dao.getCon();
		int commentcnt = 0;
		
		try {
			String sql = "select count(commentcnt) from trade";
			pstmt = dao.conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				commentcnt = rs.getInt(1);
			}
			
			pstmt.close();
			dao.conn.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return commentcnt;
	}
	
	//댓글수 상승
	public void commentcntup(int uid) {
		dao.getCon();
		
		try {
			String sql = "update trade set commentcnt = commentcnt+1 where uid=?";
			pstmt = dao.conn.prepareStatement(sql);
			
			pstmt.setInt(1, uid);
		
			pstmt.executeUpdate();
			
			pstmt.close();
			dao.conn.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	//댓글수 감소
	public void commentcntdn(int uid) {
		dao.getCon();
		
		try {
			String sql = "update trade set commentcnt = commentcnt-1 where uid=?";
			pstmt = dao.conn.prepareStatement(sql);
			
			pstmt.setInt(1, uid);
			
			pstmt.executeUpdate();
			
			pstmt.close();
			dao.conn.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
