package project_palace_guide;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

public class MonumentalDao {
	Connection con;

	public MonumentalDao() {
		con = new DBConnector().getCon();
	}

	public MonumentalVo select(String place) {
		MonumentalVo vo = new MonumentalVo();
		String sql = "select * from monumental where place = ?";
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, place);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				vo.setExplainmp3(rs.getString("explainmp3"));
				vo.setPlace(place);
				vo.setX(rs.getInt("x"));
				vo.setY(rs.getInt("y"));
				vo.setPicture1(rs.getString("picture1"));
				vo.setPicture2(rs.getString("picture2"));
				vo.setPicture3(rs.getString("picture3"));
				vo.setPicture4(rs.getString("picture4"));
			}

		} catch (Exception ex) {

		}
		return vo;
	}

	public void insert_cnt(String id, int cnt) {
		String sql = "update semi_log set compl = ? where id = ?";
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, cnt);
			ps.setString(2, id);
			ps.executeUpdate();
			con.commit();
		} catch (Exception ex) {
			try {
			con.rollback();
			ex.printStackTrace();
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			ex.printStackTrace();
		}
	}
}
