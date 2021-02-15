//2020.12.01만듦
package Model;

public class ReviewBean {
	private int uid;
	private String id;
	private String subject;
	private int ref;
	private String comment;
	private String file;
	private String file_s;
	//2020.12.02추가
	private String signdate;
	
	public String getSigndate() {
		return signdate;
	}
	public void setSigndate(String signdate) {
		this.signdate = signdate;
	}
	public int getUid() {
		return uid;
	
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public int getRef() {
		return ref;
	}
	public void setRef(int ref) {
		this.ref = ref;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getFile() {
		return file;
	}
	public void setFile(String file) {
		this.file = file;
	}
	public String getFile_s() {
		return file_s;
	}
	public void setFile_s(String file_s) {
		this.file_s = file_s;
	}
	
}
