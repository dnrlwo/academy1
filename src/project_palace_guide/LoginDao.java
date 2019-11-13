package project_palace_guide;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginDao {
	Connection con;
	
	public LoginDao() {
		con = new DBConnector().getCon();
	}
	public boolean path_insert(String id, String path) {
		boolean b = false;
		String sql = "update semi_log set path = ? where id = ?";
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, path);
			ps.setString(2, id);
			con.setAutoCommit(false);
			int cnt = ps.executeUpdate();
			if(cnt>0) {
				b = true;
				con.commit();
			}else {
				con.rollback();
			}
		}catch(Exception ex) {
			ex.printStackTrace();
			con.rollback();
		}finally {
			return b;			
		}
	}
	public boolean img_upload(String img, String id) {
		boolean b = false;
		String sql = "update semi_log set img_server = ?, img_user = ? where id = ?";
		PreparedStatement ps = null; //only saved photoname and file extention
		try { 
			ps = con.prepareStatement(sql);
			ps.setString(1, img);
			ps.setString(2, img);
			ps.setString(3, id);
			
			con.setAutoCommit(false);
			int cnt = ps.executeUpdate();
			if(cnt > 0 ) {
				con.commit();
				b = true;
			}else {
				con.rollback();
			}
		}catch(Exception ex) {
			con.rollback();
			ex.printStackTrace();
		}finally {
		return b;
		}
	}
	
	public boolean id_check(String id) {
		boolean b = false;
		String sql = "select count(*) cnt from semi_log where id = ?";
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();
			rs.next();
			int cnt = rs.getInt("cnt");
//			System.out.println(cnt);
			if(cnt==0) {
				b = true;
			}
		}catch(Exception ex){
//			ex.printStackTrace();
		}finally {
			return b;			
		}
	}
	
	public LoginVo login_check(String id, String pwd) {
		LoginVo vo = new LoginVo();
		String sql = "select img_server, img_user, nationality, compl from semi_log where id = ? and pwd = ?";
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, id);
			ps.setString(2, pwd);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				vo.setId(id);
				vo.setNationality(rs.getString("NATIONALITY"));
				vo.setImg_server(rs.getString("img_server"));
				vo.setImg_user(rs.getString("img_user"));
				vo.setCompl(rs.getInt("compl"));
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}finally {
			return vo;	
		}
	}
	
	public boolean insert(LoginVo vo) {
		boolean b = false;
		String sql = "insert into semi_log(id, pwd, nationality, gold, silver, bronze, compl) values(?, ?, ?, 0, 0, 0, 0)";
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, vo.getId());
			ps.setString(2,vo.getPwd());
			ps.setString(3, vo.getNationality());
			con.setAutoCommit(false);
			int cnt = ps.executeUpdate();
			if(cnt ==1) {
				b = true;
				con.commit();
			}
		}catch(Exception ex) {
			ex.printStackTrace();
			con.rollback();
		}finally {
		return b;
		}
	}

}
