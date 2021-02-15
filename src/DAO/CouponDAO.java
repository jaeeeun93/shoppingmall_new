package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import Model.CouponBean;
import Model.ItemBean;
import Model.OrderBean;

public class CouponDAO {
	Statement stmt;
	PreparedStatement pstmt;
	ResultSet rs;
	
	DAO dao = new DAO();

	//회원 한명의 쿠폰정보 불러오기
	public Vector<CouponBean> viewCoupon(String id) {//변수명은 달라도 상관x
		dao.getCon();
		
		Vector<CouponBean> v = new Vector<CouponBean>();
		
		try {
			String sql = "select * from coupon where id=? and coupon_use = 0 and make_date < validate";
			pstmt = dao.conn.prepareStatement(sql);
			
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				CouponBean bean = new CouponBean();
				bean.setId(rs.getString("id"));
				bean.setCoupon_name(rs.getString("coupon_name"));
				bean.setCoupon_use(rs.getString("coupon_use"));
				bean.setUse_date(rs.getString("use_date"));
				bean.setValidate(rs.getString("validate"));
				
				v.add(bean);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return v;
	}
	
	//사용 쿠폰 제거
	public void minusCoupon(String coupon,String id, String signdate) {
		 
		 dao.getCon();
	 
	 try { String sql = "update coupon set coupon_use = 1,use_date = ? where id = ? and coupon_name = ?" ;
	 
	 pstmt = dao.conn.prepareStatement(sql);
	 
	 pstmt.setString(1,signdate );
	 pstmt.setString(2,id );
	 pstmt.setString(3,coupon );
	 
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
	
	//쿠폰 발급
	public void makeCoupon(String coupon,String id, String signdate, String validate) {
	 
	dao.getCon();
		
		try {
			String sql = "insert into coupon (id, coupon_name, make_date, validate) values(?,?,?,?)";
			
			pstmt = dao.conn.prepareStatement(sql);
			
			pstmt.setString(1, id);
			pstmt.setString(2, coupon);
			pstmt.setString(3, signdate);
			pstmt.setString(4, validate);
			
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
	
	// 쿠폰 이미 발급 받았는지 체크
	
	public int couponCount(String id, String coupon){
		dao.getCon();
		
		int count = 0;
		
		try {
			
			String sql = "select count(*) as count from coupon where id=? and coupon_name = ?";
			pstmt = dao.conn.prepareStatement(sql);
			
			pstmt.setString(1, id);
			pstmt.setString(2, coupon);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				count = rs.getInt("count");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return count;
	}

	// 쿠폰 개수 체크
	
	public int couponCount2(String id){
		dao.getCon();
		
		int count = 0;
		
		try {
			
			String sql = "select count(*) as count from coupon where id=?";
			pstmt = dao.conn.prepareStatement(sql);
			
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				count = rs.getInt("count");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return count;
	}
		
	// 보유 쿠폰 정보 불러오기
	public Vector<CouponBean> viewCouponList(String id) {
		dao.getCon();
		
		CouponBean bean = new CouponBean();
		Vector<CouponBean> v = new Vector<CouponBean>();
		
		try {
			String sql = "select * from coupon where id=?";
			pstmt = dao.conn.prepareStatement(sql);
			
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				bean = new CouponBean();
				bean.setId(rs.getString("id"));
				bean.setCoupon_name(rs.getString("coupon_name"));
				bean.setCoupon_use(rs.getString("coupon_use"));
				bean.setUse_date(rs.getString("use_date"));
				bean.setMake_date(rs.getString("make_date"));
				bean.setValidate(rs.getString("validate"));
				
				v.add(bean);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return v;
	}
}
