package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.Vector;

import Model.ItemBean;
import Model.MemberBean;
import Model.ItemReviewBean;

public class ItemDAO {
	Statement stmt;
	PreparedStatement pstmt;
	ResultSet rs;
	
	DAO dao = new DAO();
	
	//물품등록
	public void insertItem(ItemBean bean) {
		dao.getCon();
		
		try {
			String sql = "insert into item (itemCode,itemName,itemFile,itemFile_s,itemPrice,itemPoint,stock,itemDate,category) values(?,?,?,?,?,?,?,?,?)";
			
			pstmt = dao.conn.prepareStatement(sql);
			
			pstmt.setString(1, bean.getItemCode());
			pstmt.setString(2, bean.getItemName());
			pstmt.setString(3, bean.getItemFile());
			pstmt.setString(4, bean.getItemFile_s());
			pstmt.setInt(5, bean.getItemPrice());
			pstmt.setInt(6, bean.getItemPoint());
			pstmt.setInt(7, bean.getStock());
			pstmt.setString(8, bean.getItemDate());
			pstmt.setString(9, bean.getCategory());
			
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
	
	//주문등록
	public void insertOrder(ItemBean bean) {
		dao.getCon();
		
		try {
			String sql = "insert into order2 (itemCode,itemName,itemPrice,itemPoint,stock,cartFile_s,id) values(?,?,?,?,?,?,?)";
			
			pstmt = dao.conn.prepareStatement(sql);
			
			pstmt.setString(1, bean.getItemCode());
			pstmt.setString(2, bean.getItemName());
			pstmt.setInt(3, bean.getItemPrice());
			pstmt.setInt(4, bean.getItemPoint());
			pstmt.setInt(5, bean.getStock());
			pstmt.setString(6, bean.getCartFile_s());
			pstmt.setString(7, bean.getId());
			
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
	
	//장바구니등록
	public void insertCart(ItemBean bean) {
		dao.getCon();
		
		try {
			String sql = "insert into cart (itemCode,itemName,itemPrice,itemPoint,stock,cartFile_s,id,orderId) values(?,?,?,?,?,?,?,?)";
			
			pstmt = dao.conn.prepareStatement(sql);
			
			pstmt.setString(1, bean.getItemCode());
			pstmt.setString(2, bean.getItemName());
			pstmt.setInt(3, bean.getItemPrice());
			pstmt.setInt(4, bean.getItemPoint());
			pstmt.setInt(5, bean.getStock());
			pstmt.setString(6, bean.getCartFile_s());
			pstmt.setString(7, bean.getId());
			pstmt.setString(8, bean.getOrderId());
			
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

	//장바구니 물건 삭제
	public void deleteCart(ItemBean bean) {
		dao.getCon();
		
		try {
			String sql = "delete from cart where cartUid = ?";
			
			pstmt = dao.conn.prepareStatement(sql);
			
			pstmt.setInt(1, bean.getCartUid());
			
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
		
	//전체 물품 불러오기
	public Vector<ItemBean> getAllItem(int startRow, int endRow) {
		dao.getCon();
			
		Vector<ItemBean> v = new Vector<ItemBean>();
	  //Vector 대신에 ArryList를 쓸수있다.
	  //ArryList<ItemBean> v = new ArryList<ItemBean>();
		
		try {
			String sql = "select * from item order by itemUid desc limit ?,? ";
			
			pstmt = dao.conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				ItemBean bean = new ItemBean();
				bean.setItemUid(rs.getInt("itemUid"));
				bean.setItemCode(rs.getString("itemCode"));
				bean.setItemName(rs.getString("itemName"));
				bean.setItemFile(rs.getString("itemFile"));
				bean.setItemFile_s(rs.getString("itemFile_s"));
				bean.setItemPrice(rs.getInt("itemPrice"));
				bean.setItemPoint(rs.getInt("itemPoint"));
				bean.setStock(rs.getInt("stock"));
				bean.setCategory(rs.getString("category"));
				bean.setItemDate(rs.getString("itemDate"));
				bean.setStarPoint(rs.getDouble("starPoint"));
				bean.setPnum(rs.getInt("pnum"));
				bean.setSold(rs.getString("sold"));
				v.add(bean);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return v;
		
	}
	
	
	//낮은 가격순
	public Vector<ItemBean> getAllItemFlag(int startRow, int endRow) {
		dao.getCon();
			
		Vector<ItemBean> v = new Vector<ItemBean>();
	  //Vector 대신에 ArryList를 쓸수있다.
	  //ArryList<ItemBean> v = new ArryList<ItemBean>();
		
		try {
			String sql = "select * from item order by itemPrice asc limit ?,?";
			
			pstmt = dao.conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				ItemBean bean = new ItemBean();
				bean.setItemUid(rs.getInt("itemUid"));
				bean.setItemCode(rs.getString("itemCode"));
				bean.setItemName(rs.getString("itemName"));
				bean.setItemFile(rs.getString("itemFile"));
				bean.setItemFile_s(rs.getString("itemFile_s"));
				bean.setItemPrice(rs.getInt("itemPrice"));
				bean.setItemPoint(rs.getInt("itemPoint"));
				bean.setStock(rs.getInt("stock"));
				bean.setCategory(rs.getString("category"));
				bean.setItemDate(rs.getString("itemDate"));
				bean.setStarPoint(rs.getDouble("starPoint"));
				bean.setPnum(rs.getInt("pnum"));
				bean.setSold(rs.getString("sold"));
				v.add(bean);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return v;
		
	}
	
	//높은 가격순
	public Vector<ItemBean> getAllItemFlag2(int startRow, int endRow) {
		dao.getCon();
			
		Vector<ItemBean> v = new Vector<ItemBean>();
	  //Vector 대신에 ArryList를 쓸수있다.
	  //ArryList<ItemBean> v = new ArryList<ItemBean>();
		
		try {
			String sql = "select * from item order by itemPrice desc limit ?,?";
			
			pstmt = dao.conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				ItemBean bean = new ItemBean();
				bean.setItemUid(rs.getInt("itemUid"));
				bean.setItemCode(rs.getString("itemCode"));
				bean.setItemName(rs.getString("itemName"));
				bean.setItemFile(rs.getString("itemFile"));
				bean.setItemFile_s(rs.getString("itemFile_s"));
				bean.setItemPrice(rs.getInt("itemPrice"));
				bean.setItemPoint(rs.getInt("itemPoint"));
				bean.setStock(rs.getInt("stock"));
				bean.setCategory(rs.getString("category"));
				bean.setItemDate(rs.getString("itemDate"));
				bean.setStarPoint(rs.getDouble("starPoint"));
				bean.setPnum(rs.getInt("pnum"));
				bean.setSold(rs.getString("sold"));
				v.add(bean);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return v;
		
	}
	
	//낮은 가격순
	public Vector<ItemBean> getAllItemFlag3(int startRow, int endRow) {
		dao.getCon();
			
		Vector<ItemBean> v = new Vector<ItemBean>();
	  //Vector 대신에 ArryList를 쓸수있다.
	  //ArryList<ItemBean> v = new ArryList<ItemBean>();
		
		try {
			String sql = "select * from item order by stock asc limit ?,?";
			
			pstmt = dao.conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				ItemBean bean = new ItemBean();
				bean.setItemUid(rs.getInt("itemUid"));
				bean.setItemCode(rs.getString("itemCode"));
				bean.setItemName(rs.getString("itemName"));
				bean.setItemFile(rs.getString("itemFile"));
				bean.setItemFile_s(rs.getString("itemFile_s"));
				bean.setItemPrice(rs.getInt("itemPrice"));
				bean.setItemPoint(rs.getInt("itemPoint"));
				bean.setStock(rs.getInt("stock"));
				bean.setCategory(rs.getString("category"));
				bean.setItemDate(rs.getString("itemDate"));
				bean.setStarPoint(rs.getDouble("starPoint"));
				bean.setPnum(rs.getInt("pnum"));
				bean.setSold(rs.getString("sold"));
				v.add(bean);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return v;
		
	}
		
	//해당 카테고리 전체 물품 불러오기
	public Vector<ItemBean> getCategoryItem(int startRow, int endRow, String category) {
		dao.getCon();
			
		Vector<ItemBean> v = new Vector<ItemBean>();
	  //Vector 대신에 ArryList를 쓸수있다.
	  //ArryList<ItemBean> v = new ArryList<ItemBean>();
		
		try {
			String sql = "select * from item where category = ? order by itemUid desc limit ?,? ";
			
			pstmt = dao.conn.prepareStatement(sql);

			pstmt.setString(1, category);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				ItemBean bean = new ItemBean();
				bean.setItemUid(rs.getInt("itemUid"));
				bean.setItemCode(rs.getString("itemCode"));
				bean.setItemName(rs.getString("itemName"));
				bean.setItemFile(rs.getString("itemFile"));
				bean.setItemFile_s(rs.getString("itemFile_s"));
				bean.setItemPrice(rs.getInt("itemPrice"));
				bean.setItemPoint(rs.getInt("itemPoint"));
				bean.setStock(rs.getInt("stock"));
				bean.setItemDate(rs.getString("itemDate"));
				bean.setStarPoint(rs.getDouble("starPoint"));
				bean.setPnum(rs.getInt("pnum"));
				v.add(bean);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return v;
		
	}
	
	//장바구니 물품 불러오기
	public Vector<ItemBean> getAllCart(ItemBean bean, int startRow, int endRow) {
		dao.getCon();
		Vector<ItemBean> v = new Vector<ItemBean>();
	  //Vector 대신에 ArryList를 쓸수있다.
	  //ArryList<ItemBean> v = new ArryList<ItemBean>();
		try {
			String sql = "select * from cart where id=? order by cartUid desc limit ?,?";
			
			pstmt = dao.conn.prepareStatement(sql);
			pstmt.setString(1, bean.getId());
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				bean = new ItemBean();
				bean.setOrderId(rs.getString("orderId"));
				bean.setItemCode(rs.getString("itemCode"));
				bean.setItemName(rs.getString("itemName"));
				bean.setItemPrice(rs.getInt("itemPrice"));
				bean.setItemPoint(rs.getInt("itemPoint"));
				bean.setStock(rs.getInt("stock"));
				bean.setId(rs.getString("id"));
				bean.setCartDate("cartDate");
				bean.setCartFile_s(rs.getString("cartFile_s"));
				bean.setCartUid(rs.getInt("cartUid"));
				
				v.add(bean);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return v;
		
	}
		
	//장바구니에서 바로 주문시 주문목록
	
	public Vector<ItemBean> viewOrderItem(String id,ItemBean bean2) {
		dao.getCon();
		
		Vector<ItemBean> v = new Vector<ItemBean>();
		
		try {
			String sql = "select * from cart where id=? order by cartUid desc";
			pstmt = dao.conn.prepareStatement(sql);
			
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				bean2 = new ItemBean();
				bean2.setOrderId(rs.getString("orderId"));
				bean2.setItemCode(rs.getString("itemCode"));
				bean2.setItemName(rs.getString("itemName"));
				bean2.setItemPrice(rs.getInt("itemPrice"));
				bean2.setItemPoint(rs.getInt("itemPoint"));
				bean2.setStock(rs.getInt("stock"));
				bean2.setId(rs.getString("id"));
				bean2.setCartDate("cartDate");
				bean2.setCartFile_s(rs.getString("cartFile_s"));
				bean2.setCartUid(rs.getInt("cartUid"));
				
				v.add(bean2);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return v;
	}

	//장바구니에 담은 전체 가격 불러오기

	public int getAllCartPrice(ItemBean bean) {
		dao.getCon();
		int price = 0;
		
		try {
			String sql = "select sum(itemPrice) from cart where id=?";
			pstmt = dao.conn.prepareStatement(sql);
			pstmt.setString(1, bean.getId());
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				price = rs.getInt(1);
			}
			
			pstmt.close();
			dao.conn.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return price;
	}
	
	//장바구니에서 체크한 상품 가격 불러오기

	public int getCheckCartPrice(String s) {
		dao.getCon();
		int price = 0;
		
		try {
			String sql = "select sum(itemPrice) from cart where cartUid=?";
			pstmt = dao.conn.prepareStatement(sql);
			pstmt.setString(1, s);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				price = rs.getInt(1);
			}
			
			pstmt.close();
			dao.conn.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return price;
	}
	
	//장바구니에서 체크한 상품 포인트 불러오기

	public int getCheckCartPoint(String s) {
		dao.getCon();
		int point = 0;
		
		try {
			String sql = "select sum(itemPoint) from cart where cartUid=?";
			pstmt = dao.conn.prepareStatement(sql);
			pstmt.setString(1, s);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				point = rs.getInt(1);
			}
			
			pstmt.close();
			dao.conn.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return point;
	}
	
	//장바구니에 주문번호 불러오기

	public String getCartOrderId(ItemBean bean) {
		dao.getCon();
		String orderId = null;
		
		try {
			String sql = "select orderId from cart where id=?";
			pstmt = dao.conn.prepareStatement(sql);
			pstmt.setString(1, bean.getId());
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				orderId = rs.getString("orderId");
			}
			
			pstmt.close();
			dao.conn.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return orderId;
	}
		
	//한개의 물품정보 불러오기
	public ItemBean viewItem(String code) {
		dao.getCon();
		
		ItemBean bean = new ItemBean();
		
		try {
			String sql = "select * from item where itemCode=?";
			pstmt = dao.conn.prepareStatement(sql);
			
			pstmt.setString(1, code);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				bean.setItemUid(rs.getInt("itemUid"));
				bean.setItemCode(rs.getString("itemCode"));
				bean.setItemName(rs.getString("itemName"));
				bean.setItemFile(rs.getString("itemFile"));
				bean.setItemFile_s(rs.getString("itemFile_s"));
				bean.setItemPrice(rs.getInt("itemPrice"));
				bean.setItemPoint(rs.getInt("itemPoint"));
				bean.setStock(rs.getInt("stock"));
				bean.setItemDate(rs.getString("itemDate"));
				bean.setSold(rs.getString("sold"));
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return bean;
	}
		
	//총 물품 갯수
	public int getAllItemCount() {
		dao.getCon();
		int count = 0;
		
		try {
			String sql = "select count(*) from item";
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
	
	//카테고리별 총 물품 갯수
	public int getCategoryItemCount(String category) {
		dao.getCon();
		int count = 0;
		
		try {
			String sql = "select count(*) from item where category = ?";
			pstmt = dao.conn.prepareStatement(sql);
			pstmt.setString(1, category);
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
		
	//총 장바구니 물품 갯수
	public int getAllCartCount(ItemBean bean) {
		dao.getCon();
		int count = 0;
		
		try {
			String sql = "select count(*) from cart where id=?";
			pstmt = dao.conn.prepareStatement(sql);
			pstmt.setString(1, bean.getId());
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
		
	//장바구니에서 바로 주문시 주문목록
		
	public Vector<ItemBean> viewCartOrder(String id,ItemBean bean2) {
		dao.getCon();
		
		Vector<ItemBean> v = new Vector<ItemBean>();
		
		try {
			String sql = "select * from cart where id=? order by cartUid desc";
			pstmt = dao.conn.prepareStatement(sql);
			
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				bean2 = new ItemBean();
				bean2.setOrderId(rs.getString("orderId"));
				bean2.setItemCode(rs.getString("itemCode"));
				bean2.setItemName(rs.getString("itemName"));
				bean2.setItemPrice(rs.getInt("itemPrice"));
				bean2.setItemPoint(rs.getInt("itemPoint"));
				bean2.setStock(rs.getInt("stock"));
				bean2.setId(rs.getString("id"));
				bean2.setCartDate("cartDate");
				bean2.setCartFile_s(rs.getString("cartFile_s"));
				bean2.setCartUid(rs.getInt("cartUid"));
				
				v.add(bean2);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return v;
	}
	
	//장바구니에서 바로 주문시 체크한것만 보여주는 주문목록
	
		public Vector<ItemBean> viewCheckCartOrder(String s) {
			dao.getCon();
			
			Vector<ItemBean> v = new Vector<ItemBean>();
			
			try {
				String sql = "select * from cart where cartUid=?";
				pstmt = dao.conn.prepareStatement(sql);
				
				pstmt.setString(1, s);
				
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					ItemBean bean2 = new ItemBean();
					bean2.setOrderId(rs.getString("orderId"));
					bean2.setItemCode(rs.getString("itemCode"));
					bean2.setItemName(rs.getString("itemName"));
					bean2.setItemPrice(rs.getInt("itemPrice"));
					bean2.setItemPoint(rs.getInt("itemPoint"));
					bean2.setStock(rs.getInt("stock"));
					bean2.setId(rs.getString("id"));
					bean2.setCartDate("cartDate");
					bean2.setCartFile_s(rs.getString("cartFile_s"));
					bean2.setCartUid(rs.getInt("cartUid"));
					
					v.add(bean2);
				}
				
			}catch(Exception e) {
				e.printStackTrace();
			}
			return v;
		}
	
	
	
	//주문한 아이템 코드값 불러오기
	
	public ArrayList<ItemBean> viewItemCode(String id) {
		dao.getCon();
		
		ArrayList<ItemBean> list = new ArrayList<ItemBean>();
		
		try {
			String sql = "select itemCode from cart where id=?";
			pstmt = dao.conn.prepareStatement(sql);
			
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				ItemBean bean2 = new ItemBean();
				bean2.setItemCode(rs.getString("itemCode"));
				
				list.add(bean2);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return list ;
	}

	// 별점 매기기
	public void insertStarPoint(ItemReviewBean bean, String code) {
		dao.getCon();
		
		try {
			String sql = "update item set starPoint=starPoint + ?, pnum=pnum + 1  where itemCode=?";
			
			pstmt = dao.conn.prepareStatement(sql);
			
			pstmt.setDouble(1, bean.getStarPoint());
			pstmt.setString(2, code);
			
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
	
	// 별점 취소하기
	public void minusStarPoint(ItemReviewBean bean, String code, double minus) {
		dao.getCon();
		
		try {
			String sql = "update item set starPoint=starPoint - ?, pnum=pnum - 1  where itemCode=?";
			
			pstmt = dao.conn.prepareStatement(sql);
			
			pstmt.setDouble(1, minus);
			pstmt.setString(2, code);
			
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
	
	// 찜
	public void jjimY(String id, int itemUid, int jjim) {
		dao.getCon();
		
		try {
			String sql = "update jjim set jjim = ? where id=? and itemUid = ?";
			
			pstmt = dao.conn.prepareStatement(sql);
			
			pstmt.setInt(1, jjim);
			pstmt.setString(2, id);
			pstmt.setInt(3, itemUid);
			
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
	
	// 찜 취소
	public void jjimN(String id,int itemUid) {
		dao.getCon();
		
		try {
			String sql = "update jjim set jjim = 0 where id=? and itemUid = ?";
			
			pstmt = dao.conn.prepareStatement(sql);

			pstmt.setString(1, id);
			pstmt.setInt(2, itemUid);
			
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
		

	//찜한 아이템 보여주기
	public Vector<ItemBean> jjimView(String id) {
		dao.getCon();
		
		ItemBean bean = new ItemBean();
		Vector<ItemBean> v = new Vector<ItemBean>();
		
		try {
			String sql = "select * from jjim where id=? order by itemUid desc";
			pstmt = dao.conn.prepareStatement(sql);
			
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				bean = new ItemBean();
				bean.setItemUid(rs.getInt("itemUid"));
				bean.setId(rs.getString("id"));
				bean.setJjim(rs.getString("jjim"));
				v.add(bean);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return v;
	}
	
	
	// 찜한거 있는지 없는지 체크
	public int checkId(String id) {
		dao.getCon();
		
		int count = 0;
		try {
			String sql = "select count(*) from jjim where id=?";
			pstmt = dao.conn.prepareStatement(sql);
			
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				count = rs.getInt(1);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return count;
	}

	//쇼핑 들어올 때 찜
	public void insertJjim(String id) {
		dao.getCon();
		
		try {

			String sql = "select * from item";
			pstmt = dao.conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int uid = rs.getInt("itemUid");
				
				String sql2 = "insert into jjim (id,itemUid,jjim) values (?,?,?)";
				PreparedStatement pstmt2 = dao.conn.prepareStatement(sql2);
				pstmt2.setString(1,id);
				pstmt2.setInt(2,uid);
				pstmt2.setInt(3,0);
				pstmt2.executeUpdate();
			
				pstmt2.close();
			}
			
			rs.close();
			pstmt.close();
			dao.conn.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	//쇼핑 들어올 때 상품 추가된 경우
	public void updateJjim(String id, int start, int end) {
		dao.getCon();
		
		try {

			String sql = "select * from item limit ?, ?";
			
			pstmt = dao.conn.prepareStatement(sql);
			pstmt.setInt(1,start);
			pstmt.setInt(2,end);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int uid = rs.getInt("itemUid");
				
				String sql2 = "insert into jjim (id,itemUid,jjim) values (?,?,?)";
				PreparedStatement pstmt2 = dao.conn.prepareStatement(sql2);
				pstmt2.setString(1,id);
				pstmt2.setInt(2,uid);
				pstmt2.setInt(3,0);
				pstmt2.executeUpdate();
			
				pstmt2.close();
			}
			
			rs.close();
			pstmt.close();
			dao.conn.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	// 상품 개수 체크
	public int checkItem() {
		dao.getCon();
		
		int count = 0;
		try {
			String sql = "select count(*) from item";
			pstmt = dao.conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				count = rs.getInt(1);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return count;
	}
	
	//검색 상품 불러오기
	public Vector<ItemBean> getSearchItem(String field, String search, int startRow, int endRow) {
		dao.getCon();
			
		Vector<ItemBean> v = new Vector<ItemBean>();
	  //Vector 대신에 ArryList를 쓸수있다.
	  //ArryList<ItemBean> v = new ArryList<ItemBean>();
		
		try {
			String sql = "select * from item where "+field+" like '%" +search+ "%'  order by itemUid desc limit ?,? ";
			
			pstmt = dao.conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				ItemBean bean = new ItemBean();
				bean.setItemUid(rs.getInt("itemUid"));
				bean.setItemCode(rs.getString("itemCode"));
				bean.setItemName(rs.getString("itemName"));
				bean.setItemFile(rs.getString("itemFile"));
				bean.setItemFile_s(rs.getString("itemFile_s"));
				bean.setItemPrice(rs.getInt("itemPrice"));
				bean.setItemPoint(rs.getInt("itemPoint"));
				bean.setStock(rs.getInt("stock"));
				bean.setItemDate(rs.getString("itemDate"));
				bean.setStarPoint(rs.getDouble("starPoint"));
				bean.setPnum(rs.getInt("pnum"));
				v.add(bean);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return v;
		
	}
	
	//검색한 상품 갯수
	public int getSearchItemCount(String field, String search) {
		dao.getCon();
		int count = 0;
		
		try {
			String sql = "select count(*) from item where "+field+" like '%" +search+ "%'";
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
	
	//찜한 상품 itemUid 불러오기
	public Vector<ItemBean> viewJjimList(String id) {
		dao.getCon();
		
		ItemBean bean = new ItemBean();
		Vector<ItemBean> v = new Vector<ItemBean>();
		
		try {
			String sql = "select * from jjim where id=? and jjim = 1 order by itemUid desc";
			pstmt = dao.conn.prepareStatement(sql);
			
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				bean = new ItemBean();
				bean.setItemUid(rs.getInt("itemUid"));
				bean.setId(rs.getString("id"));
				bean.setJjim(rs.getString("jjim"));
				v.add(bean);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return v;
	}

	//찜한 상품 불러오기
	public Vector<ItemBean> getJjimItem(int itemUid) {
		dao.getCon();
			
		Vector<ItemBean> v = new Vector<ItemBean>();
		
		try {
			String sql = "select * from item where itemUid = ? order by itemUid desc";
			
			pstmt = dao.conn.prepareStatement(sql);
			pstmt.setInt(1, itemUid);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				ItemBean bean = new ItemBean();
				bean.setItemUid(rs.getInt("itemUid"));
				bean.setItemCode(rs.getString("itemCode"));
				bean.setItemName(rs.getString("itemName"));
				bean.setItemFile(rs.getString("itemFile"));
				bean.setItemFile_s(rs.getString("itemFile_s"));
				bean.setItemPrice(rs.getInt("itemPrice"));
				bean.setItemPoint(rs.getInt("itemPoint"));
				bean.setStock(rs.getInt("stock"));
				bean.setItemDate(rs.getString("itemDate"));
				bean.setStarPoint(rs.getDouble("starPoint"));
				bean.setPnum(rs.getInt("pnum"));
				v.add(bean);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return v;
		
	}
	
	//총 찜 상품 갯수
	public int getJjimCount(String id) {
		dao.getCon();
		int count = 0;
		
		try {
			String sql = "select count(*) from Jjim where id = ? and jjim = 1";
			pstmt = dao.conn.prepareStatement(sql);
			pstmt.setString(1, id);
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
	
	// 총 찜 상품 가격
	public int getJjimPrice(int itemUid) {
		dao.getCon();
			
		int price=0;
		
		try {
			String sql = "select sum(itemPrice) from item where itemUid = ? order by itemUid desc";
			
			pstmt = dao.conn.prepareStatement(sql);
			pstmt.setInt(1, itemUid);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				price = rs.getInt(1);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return price;
	}
	
	//최근 본 상품 갯수
	public int getViewCount() {
		dao.getCon();
		int count = 0;
		
		try {
			String sql = "select count(*) from item where view = 1";
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

	//최근 본 상품 불러오기
	public Vector<ItemBean> getAllViewItem() {
		dao.getCon();
			
		Vector<ItemBean> v = new Vector<ItemBean>();
		
		try {
			String sql = "select * from item where view = 1 limit 5";
			
			pstmt = dao.conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				ItemBean bean = new ItemBean();
				bean.setItemUid(rs.getInt("itemUid"));
				bean.setItemCode(rs.getString("itemCode"));
				bean.setItemName(rs.getString("itemName"));
				bean.setItemFile(rs.getString("itemFile"));
				bean.setItemFile_s(rs.getString("itemFile_s"));
				bean.setItemPrice(rs.getInt("itemPrice"));
				bean.setItemPoint(rs.getInt("itemPoint"));
				bean.setStock(rs.getInt("stock"));
				bean.setItemDate(rs.getString("itemDate"));
				bean.setStarPoint(rs.getDouble("starPoint"));
				bean.setPnum(rs.getInt("pnum"));
				v.add(bean);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return v;
		
	}

	//최근 본 상품 체크하기
	public void checkViewItem(String code) {
		dao.getCon();
		
		try {
			String sql = "update item set view='1' where itemCode = ?";
			pstmt = dao.conn.prepareStatement(sql);
			
			pstmt.setString(1, code);
			
			pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//최근 본 상품 지우기
	public void deleteViewItem() {
		dao.getCon();
		
		try {
			String sql = "update item set view='0'";
			pstmt = dao.conn.prepareStatement(sql);
			
			
			pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	//관리자 상품 품절
	public void itemSoldOut(int uid) {
		dao.getCon();
		
		try {
			String sql = "update item set sold=? where itemUid=? ";
			pstmt = dao.conn.prepareStatement(sql);
			
			pstmt.setString(1, "품절");
			pstmt.setInt(2, uid);
			
			pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//관리자 상품 판매
	public void itemSold(int uid) {
		dao.getCon();
		
		try {
			String sql = "update item set sold=? where itemUid=? ";
			pstmt = dao.conn.prepareStatement(sql);
			
			pstmt.setString(1, "판매");
			pstmt.setInt(2, uid);
			
			pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//관리자 상품 삭제
	public void itemDelete(int uid) {
		dao.getCon();
		
		try {
			String sql ="delete from item where itemUid=?";
			pstmt = dao.conn.prepareStatement(sql);
			
			pstmt.setInt(1, uid);
			
			pstmt.executeUpdate();
			
			pstmt.close();
			dao.conn.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//관리자 한개의 상품정보 수정용
	public ItemBean itemModify(int uid) {
		dao.getCon();
		
		ItemBean bean = new ItemBean();
		
		try {
			String sql = "select * from item where itemUid=?";
			pstmt = dao.conn.prepareStatement(sql);
			
			pstmt.setInt(1, uid);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				bean.setItemUid(rs.getInt("itemUid"));
				bean.setItemCode(rs.getString("itemCode"));
				bean.setItemName(rs.getString("itemName"));
				bean.setItemFile(rs.getString("itemFile"));
				bean.setItemFile_s(rs.getString("itemFile_s"));
				bean.setItemPrice(rs.getInt("itemPrice"));
				bean.setItemPoint(rs.getInt("itemPoint"));
				bean.setStock(rs.getInt("stock"));
				bean.setItemDate(rs.getString("itemDate"));
				bean.setCategory(rs.getString("category"));
				bean.setSold(rs.getString("sold"));
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return bean;
	}
	//게시글 수정
	public void updateItem(ItemBean bean) {
		dao.getCon();
		
		try {
			String sql = "update item set itemCode=?, itemName=?, itemPrice=?, itemPoint=?, stock=?, itemFile=?, itemFile_s=?, category=? where itemUid=? ";
									
			pstmt = dao.conn.prepareStatement(sql);
			
			pstmt.setString(1, bean.getItemCode());
			pstmt.setString(2, bean.getItemName());
			pstmt.setInt(3, bean.getItemPrice());
			pstmt.setInt(4, bean.getItemPoint());
			pstmt.setInt(5, bean.getStock());
			pstmt.setString(6, bean.getItemFile());
			pstmt.setString(7, bean.getItemFile_s());
			pstmt.setString(8, bean.getCategory());
			pstmt.setInt(9, bean.getItemUid());
		
			pstmt.executeUpdate();
			
			pstmt.close();
			dao.conn.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
}
