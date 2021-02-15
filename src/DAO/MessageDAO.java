package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import Model.ItemBean;
import Model.MessageBean;

public class MessageDAO {

	Statement stmt;
	PreparedStatement pstmt;
	ResultSet rs;
	
	DAO dao = new DAO();
	
	//쪽지 보내기
	public void messageWrite(String send_id, String recv_id, String content, String signdate ) {
		dao.getCon();
		
		try {
			String sql = "insert into message(send_id,recv_id,content,send_date) values(?,?,?,?)";
			
			pstmt = dao.conn.prepareStatement(sql);
			
			pstmt.setString(1, send_id);
			pstmt.setString(2, recv_id);
			pstmt.setString(3, content);
			pstmt.setString(4, signdate);
			
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

	//받은 쪽지함
	public Vector<MessageBean> viewMessage(String session_id){
		dao.getCon();
		
		Vector<MessageBean> v = new Vector<MessageBean>();
		
		
		try {
			String sql = "select * from message where recv_id=? and recv_del = 0";
			pstmt = dao.conn.prepareStatement(sql);
			
			pstmt.setString(1, session_id);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				MessageBean bean = new MessageBean();
				bean.setUid(rs.getInt("uid"));
				bean.setSend_id(rs.getString("send_id"));
				bean.setContent(rs.getString("content"));
				bean.setSend_date(rs.getString("send_date"));
				bean.setMsg_read(rs.getString("msg_read"));
				
				v.add(bean);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return v;
	}
	
	//보낸 쪽지함
	public Vector<MessageBean> sendMessage(String session_id){
		dao.getCon();
		
		Vector<MessageBean> v = new Vector<MessageBean>();
		
		
		try {
			String sql = "select * from message where send_id=? and send_del = 0";
			pstmt = dao.conn.prepareStatement(sql);
			
			pstmt.setString(1, session_id);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				MessageBean bean = new MessageBean();
				bean.setUid(rs.getInt("uid"));
				bean.setRecv_id(rs.getString("recv_id"));
				bean.setContent(rs.getString("content"));
				bean.setSend_date(rs.getString("send_date"));
				bean.setMsg_read(rs.getString("msg_read"));
				v.add(bean);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return v;
	}
	
	//받은 쪽지함에서 선택한 쪽지만 삭제처리(받은 쪽에서만)
	
	public void deleteCheckRecv(String s) {
		dao.getCon();
		
		int to = Integer.parseInt(s);
		
		try {
			
			String sql = "update message set recv_del = 1 where uid=?";
			pstmt = dao.conn.prepareStatement(sql);
			
			pstmt.setInt(1, to);
			
			pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//보낸 쪽지함에서 선택한 쪽지만 삭제처리(보낸 쪽에서만)
	
	public void deleteCheckSend(String s) {
		dao.getCon();
		
		int to = Integer.parseInt(s);
		
		try {
			
			String sql = "update message set send_del = 1 where uid=?";
			pstmt = dao.conn.prepareStatement(sql);
			
			pstmt.setInt(1, to);
			
			pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//받은 쪽지함 쪽지 내용 보기
	public Vector<MessageBean> viewMessageDetail(String session_id, int uid){
		dao.getCon();
		
		Vector<MessageBean> v = new Vector<MessageBean>();
		
		
		try {
			String sql = "select * from message where recv_id=? and recv_del = 0 and uid = ?";
			pstmt = dao.conn.prepareStatement(sql);
			
			pstmt.setString(1, session_id);
			pstmt.setInt(2, uid);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				MessageBean bean = new MessageBean();
				bean.setUid(rs.getInt("uid"));
				bean.setSend_id(rs.getString("send_id"));
				bean.setContent(rs.getString("content"));
				bean.setSend_date(rs.getString("send_date"));
				v.add(bean);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return v;
	}
	
	//쪽지 읽음처리
	public void MessageRead(int uid){
		dao.getCon();
		
		try {
			
			String sql = "update message set msg_read = 1 where uid=?";
			pstmt = dao.conn.prepareStatement(sql);
			
			pstmt.setInt(1, uid);
			
			pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	// 받은 메세지 개수 카운팅
	public int msgCountRecv(String id) {
		dao.getCon();
		
		int count = 0;
		
		try {
			
			String sql = "select count(*) as count from message where recv_id=? and recv_del = 0";
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
	
	// 보낸 메세지 개수 카운팅
	public int msgCountSend(String id) {
		dao.getCon();
		
		int count = 0;
		
		try {
			
			String sql = "select count(*) as count from message where send_id=? and send_del = 0";
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
	
	public int recvCount(String recv_id){
		dao.getCon();
		
		int count = 0;
		
		try {
			
			String sql = "select count(*) as count from member where id=?";
			pstmt = dao.conn.prepareStatement(sql);
			
			pstmt.setString(1, recv_id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				count = rs.getInt("count");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return count;
	}
}
