package Model;

public class CommentBean {

	private int uid;
	private int view_uid;
	private String replyid;
	private String replycomment;
	private String replydate;
	private int fid;
	private String thread;
	private String commenttype;
	
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public int getView_uid() {
		return view_uid;
	}
	public void setView_uid(int view_uid) {
		this.view_uid = view_uid;
	}
	public String getReplyid() {
		return replyid;
	}
	public void setReplyid(String replyid) {
		this.replyid = replyid;
	}
	public String getReplycomment() {
		return replycomment;
	}
	public void setReplycomment(String replycommment) {
		this.replycomment = replycommment;
	}
	public String getReplydate() {
		return replydate;
	}
	public void setReplydate(String replydate) {
		this.replydate = replydate;
	}
	public int getFid() {
		return fid;
	}
	public void setFid(int fid) {
		this.fid = fid;
	}
	public String getThread() {
		return thread;
	}
	public void setThread(String thread) {
		this.thread = thread;
	}
	public String getCommenttype() {
		return commenttype;
	}
	public void setCommenttype(String commenttype) {
		this.commenttype = commenttype;
	}
	
	
	
}
