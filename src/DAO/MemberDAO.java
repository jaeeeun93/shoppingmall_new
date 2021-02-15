package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Model.MemberBean;
import Model.OrderBean;

public class MemberDAO {
	Statement stmt;
	PreparedStatement pstmt;
	ResultSet rs;
	
	DAO dao = new DAO();
	
	//회원가입
	public void insertMember(MemberBean bean) {//Memberinsert 서블릿에서 불러옴
		dao.getCon();//db연결
		
		try {//db에 값넣고
			
			String sql = "insert into member (id, password, name, email, level, zipcode, zip1, zip2, zip3, phone, joinDate) values(?,?,?,?,?,?,?,?,?,?,?)";
			
			pstmt = dao.conn.prepareStatement(sql);//sql구문을 pstmt에 넣는다.
			
			pstmt.setString(1, bean.getId());//db에 bean에 넣엇던 값을 set해준다.
			pstmt.setString(2, bean.getPass());//앞에 숫자는 물음표 순서 두번쨰는 value값이다!
			pstmt.setString(3, bean.getName());
			pstmt.setString(4, bean.getEmail());
			pstmt.setString(5, "1");//level 기본값 "1"
			pstmt.setString(6, bean.getZipcode());
			pstmt.setString(7, bean.getZip1());
			pstmt.setString(8, bean.getZip2());
			pstmt.setString(9, bean.getZip3());
			pstmt.setString(10, bean.getPhone());
			pstmt.setString(11, bean.getJoinDate());
			
			pstmt.executeUpdate();//update해주고
			
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
	
	//로그인 회원 매칭(로그인)
	public int loginMember(String id,String pass){
		dao.getCon();
		
		int total_record = 0;
		
		try {
			String sql = "select count(*) from member where id=? and password=? and userDel != '탈퇴'";//id랑 password에 받은값을 넣고
			
			pstmt = dao.conn.prepareStatement(sql);
			
			pstmt.setString(1, id);
			pstmt.setString(2, pass);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				total_record = rs.getInt(1);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return total_record;
	}
	
	public int withdrawId(String id, String pass) {
		dao.getCon();
		
		int cnt = 0;//cnt변수를 쓰기위해 선언 return 될떄는 dao에서 받음
		
		try {
			String sql = "select count(*) as cnt from member where id=? and password=? and userDel='탈퇴'";
			//cnt로 쓰기 위해 as cnt를 씀 아니면 count로 db에서 받으니까 count를 리턴해주면 됨
			pstmt= dao.conn.prepareStatement(sql);
			
			pstmt.setString(1, id);//물음표 있으면 무조건 적어줘야함!!
			pstmt.setString(2, pass);
			
			rs= pstmt.executeQuery();//rs에 담음
			
			if(rs.next()) {//db에 있는 것들을 불러와 찾는다.
				cnt = rs.getInt("cnt");//db실행 후 cnt값을 cnt에 담는다.
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return cnt;
	}
	
	//로그인 회원정보 불러오기
	public MemberBean loginSelect(String id, String pass) {
		dao.getCon();
		
		MemberBean bean = new MemberBean();
		
		try {
			String sql = "select * from member where id=? and password=?";
			
			pstmt = dao.conn.prepareStatement(sql);
			
			pstmt.setString(1, id);
			pstmt.setString(2, pass);
			
			rs = pstmt.executeQuery();//rs에 db넣고
			
			if(rs.next()) {
				bean.setId(rs.getString("id"));//rs.getString이니까 db에 있는 id 가져와서 bean에 set해준다.
				bean.setName(rs.getString("name"));//bean에 넣어서 dao에서 쓰기 위해서!
				bean.setLevel(rs.getString("level"));//그렇기때문에 위에 bean객체를 선언함 여러객체를 담을때 필요!
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return bean;
		
	}
	
	//한명의 회원정보 불러오기
	public MemberBean viewMember(String id) {//변수명은 달라도 상관x
		dao.getCon();
		
		MemberBean bean = new MemberBean();
		
		try {
			String sql = "select * from member where id=?";
			pstmt = dao.conn.prepareStatement(sql);
			
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				bean.setId(rs.getString("id"));
				bean.setPass(rs.getString("password"));
				bean.setName(rs.getString("name"));
				bean.setEmail(rs.getString("email"));
				bean.setZipcode(rs.getString("zipcode"));
				bean.setZip1(rs.getString("zip1"));
				bean.setZip2(rs.getString("zip2"));
				bean.setZip3(rs.getString("zip3"));
				bean.setPhone(rs.getString("phone"));
				bean.setPoint(rs.getString("point"));
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return bean;
	}
	
	//관리자 수정 한명의 회원정보 불러오기
	public MemberBean adminviewMember(String id) {
		dao.getCon();
		//System.out.println(id);
		MemberBean bean = new MemberBean();
		try {
			String sql = "select * from member where id=?";
			pstmt = dao.conn.prepareStatement(sql);
			
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {//db값을 가져와서 씀
				bean.setId(rs.getString("id"));
				bean.setPass(rs.getString("password"));
				bean.setName(rs.getString("name"));
				bean.setEmail(rs.getString("email"));
				bean.setLevel(rs.getString("level"));
				bean.setZipcode(rs.getString("zipcode"));
				bean.setZip1(rs.getString("zip1"));
				bean.setZip2(rs.getString("zip2"));
				bean.setZip3(rs.getString("zip3"));
				bean.setPhone(rs.getString("phone"));
				bean.setPoint(rs.getString("point"));
				bean.setUserDel(rs.getString("userDel"));
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return bean;
	}
	
	//일반회원 수정
	public void modifyMember(MemberBean bean) {
		dao.getCon();
		
		try {
			String sql = "update member set password=?, name=?, email=?, zipcode=?, zip1=?, zip2=?, zip3=?, phone=? where id=? ";
			pstmt = dao.conn.prepareStatement(sql);//아이디가 111a인것을 update set하겠다.
			
			pstmt.setString(1, bean.getPass());
			pstmt.setString(2, bean.getName());
			pstmt.setString(3, bean.getEmail());
			pstmt.setString(4, bean.getZipcode());
			pstmt.setString(5, bean.getZip1());
			pstmt.setString(6, bean.getZip2());
			pstmt.setString(7, bean.getZip3());
			pstmt.setString(8, bean.getPhone());
			pstmt.setString(9, bean.getId());
			
			pstmt.executeUpdate();
			
			pstmt.close();
			dao.conn.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	//관리자 회원수정
	public void adminmodifyMember(MemberBean bean) {
		dao.getCon();
		
		try {
			String sql = "update member set name=?, level=?, point=? where id=? ";
			
			pstmt = dao.conn.prepareStatement(sql);
			
			pstmt.setString(1, bean.getName());
			pstmt.setString(2, bean.getLevel());
			pstmt.setString(3, bean.getPoint());
			pstmt.setString(4, bean.getId());
		
			pstmt.executeUpdate();
			
			pstmt.close();
			dao.conn.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	//전체 회원 불러오기
	public ArrayList<MemberBean> getAllmember(int startRow, int endRow) {
		dao.getCon();
			
		ArrayList<MemberBean> v = new ArrayList<MemberBean>();
	  //Vector 대신에 ArryList를 쓸수있다.
	  //ArryList<MemberBean> v = new ArryList<MemberBean>();
		
		try {
			String sql = "select * from member order by joinDate desc limit ?,?";
			
			pstmt = dao.conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				MemberBean bean = new MemberBean();
				
				bean.setId(rs.getString("id"));
				bean.setPass(rs.getString("password"));
				bean.setName(rs.getString("name"));
				bean.setEmail(rs.getString("email"));
				bean.setLevel(rs.getString("level"));
				bean.setZipcode(rs.getString("zipcode"));
				bean.setZip1(rs.getString("zip1"));
				bean.setZip2(rs.getString("zip2"));
				bean.setZip3(rs.getString("zip3"));
				bean.setUserDel(rs.getString("userDel"));
				bean.setPhone(rs.getString("phone"));
				bean.setPoint(rs.getString("point"));
				bean.setJoinDate(rs.getString("joinDate"));
				
				v.add(bean);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return v;
		
	}
	
	//총 회원의 갯수
	public int getAllcount() {
		dao.getCon();
		int count = 0;
		
		try {
			String sql = "select count(*) from member";
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
	
	//관리자 회원 삭제
	public void WithdrawMember(MemberBean bean) {
		dao.getCon();

		try {
			String sql = "update member set userDel=? where id = ?";

			pstmt = dao.conn.prepareStatement(sql);

			pstmt.setString(1, "탈퇴");
			pstmt.setString(2, bean.getId());

			pstmt.executeUpdate();
			
			pstmt.close();
			dao.conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	//회원검색
	public List<MemberBean> searchMem(String field,String search) {
		dao.getCon();
		
		List<MemberBean> v = new ArrayList<>();
		
		try {
			String sql = "select * from member where "+field+" like '%" +search+ "%'";
			
			stmt = dao.conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				MemberBean bean = new MemberBean();
				
				bean.setId(rs.getString("id"));
				bean.setPass(rs.getString("password"));
				bean.setName(rs.getString("name"));
				bean.setEmail(rs.getString("email"));
				bean.setLevel(rs.getString("level"));
				bean.setZipcode(rs.getString("zipcode"));
				bean.setZip1(rs.getString("zip1"));
				bean.setZip2(rs.getString("zip2"));
				bean.setZip3(rs.getString("zip3"));
				bean.setUserDel(rs.getString("userDel"));
				bean.setPhone(rs.getString("phone"));
				bean.setPoint(rs.getString("point"));
				bean.setJoinDate(rs.getString("joinDate"));
				
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
	
	//회원탈퇴
	public void deleteMember(String id, String pass) {//입력받은 id,pass를 불러온다.
		dao.getCon();
				
		try {
			String sql = "update member set userDel='탈퇴' where password=? and id=?";
		//pass id 같다면 업데이트해라
			pstmt= dao.conn.prepareStatement(sql);//db값을 pstmt변수에 담고
			pstmt.setString(1, pass);//input에 담은 pass랑 id를 pstmt에 넣어서 db에있는 값이랑 같은지 확인 후 같다면 update
			pstmt.setString(2, id);
			
			pstmt.executeUpdate();//select 만 쿼리로!!!!!!
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	//중복회원 체크
	public int idCheck(String id2) {//idCheck에 id2를 넣고
		dao.getCon();//db연결
		
		int cnt=0;
		
		try {
			String sql = "select count(*) as cnt from member where id = ?";//id2값이랑 같은게 있는지 확인 물음표에 id2넣고 확인
			
			pstmt = dao.conn.prepareStatement(sql);//sql구문을 pstmt변수에 넣는다.

			pstmt.setString(1, id2);//pstmt(db에)물음표 하나니까 첫번째에 id2(아이디입력값)을 넣어서 sql구문을 이용해 같은 아이디가 있는지 확인 
			
			//select count '111a' as cnt from member where id='111a';
			rs = pstmt.executeQuery();//sql구문 돌린다.
			
			if(rs.next()) {//db에 있는 것들을 불러와 찾는다.
				cnt = rs.getInt("cnt");
			}//cnt가 int니까 int 로 받아서 cnt변수에 담는다.
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return cnt;//cnt에 담은 값을 돌려준다.
	}
		
	////아이디 갯수찾아서 아이디못찾는다. 알려줌아이디 갯수찾아서 아이디못찾는다. 알려줌
	public int idsearchNum(MemberBean bean) {
		dao.getCon();
		
		int id=0;
		try {
			String sql="select count(*) as id2 from member where password=? and email=? and phone= ?";
			
			pstmt = dao.conn.prepareStatement(sql);
			
			pstmt.setString(1, bean.getPass());
			pstmt.setString(2, bean.getEmail());
			pstmt.setString(3, bean.getPhone());
			
			rs= pstmt.executeQuery();
			
			while(rs.next()) {
				id= rs.getInt("id2");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return id;
	}
	//아이디 찾기
	public String idsearch(MemberBean bean) {
		dao.getCon();
		String id="";
		try {
			String sql="select * from member where password=? and email=? and phone= ?";
			
			pstmt = dao.conn.prepareStatement(sql);
			
			pstmt.setString(1, bean.getPass());
			pstmt.setString(2, bean.getEmail());
			pstmt.setString(3, bean.getPhone());
			
			rs= pstmt.executeQuery();
			
			while(rs.next()) {
				id= rs.getString("id");//sql모든 구문 돌면서 저기에 일치하는 id컬럼 들고옴!!
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return id;
	}
	//인풋값으로 맞는비번 갯수찾아서 비번못찾는다. 알려줌
	public int passSearchNum(MemberBean bean) {
		dao.getCon();
		
		int pass2=0;
		try {
			String sql = "select count(*) as pass2 from member where id=? and email=? and phone=?";//countfmf pass2로
			
			pstmt= dao.conn.prepareStatement(sql);
			
			pstmt.setString(1, bean.getId());
			pstmt.setString(2, bean.getEmail());
			pstmt.setString(3, bean.getPhone());
			
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				pass2=rs.getInt("pass2");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return pass2;
	}
	
	//비번 찾기
	public String passSearch(MemberBean bean) {
		dao.getCon();
		
		String pass="";
		try{
			String sql="select * from member where id=? and email=? and phone=?";
			
			pstmt = dao.conn.prepareStatement(sql);
			
			pstmt.setString(1, bean.getId());
			pstmt.setString(2, bean.getEmail());
			pstmt.setString(3, bean.getPhone());
			
			rs= pstmt.executeQuery();
			
			while(rs.next()) {
				pass= rs.getString("password");//db 비번값 변수 pass에 넣음
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return pass;
	}

	//이름 조회
		public String getNameMember(String session_id) {
			dao.getCon();
			String name = null;
			
			try {
				String sql = "select name from member where id = ?";
				pstmt = dao.conn.prepareStatement(sql);
				pstmt.setString(1, session_id);
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					name = rs.getString("name");
				}
				
				pstmt.close();
				dao.conn.close();
				
			}catch(Exception e) {
				e.printStackTrace();
			}
			return name;
		}

		
	// 룰렛 돌릴 시포인트 감소처리
	 public void minusPoint(String id) {
		 
		 dao.getCon();
	 
	 try {
		 
		 String sql = "update member set point = point-1000 where id = ?" ;
	 
		 pstmt = dao.conn.prepareStatement(sql);
	 
		 pstmt.setString(1,id );
	 
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
	 
	 public int idChk(String userid) throws Exception {
		 dao.getCon();
		 int result = 0;
		 try {
				String sql = "select count(*) as cnt from member where id = ?";
				pstmt = dao.conn.prepareStatement(sql);
				pstmt.setString(1, userid);
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					result = rs.getInt("cnt");
				}
				
				pstmt.close();
				dao.conn.close();
				
			}catch(Exception e) {
				e.printStackTrace();
			}
			return result;
		}
}