package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import Model.ItemBean;
import Model.OrderBean;
import Model.ItemReviewBean;

public class ItemReviewDAO {
	Statement stmt;
	PreparedStatement pstmt;
	ResultSet rs;
	
	DAO dao = new DAO();

	// 리뷰 쓰기
	public void writeReview(ItemReviewBean bean) {
		dao.getCon();
		
		try {
			String sql = "insert into itemreview (id,itemCode,itemName,itemFile,itemFile_s,starPoint,review,summary,reviewDate,name) values(?,?,?,?,?,?,?,?,?,?)";
			
			pstmt = dao.conn.prepareStatement(sql);
			
			pstmt.setString(1, bean.getId());
			pstmt.setString(2, bean.getItemCode());
			pstmt.setString(3, bean.getItemName());
			pstmt.setString(4, bean.getItemFile());
			pstmt.setString(5, bean.getItemFile_s());
			pstmt.setDouble(6, bean.getStarPoint());
			pstmt.setString(7, bean.getReview());
			pstmt.setString(8, bean.getSummary());
			pstmt.setString(9, bean.getReviewDate());
			pstmt.setString(10, bean.getName());
			
			pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
			
		}finally {
			try {
				pstmt.close();
				dao.conn.close();
			}catch(Exception e) {
				e.printStackTrace();	
			}
		}
	}
	
	// 리뷰 업데이트 하기
	public void updateReview(ItemReviewBean bean, int reviewUid) {
		dao.getCon();
		
		try {
			String sql = "update itemreview set review = ?, summary = ?, reviewDate =?, itemFile =?, itemFile_s =?, starPoint=? where reviewUid= ?";
			
			pstmt = dao.conn.prepareStatement(sql);
			
			
			pstmt.setString(1, bean.getReview());
			pstmt.setString(2, bean.getSummary());
			pstmt.setString(3, bean.getReviewDate());
			pstmt.setString(4, bean.getItemFile());
			pstmt.setString(5, bean.getItemFile_s());
			pstmt.setDouble(6, bean.getStarPoint());
			pstmt.setInt(7, reviewUid);
			
			pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
			
		}finally {
			try {
				pstmt.close();
				dao.conn.close();
			}catch(Exception e) {
				e.printStackTrace();	
			}
		}
	}
	
	// 내가 쓴 리뷰 보기
	public Vector<ItemReviewBean> viewWroteReview(String id, ItemReviewBean bean) {
		 dao.getCon();
	 
		 Vector<ItemReviewBean> v = new Vector<ItemReviewBean>();
	 try {
		 
		 String sql = "select * from itemreview where id=? order by reviewUid desc";
	 
	 pstmt = dao.conn.prepareStatement(sql);
	 
	 pstmt.setString(1, id);
	 
	 rs = pstmt.executeQuery();
	 
	 while(rs.next()) {
		 	bean = new ItemReviewBean();
		 	bean.setReviewUid(rs.getInt("reviewUid"));
			bean.setItemCode(rs.getString("itemCode"));
			bean.setItemName(rs.getString("itemName"));
			bean.setItemFile_s(rs.getString("itemFile_s"));
			bean.setReviewDate(rs.getString("reviewDate"));
			bean.setReview(rs.getString("review"));
			bean.setSummary(rs.getString("summary"));
			bean.setStarPoint(rs.getInt("starPoint"));
			v.add(bean);
		}
	 
	 }catch(Exception e) {
		 e.printStackTrace();
	 }
	 return v;
	}
	
	//리뷰 개수 출력
	
	public int countReview(String code) {
		dao.getCon();
		int count = 0;
		
		try {
			String sql = "select count(*) from itemreview where itemCode=?";
			pstmt = dao.conn.prepareStatement(sql);
			pstmt.setString(1, code);
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
	
	//리뷰 합계 점수 출력
	
	public double avgReview(String code) {
		dao.getCon();
		double avg = 0.0;
		
		try {
			String sql = "select avg(starPoint) from itemreview where itemCode=?";
			pstmt = dao.conn.prepareStatement(sql);
			pstmt.setString(1, code);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				avg = rs.getDouble(1);
			}
			
			pstmt.close();
			dao.conn.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return avg;
	}

	// 상품별 리뷰 보기
	public Vector<ItemReviewBean> viewItemReview(String code, ItemReviewBean bean) {
		 dao.getCon();
	 
		 Vector<ItemReviewBean> v = new Vector<ItemReviewBean>();
	 try {
		 
		 String sql = "select * from itemreview where itemCode=? order by reviewUid desc";
	 
	 pstmt = dao.conn.prepareStatement(sql);
	 
	 pstmt.setString(1, code);
	 
	 rs = pstmt.executeQuery();
	 
	 while(rs.next()) {
		 	bean = new ItemReviewBean();
		 	bean.setId(rs.getString("id"));
			bean.setItemCode(rs.getString("itemCode"));
			bean.setItemName(rs.getString("itemName"));
			bean.setItemFile_s(rs.getString("itemFile_s"));
			bean.setReviewDate(rs.getString("reviewDate"));
			bean.setReview(rs.getString("review"));
			bean.setSummary(rs.getString("summary"));
			bean.setStarPoint(rs.getInt("starPoint"));
			bean.setName(rs.getString("name"));
			v.add(bean);
		}
	 
	 }catch(Exception e) {
		 e.printStackTrace();
	 }
	 return v;
	}

	//리뷰 삭제
	public void deleteReview(ItemReviewBean bean) {
		dao.getCon();
		
		try {
			String sql = "delete from itemreview where reviewUid = ?";
			
			pstmt = dao.conn.prepareStatement(sql);
			
			pstmt.setInt(1, bean.getReviewUid());
			
			pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
			
		}finally {
			try {
				pstmt.close();
				dao.conn.close();
			}catch(Exception e) {
				e.printStackTrace();	
			}
		}
	}	

	//수정시 내가 쓴 리뷰 불러오기
	public ItemReviewBean viewWroteReview(int reviewUid) {
		dao.getCon();
		
		ItemReviewBean bean = new ItemReviewBean();
		
		try {
			String sql = "select * from itemreview where reviewUid=?";
			pstmt = dao.conn.prepareStatement(sql);
			
			pstmt.setInt(1, reviewUid);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				/* bean.setReviewUid(rs.getInt("reviewUid")); */
				bean.setId(rs.getString("id"));
				bean.setItemCode(rs.getString("itemCode"));
				bean.setItemName(rs.getString("itemName"));
				bean.setItemFile(rs.getString("itemFile"));
				bean.setItemFile_s(rs.getString("itemFile_s"));
				/* bean.setStarPoint(rs.getDouble("starPoint")); */
				bean.setReview(rs.getString("review"));
				bean.setSummary(rs.getString("summary"));
				bean.setReviewDate(rs.getString("reviewDate"));
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return bean;
	}
}
