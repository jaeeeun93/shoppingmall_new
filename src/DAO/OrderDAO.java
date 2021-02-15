package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;

import Model.ItemBean;
import Model.OrderBean;



public class OrderDAO {
	Statement stmt;
	PreparedStatement pstmt;
	ResultSet rs;
	
	DAO dao = new DAO();
	
	//주문리스트에 등록
	/*
	 * public void insertOrderBuy(OrderBean bean) { dao.getCon();
	 * 
	 * Vector<OrderBean> v = new Vector<OrderBean>();
	 * 
	 * try { String sql =
	 * "insert into order2 (id, name, email, phone, cname, zipcode, zip1, zip2, zip3, cphone, pick, cartFile_s, itemName, itemPrice, coupon, totalPrice, point, cartUid) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"
	 * ;
	 * 
	 * pstmt = dao.conn.prepareStatement(sql);
	 * 
	 * pstmt.setString(1, bean.getId()); pstmt.setString(2, bean.getName());
	 * pstmt.setString(3, bean.getEmail()); pstmt.setString(4, bean.getPhone());
	 * pstmt.setString(5, bean.getCname()); pstmt.setString(6, bean.getZipcode());
	 * pstmt.setString(7, bean.getZip1()); pstmt.setString(8, bean.getZip2());
	 * pstmt.setString(9, bean.getZip3()); pstmt.setString(10, bean.getCphone());
	 * pstmt.setString(11, bean.getPick()); pstmt.setString(12,
	 * bean.getCartFile_s()); pstmt.setString(13, bean.getItemName());
	 * pstmt.setInt(14, bean.getItemPrice()); pstmt.setString(15, bean.getCoupon());
	 * pstmt.setInt(16, bean.getTotalPrice()); pstmt.setString(17, bean.getPoint());
	 * pstmt.setInt(18, bean.getCartUid());
	 * 
	 * pstmt.executeUpdate();
	 * 
	 * }catch(Exception e) { e.printStackTrace();
	 * 
	 * }finally { try { pstmt.close(); dao.conn.close(); }catch(Exception e) {
	 * e.printStackTrace(); } } }
	 */
	//주문리스트에 등록
	public void insertOrderBuy(OrderBean bean) {
		dao.getCon();
		
		try {
			String sql = "insert into order2 (id, name, email, phone, cname, zipcode, zip1, zip2, zip3, cphone, totalPrice, orderId, orderDate, pick,coupon) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			
			pstmt = dao.conn.prepareStatement(sql);
			
			pstmt.setString(1, bean.getId());
			pstmt.setString(2, bean.getName());
			pstmt.setString(3, bean.getEmail());
			pstmt.setString(4, bean.getPhone());
			pstmt.setString(5, bean.getCname());
			pstmt.setString(6, bean.getZipcode());
			pstmt.setString(7, bean.getZip1());
			pstmt.setString(8, bean.getZip2());
			pstmt.setString(9, bean.getZip3());
			pstmt.setString(10, bean.getCphone());
			pstmt.setInt(11, bean.getTotalPrice());
			pstmt.setString(12, bean.getOrderId());
			pstmt.setString(13, bean.getOrderDate());
			pstmt.setString(14, bean.getPick());
			pstmt.setString(15, bean.getCoupon());
			
			
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

	// 재고량 감소처리
	 public void minusStock(OrderBean bean, String b ) {
		 
		 dao.getCon();
	 
	 try { String sql = "update item set stock = stock -1 where itemCode = ?" ;
	 
	 pstmt = dao.conn.prepareStatement(sql);
	 
	 pstmt.setString(1,b );
	 
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
	
	// 주문 / 배송 조회시 정보 입력
	 public void insertTempOrder(ItemBean bean, String itemName, String cartFile_s, int itemPrice, String session_id, OrderBean bean1, String itemCode, String orderId) {
		 
		 dao.getCon();
	 
	 try { String sql = "insert into tempOrder2 (itemName, cartFile_s, itemPrice, id, tOrderDate, itemCode, orderId) values (?,?,?,?,?,?,?)";
	 
	 pstmt = dao.conn.prepareStatement(sql);
	 
	 pstmt.setString(1, itemName);
	 pstmt.setString(2, cartFile_s);
	 pstmt.setInt(3, itemPrice );
	 pstmt.setString(4, session_id );
	 pstmt.setString(5, bean1.getOrderDate());
	 pstmt.setString(6, itemCode);
	 pstmt.setString(7, orderId);
	 
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
	 
	// 주문 / 배송 조회시 정보 조회
	 public Vector<OrderBean> viewTempOrder2(String id, OrderBean bean) {
		 dao.getCon();
	 
		 Vector<OrderBean> v = new Vector<OrderBean>();
	 try {
		 
		 String sql = "select * from tempOrder2 where id=? order by tempUid desc";
	 
	 pstmt = dao.conn.prepareStatement(sql);
	 
	 pstmt.setString(1, id);
	 
	 rs = pstmt.executeQuery();
	 
	 while(rs.next()) {
		 	bean = new OrderBean();
			bean.setItemPrice(rs.getInt("itemPrice"));
			bean.setItemName(rs.getString("itemName"));
			bean.setCartFile_s(rs.getString("cartFile_s"));
			bean.setOrderDate(rs.getString("tOrderDate"));
			bean.setItemCode(rs.getString("itemCode"));
			bean.setOrderId(rs.getString("orderId"));
			bean.setDelivery(rs.getString("delivery"));
			bean.setDeliveryDate(rs.getString("deliveryDate"));
			v.add(bean);
		}
	 
	 }catch(Exception e) {
		 e.printStackTrace();
	 }
	 return v;
	}
	
	// 주문 / 배송 취소시 정보 삭제
	public void deleteTempOrder(String orderId ) {
			 
			 dao.getCon();
		 
		 try { String sql = "delete from tempOrder2 where orderId = ?" ;
		 
		 pstmt = dao.conn.prepareStatement(sql);
		 
		 pstmt.setString(1,orderId );
		 
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
	 
	 
	 //주문/배송조회
	 public Vector<OrderBean> viewOrderList(String id,OrderBean bean) {
		dao.getCon();
		
		Vector<OrderBean> v = new Vector<OrderBean>();
		
		try {
			String sql = "select * from order2 where id=? order by orderUid desc";
			pstmt = dao.conn.prepareStatement(sql);
			
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				bean = new OrderBean();
				bean.setName(rs.getString("name"));
				bean.setId(rs.getString("id"));
				bean.setOrderDate(rs.getString("orderDate"));
				bean.setZipcode("zipcode");
				bean.setZip1("zip1");
				bean.setZip2("zip2");
				bean.setZip3("zip3");
				bean.setDelivery(rs.getString("delivery"));
				v.add(bean);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return v;
	}
	 
	//주문 상세보기
	 public OrderBean viewOrderDetail(String orderId) {
		dao.getCon();
		
		OrderBean bean = new OrderBean();
		
		try {
			String sql = "select * from order2 where orderId=? order by orderUid desc";
			pstmt = dao.conn.prepareStatement(sql);
			
			pstmt.setString(1, orderId);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				bean.setName(rs.getString("name"));
				bean.setId(rs.getString("id"));
				bean.setOrderDate(rs.getString("orderDate"));
				bean.setZipcode(rs.getString("zipcode"));
				bean.setZip1(rs.getString("zip1"));
				bean.setZip2(rs.getString("zip2"));
				bean.setZip3(rs.getString("zip3"));
				bean.setCname(rs.getString("cname"));
				bean.setCphone(rs.getString("cphone"));
				bean.setPick(rs.getString("pick"));
				bean.setTotalPrice(rs.getInt("totalPrice"));
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return bean;
	}
		 
	// 리뷰 가능한 상품 불러오기
	 public Vector<OrderBean> viewReviewItem(String id, OrderBean bean, String itemCode) {
		 dao.getCon();
	 
		 Vector<OrderBean> v = new Vector<OrderBean>();
	 try {
		 
		 String sql = "select * from tempOrder2 where id=? and itemCode = ? order by tempUid desc";
	 
	 pstmt = dao.conn.prepareStatement(sql);
	 
	 pstmt.setString(1, id);
	 pstmt.setString(2, itemCode);
	 
	 rs = pstmt.executeQuery();
	 
	 while(rs.next()) {
		 	bean = new OrderBean();
			bean.setItemPrice(rs.getInt("itemPrice"));
			bean.setItemName(rs.getString("itemName"));
			bean.setCartFile_s(rs.getString("cartFile_s"));
			bean.setOrderDate(rs.getString("tOrderDate"));
			bean.setItemCode(rs.getString("itemCode"));
			bean.setOrderId(rs.getString("orderId"));
			v.add(bean);
		}
	 
	 }catch(Exception e) {
		 e.printStackTrace();
	 }
	 return v;
	}
	 
	// 포인트 증가처리
	 public void insertPoint(String id, String itemPoint) {
		 
		 dao.getCon();
	 
	 try {
	
		 String sql = "update member set point = point +? where id = ?" ;
	 
	 pstmt = dao.conn.prepareStatement(sql);
	 
	 pstmt.setString(1,itemPoint );
	 pstmt.setString(2,id );
	 
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

	// 장바구니 주문시 포인트 증가처리
	 public void insertCartPoint(String id, String itemPoint) {
		 
		 dao.getCon();
	 
	 try {
	
		 String sql = "update member set point = point +? where id = ?" ;
	 
	 pstmt = dao.conn.prepareStatement(sql);
	 
	 pstmt.setString(1,itemPoint );
	 pstmt.setString(2,id );
	 
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

	 // 관리자 주문상세 시 상품목록
	 public Vector<OrderBean> ViewTempOrder2(String orderId, OrderBean bean) {
		 dao.getCon();
		 
		 Vector<OrderBean> v = new Vector<OrderBean>();
		 try {
			 
			 String sql = "select * from tempOrder2 where orderId=? order by tempUid desc";
			 
			 pstmt = dao.conn.prepareStatement(sql);
			 
			 pstmt.setString(1, orderId);
			 
			 rs = pstmt.executeQuery();
			 
			 while(rs.next()) {
				 bean = new OrderBean();
				 bean.setItemPrice(rs.getInt("itemPrice"));
				 bean.setItemName(rs.getString("itemName"));
				 bean.setCartFile_s(rs.getString("cartFile_s"));
				 bean.setOrderDate(rs.getString("tOrderDate"));
				 bean.setItemCode(rs.getString("itemCode"));
				 bean.setOrderId(rs.getString("orderId"));
				 v.add(bean);
			 }
			 
		 }catch(Exception e) {
			 e.printStackTrace();
		 }
		 return v;
	 }
	//총 주문의 갯수
	public int AllcountOrder() {
		dao.getCon();
		int count = 0;
		
		try {
			String sql = "select count(*) from order2";
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

	//관리자 전체 주문불러오기
	public Vector<OrderBean> getAllOrder(int startRow, int endRow) {
		dao.getCon();
			
		Vector<OrderBean> v = new Vector<OrderBean>();
		
		try {
			String sql = "select * from order2 order by orderUid desc limit ?,?";
			
			pstmt = dao.conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				OrderBean bean = new OrderBean();
				
				bean.setOrderUid(rs.getInt("orderUid"));
				bean.setId(rs.getString("id"));
				bean.setZipcode(rs.getString("zipcode"));
				bean.setZip1(rs.getString("zip1"));
				bean.setZip2(rs.getString("zip2"));
				bean.setZip3(rs.getString("zip3"));
				bean.setPhone(rs.getString("phone"));
				bean.setOrderId(rs.getString("orderid"));
				bean.setOrderDate(rs.getString("orderdate"));
				bean.setName(rs.getString("name"));
				bean.setEmail(rs.getString("email"));
				bean.setCname(rs.getString("cname"));
				bean.setCphone(rs.getString("cphone"));
				bean.setPick(rs.getString("pick"));
				bean.setTotalPrice(rs.getInt("totalprice"));
				bean.setDelivery(rs.getString("delivery"));
				
				v.add(bean);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return v;
	}
	
	//관리자 주문내역 배송
	public void orderDelivery(int uid) {
		dao.getCon();

		try {
			String sql = "update order2 set delivery=? where orderUid=? ";

			pstmt = dao.conn.prepareStatement(sql);

			pstmt.setString(1, "배송");
			pstmt.setInt(2, uid);
			pstmt.executeUpdate();
			
			pstmt.close();
			dao.conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	//관리자 주문내역 배송중
	public void orderDeliveryStart(int uid) {
		dao.getCon();
		
		try {
			String sql = "update order2 set delivery=? where orderUid=? ";
			
			pstmt = dao.conn.prepareStatement(sql);
			
			pstmt.setString(1, "배송중");
			pstmt.setInt(2, uid);
			pstmt.executeUpdate();
			
			pstmt.close();
			dao.conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	//관리자 주문내역 배송완료
	public void orderDeliveryOk(int uid) {
		dao.getCon();
		
		try {
			String sql = "update order2 set delivery=? where orderUid=? ";
			
			pstmt = dao.conn.prepareStatement(sql);
			
			pstmt.setString(1, "배송완료");
			pstmt.setInt(2, uid);
			pstmt.executeUpdate();
			
			pstmt.close();
			dao.conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	//관리자 temporder2내역 배송완료
	public void temporderDeliveryOK(String id, String date) {
		dao.getCon();
		
		try {
			String sql = "update temporder2 set delivery=?, deliveryDate = ?  where orderId=?";
			
			pstmt = dao.conn.prepareStatement(sql);
			
			pstmt.setString(1, "배송완료");
			pstmt.setString(2, date );
			pstmt.setString(3, id);
			pstmt.executeUpdate();
			
			pstmt.close();
			dao.conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	//관리자 temporder2내역 배송중
	public void temporderDeliveryStart(String id, String date) {
		dao.getCon();
		
		try {
			String sql = "update temporder2 set delivery=?, deliveryDate = ?  where orderId=?";
			
			pstmt = dao.conn.prepareStatement(sql);
			
			pstmt.setString(1, "배송중");
			pstmt.setString(2, date );
			pstmt.setString(3, id);
			pstmt.executeUpdate();
			
			pstmt.close();
			dao.conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	//관리자 주문내역 배송취소
	public void orderDeliveryUndo(int uid) {
		dao.getCon();
		
		try {
			String sql = "update order2 set delivery=? where orderUid=? ";
			
			pstmt = dao.conn.prepareStatement(sql);
			
			pstmt.setString(1, "배송취소");
			pstmt.setInt(2, uid);
			pstmt.executeUpdate();
			
			pstmt.close();
			dao.conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	//관리자 temporder2내역 배송취소
	public void temporderDeliveryUndo(String id) {
		dao.getCon();
		
		try {
			String sql = "update temporder2 set delivery=?, deliveryDate = ''  where orderId=?";
			
			pstmt = dao.conn.prepareStatement(sql);
			
			pstmt.setString(1, "배송준비중");
			pstmt.setString(2, id);
			pstmt.executeUpdate();
			
			pstmt.close();
			dao.conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	 
	//총 주문액
	public int totalsales() {
		dao.getCon();
		int totalSale = 0;
		try {
			String sql = "select sum(totalPrice) from order2";
			pstmt = dao.conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				totalSale = rs.getInt("sum(totalPrice)");
			}
			
			pstmt.close();
			dao.conn.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return totalSale;
	}
	

	//일일 매출
	 public Vector<OrderBean> viewDaySale(String date, OrderBean bean) {
		dao.getCon();
		
		Vector<OrderBean> v = new Vector<OrderBean>();
		
		try {
			String sql = "select * from order2 where left(orderDate,8) = ?";
			pstmt = dao.conn.prepareStatement(sql);
			
			pstmt.setString(1, date);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				bean = new OrderBean();
				bean.setOrderUid(rs.getInt("orderUid"));
				bean.setName(rs.getString("name"));
				bean.setId(rs.getString("id"));
				bean.setOrderDate(rs.getString("orderDate"));
				bean.setZipcode(rs.getString("zipcode"));
				bean.setZip1(rs.getString("zip1"));
				bean.setZip2(rs.getString("zip2"));
				bean.setZip3(rs.getString("zip3"));
				bean.setCname(rs.getString("cname"));
				bean.setCphone(rs.getString("cphone"));
				bean.setPick(rs.getString("pick"));
				bean.setTotalPrice(rs.getInt("totalPrice"));
				bean.setCoupon(rs.getString("coupon"));
				bean.setPhone(rs.getString("phone"));
				bean.setDelivery(rs.getString("delivery"));
				v.add(bean);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return v;
	}
	 
	//일별 주문 개수
	public int countDaySale(String date) {
		dao.getCon();
		int count = 0;
		
		try {
			String sql = "select count(*) from order2 where left(orderDate,8) = "+date+"";
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
	
	//일간 주문 갯수
	public int countDay2Sale(String date, String date2) {
		dao.getCon();
		int count = 0;
		
		try {
			String sql = "select count(*) from order2 where left(orderDate,8) >= "+date+" and left(orderDate,8) <= "+date2+"";
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
	
	//월간 주문 갯수
	public int countDay3Sale(String date) {
		dao.getCon();
		int count = 0;
		
		try {
			String sql = "select count(*) from order2 where left(orderDate,6) = "+date+"";
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
	
	
	
	//일간 매출
	 public Vector<OrderBean> viewDay2Sale(String date,String date2, OrderBean bean) {
		dao.getCon();
		
		Vector<OrderBean> v = new Vector<OrderBean>();
		
		try {
			String sql = "select * from order2 where left(orderDate,8) >= ? and left(orderDate,8) <= ? order by orderDate asc";
			pstmt = dao.conn.prepareStatement(sql);
			
			pstmt.setString(1, date);
			pstmt.setString(2, date2);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				bean = new OrderBean();
				bean.setOrderUid(rs.getInt("orderUid"));
				bean.setName(rs.getString("name"));
				bean.setId(rs.getString("id"));
				bean.setOrderDate(rs.getString("orderDate"));
				bean.setZipcode(rs.getString("zipcode"));
				bean.setZip1(rs.getString("zip1"));
				bean.setZip2(rs.getString("zip2"));
				bean.setZip3(rs.getString("zip3"));
				bean.setCname(rs.getString("cname"));
				bean.setCphone(rs.getString("cphone"));
				bean.setPick(rs.getString("pick"));
				bean.setTotalPrice(rs.getInt("totalPrice"));
				bean.setCoupon(rs.getString("coupon"));
				bean.setPhone(rs.getString("phone"));
				bean.setDelivery(rs.getString("delivery"));
				v.add(bean);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return v;
	}
	 
	//월간 매출
	 public Vector<OrderBean> viewDay3Sale(String date, OrderBean bean) {
		dao.getCon();
		
		Vector<OrderBean> v = new Vector<OrderBean>();
		
		try {
			String sql = "select * from order2 where left(orderDate,6) = ? order by orderDate asc";
			pstmt = dao.conn.prepareStatement(sql);
			
			pstmt.setString(1, date);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				bean = new OrderBean();
				bean.setOrderUid(rs.getInt("orderUid"));
				bean.setName(rs.getString("name"));
				bean.setId(rs.getString("id"));
				bean.setOrderDate(rs.getString("orderDate"));
				bean.setZipcode(rs.getString("zipcode"));
				bean.setZip1(rs.getString("zip1"));
				bean.setZip2(rs.getString("zip2"));
				bean.setZip3(rs.getString("zip3"));
				bean.setCname(rs.getString("cname"));
				bean.setCphone(rs.getString("cphone"));
				bean.setPick(rs.getString("pick"));
				bean.setTotalPrice(rs.getInt("totalPrice"));
				bean.setCoupon(rs.getString("coupon"));
				bean.setPhone(rs.getString("phone"));
				bean.setDelivery(rs.getString("delivery"));
				v.add(bean);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return v;
	}
	 
	//월간 총 주문액
	public int totalsale3(String date) {
		dao.getCon();
		int totalSale = 0;
		try {
			String sql = "select sum(totalPrice) from order2 where left(orderDate,6) = "+date+"";
			pstmt = dao.conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				totalSale = rs.getInt("sum(totalPrice)");
			}
			
			pstmt.close();
			dao.conn.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return totalSale;
	}
	
	//일간 총 주문액
	public int totalsale2(String date, String date2) {
		dao.getCon();
		int totalSale = 0;
		try {
			String sql = "select sum(totalPrice) from order2 where left(orderDate,8) >= "+date+" and left(orderDate,8) <= "+date2+" order by orderDate asc";
			pstmt = dao.conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				totalSale = rs.getInt("sum(totalPrice)");
			}
			
			pstmt.close();
			dao.conn.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return totalSale;
	}
	
	//일 총 주문액
	public int totalsale(String date) {
		dao.getCon();
		int totalSale = 0;
		try {
			String sql = "select sum(totalPrice) from order2 where left(orderDate,8) = "+date+"";
			pstmt = dao.conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				totalSale = rs.getInt("sum(totalPrice)");
			}
			
			pstmt.close();
			dao.conn.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return totalSale;
	}
}
