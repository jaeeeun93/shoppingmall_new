package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import Model.CommentBean;
import Model.TradeBean;


public class CommentDAO {
	Statement stmt;
	PreparedStatement pstmt;
	ResultSet rs;
	
	DAO dao = new DAO();
	
	//댓글 작성시 부모 fid값 생성
	public int fidnumber () {
		dao.getCon();
		int fid = 0;
		
		try {
			String sql = "select max(fid) as fid_max from comment";
			pstmt = dao.conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				if(rs.getInt("fid_max")>0) {
					fid = rs.getInt("fid_max")+1;
				}else {
					fid = 1;
				}
			}
			
			pstmt.close();
			dao.conn.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return fid;
	}
	
	//댓글 작성
	public void insertComment(CommentBean bean) {
		dao.getCon();
		
		try {
			String sql = "insert into comment (view_uid,replyid,replycomment,replydate,fid) values(?,?,?,?,?)";
			
			pstmt = dao.conn.prepareStatement(sql);
			
			pstmt.setInt(1, bean.getView_uid());
			pstmt.setString(2, bean.getReplyid());
			pstmt.setString(3, bean.getReplycomment());
			pstmt.setString(4, bean.getReplydate());
			pstmt.setInt(5, bean.getFid());
			
			pstmt.executeUpdate();
			
			pstmt.close();
			dao.conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//댓글 보기
	public ArrayList<CommentBean> commentview(int uid) {
		dao.getCon();
		
		ArrayList<CommentBean> v = new ArrayList<CommentBean>();
		
		try {
			String sql = "select * from comment where view_uid=? order by fid ";
			
			pstmt = dao.conn.prepareStatement(sql);
			pstmt.setInt(1, uid);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				CommentBean bean = new CommentBean();
				
				bean.setUid(rs.getInt("uid"));
				bean.setReplyid(rs.getString("replyid"));
				bean.setReplycomment(rs.getString("replycomment"));
				bean.setReplydate(rs.getString("replydate"));
				bean.setFid(rs.getInt("fid"));
				bean.setCommenttype(rs.getString("commenttype"));
				
				v.add(bean);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return v;
	}

	//대댓글 자식 thread값 지정
	public char commentThread (int fid) {
		dao.getCon();
		char thread = 'A';
		
		try {
			String sql = "select thread from comment where fid=? order by thread desc limit 0,1";
			pstmt = dao.conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			pstmt.setInt(1, fid);
			
			if(rs.next()) {
				thread = rs.getString("thread").charAt(0);
			}
				
			pstmt.close();
			dao.conn.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return thread;
	}
	
	//대댓글 작성
	public void insertRecomment(CommentBean bean ,char thread) {
		dao.getCon();
		
		try {
			String sql = "insert into comment (view_uid,replyid,replycomment,replydate,fid,commenttype,thread) values (?,?,?,?,?,?,'"+thread+"')";
			pstmt = dao.conn.prepareStatement(sql);

			pstmt.setInt(1, bean.getView_uid());
			pstmt.setString(2, bean.getReplyid());
			pstmt.setString(3, bean.getReplycomment());
			pstmt.setString(4, bean.getReplydate());
			pstmt.setInt(5, bean.getFid());
			pstmt.setString(6, "1");
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();			
		}
	}
	
	//게시글삭제시 해당하는 댓글삭제
	public void viewcommentDelete(int uid) {
		dao.getCon();
		
		try {
			String sql = "delete from comment where view_uid=?";
			pstmt = dao.conn.prepareStatement(sql);
			
			pstmt.setInt(1, uid);
			
			pstmt.executeUpdate();
			
			pstmt.close();
			dao.conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	//댓글삭제
	public void CommentDelete(int uid) {
		dao.getCon();
		
		try {
			String sql = "delete from comment where uid=?";
			pstmt = dao.conn.prepareStatement(sql);
			
			pstmt.setInt(1, uid);
			
			pstmt.executeUpdate();
			
			pstmt.close();
			dao.conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	//수정용 댓글 보기
	public CommentBean modifyComment(int uid) {
		dao.getCon();
		
		CommentBean bean = new CommentBean();
		
		try {
			String sql = "select * from comment where uid=?";
			
			pstmt = dao.conn.prepareStatement(sql);
			pstmt.setInt(1, uid);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				bean.setUid(rs.getInt("uid"));
				bean.setReplyid(rs.getString("replyid"));
				bean.setReplycomment(rs.getString("replycomment"));
				bean.setReplydate(rs.getString("replydate"));
				bean.setFid(rs.getInt("fid"));
				bean.setCommenttype(rs.getString("commenttype"));
				
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return bean;
	}
	
	//댓글 수정
	public void CommentUpdate(CommentBean bean) {
		dao.getCon();
		
		try {
			String sql="update comment set replycomment=? where uid=?";
			pstmt = dao.conn.prepareStatement(sql);
			
			pstmt.setString(1, bean.getReplycomment());
			pstmt.setInt(2, bean.getUid());
			
			pstmt.executeUpdate();
			
			pstmt.close();
			dao.conn.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//구매 게시글 수
	public int commentCount(int uid) {
		dao.getCon();
		int commentCount = 0;
		
		try {
			String sql = "select count(*) from comment where view_uid=?";
			pstmt = dao.conn.prepareStatement(sql);
			
			pstmt.setInt(1, uid);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				commentCount = rs.getInt(1);
			}
			
			pstmt.close();
			dao.conn.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return commentCount;
	}
	
	

}
