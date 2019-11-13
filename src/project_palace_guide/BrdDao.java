package project_palace_guide;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import javax.naming.spi.DirStateFactory.Result;

public class BrdDao {
	Connection con;
	int nowpage;
	int totListSize; // 검색된 전체 데이터의 길이
	int listsize = 20; // 페이지당 표현 될 수.
	int endNo;
	int startNo;
	int totpage; // 전체 페이지 수

	public BrdDao() {
		con = new DBConnector().getCon();
	}

	public Vector<BrdVo> select(String findstr, String sort, String np) { // 조회버튼. 검색어입력, 분류방법, 페이지 위치
		Vector<BrdVo> vo = new Vector<BrdVo>();
		String sql = "select count(*) CNT from mbrd where id like ? or title like ? or  mcontent like ?";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, "%" + findstr + "%");
			ps.setString(2, "%" + findstr + "%");
			ps.setString(3, "%" + findstr + "%");
			
			rs = ps.executeQuery();
			rs.next();

			totListSize = rs.getInt("CNT");
			totpage = (int) Math.ceil(totListSize / (double) listsize);
		
			
			switch (np) {
			case "first":
				nowpage = 1;
				break;
			case "preview":
				nowpage--;
				if (nowpage <= 0)
					nowpage = 1;
				break;
			case "nextpage":
				nowpage++;
				if (nowpage > totpage)
					nowpage = totpage;
				break;
			case "lastpage":
				nowpage = totpage;
				break;
			case "nowpage":
				break;
			}

			endNo = nowpage * listsize;
			startNo = endNo - (listsize - 1);
			
			if(sort.equals("SERIAL")) {				
				sql = "select * from (select rownum rno, A.* from (select * from mbrd where id like ?or title like ? or mcontent like ? ) A) where rno between ? and ? order by serial desc";
			}else if(sort.equals("FAVORITE")) {
				sql = "select * from (select rownum rno, A.* from (select * from mbrd where id like ?or title like ? or mcontent like ? ) A) where rno between ? and ? order by favorite desc";
			}else if(sort.equals("CNT_COMMENT")) {
				sql = "select * from (select rownum rno, A.* from (select * from mbrd where id like ?or title like ? or mcontent like ? ) A) where rno between ? and ? order by CNT_COMMENT desc";
			}else if(sort.equals("VIEWS")) {
				sql = "select * from (select rownum rno, A.* from (select * from mbrd where id like ?or title like ? or mcontent like ? ) A) where rno between ? and ? order by views desc";
			}
			ps = con.prepareStatement(sql);
			ps.setString(1, "%" + findstr + "%");
			ps.setString(2, "%" + findstr + "%");
			ps.setString(3, "%" + findstr + "%");
			ps.setInt(4, startNo);
			ps.setInt(5, endNo);

			rs = ps.executeQuery();
			
			while (rs.next()) {
				BrdVo v = new BrdVo();
				v.setContent(rs.getString("mcontent"));
				v.setDate(rs.getDate("mdate").toString()); // TODO 이거 아마 컨트롤 해야할거야
				v.setId(rs.getString("id"));
				v.setLike(rs.getInt("favorite"));
				v.setPicture(rs.getString("picture"));
				v.setSerial(rs.getInt("serial"));
				v.setTitle(rs.getString("title"));
				v.setViews(rs.getInt("views"));

				String sql2 = "select * from cbrd where pserial = ? order by serial asc";
				PreparedStatement ps2 = con.prepareStatement(sql2);
				ps2.setInt(1, rs.getInt("serial2"));

				ResultSet rs2 = ps2.executeQuery();
				Vector<String> comment = new Vector<String>();
				while (rs2.next()) {
					comment.add(rs2.getString("mcomment"));
				}
				v.setComment(comment);
				vo.add(v);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			return vo;
		}
	}

	public BrdVo pathInsert(String id) {
		BrdVo v = new BrdVo();
		String sql = "select path from semi_log where id = ?";
		PreparedStatement ps = null;
		try {
		ps = con.prepareStatement(sql);
		ps.setString(1, id);
		ResultSet rs3 = ps.executeQuery();
		if(rs3.next()) {
			v.setPath(rs3.getString("path"));
		}
		}catch(Exception ex) {
			
		}finally {			
			return v;
		}		
	}
	public BrdVo view(int serial) {
		BrdVo vo = new BrdVo();
		String sql = "select * from mbrd where serial = ?";
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, serial);

			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				vo.setContent(rs.getString("mcontent"));
				vo.setDate(rs.getString("mdate"));
				vo.setId(rs.getString("id"));
				vo.setLike(rs.getInt("favorite"));
				vo.setPicture(rs.getString("picture"));
				vo.setSerial(serial);
				vo.setTitle(rs.getString("title"));
				vo.setViews(rs.getInt("views"));
			}

			sql = "select * from cbrd where pserial = ?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, serial);
			rs = ps.executeQuery();
			Vector<String> v = new Vector<String>();
			while (rs.next()) {
				v.add(rs.getString("id") +" : " +rs.getString("mcomment"));
			}
			vo.setComment(v);
			
			sql = "update mbrd set views = views + 1 where serial = ?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, serial);
			ps.executeUpdate();
			con.commit();
			
			sql = "select path from semi_log where id = (select id from mbrd where serial = ?)";
			ps = con.prepareStatement(sql);
			ps.setInt(1, serial);
			ResultSet rs3 = ps.executeQuery();
			if(rs3.next()) {
				vo.setPath(rs3.getString("path"));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			return vo;
		}
	}

	public boolean favorite(int serial) {
		boolean b = false;
		String sql = "update mbrd set favorite = favorite + 1 where serial = ?";
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, serial);
			con.setAutoCommit(false);
			int cnt = ps.executeUpdate();
			if (cnt > 0) {
				b = true;
				con.commit();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			con.rollback();
		} finally {
			return b;
		}
	}

	public boolean delete(int serial) {
		boolean b = false;
		String sql = "delete from mbrd where serial = ?";
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, serial);
			con.setAutoCommit(false);
			int cnt = ps.executeUpdate();
			if (cnt > 0) {
				b = true;
				con.commit();
				sql = "delete from cbrd where pserial = ?";
				ps = con.prepareStatement(sql);
				ps.setInt(1, serial);
				int cnt2 = ps.executeUpdate();
				if (cnt2 > 0) {
					con.commit();
				}
			} else {
				con.rollback();
			}
		} catch (Exception ex) {
			con.rollback();
		} finally {
			return b;
		}
	}

	public boolean modify(BrdVo vo, boolean pic) {
		boolean b = false;
		if (pic) {
			String sql = "update mbrd set(content, picture) values(?, ?) where serial = ?";
			PreparedStatement ps = null;
			try {
				ps = con.prepareStatement(sql);
				ps.setString(1, vo.getContent());
				ps.setString(2, vo.getPicture());
				ps.setInt(3, vo.getSerial());

				con.setAutoCommit(false);
				int cnt = ps.executeUpdate();
				if (cnt > 0) {
					b = true;
					con.commit();
				} else {
					con.rollback();
				}
			} catch (Exception ex) {
				con.rollback();
			} finally {
				return b;
			}
		} else {
			String sql = "update mbrd set content = ? where serial = ?";
			PreparedStatement ps = null;
			try {
				ps = con.prepareStatement(sql);
				ps.setString(1, vo.getContent());
				ps.setInt(2, vo.getSerial());

				con.setAutoCommit(false);
				int cnt = ps.executeUpdate();
				if (cnt > 0) {
					b = true;
					con.commit();
				} else {
					con.rollback();
				}
			} catch (Exception ex) {
				con.rollback();
			} finally {
				return b;
			}
		}
	}

	public boolean comment(String comment, int serial, String id) {
		boolean b = false;
		String sql = "insert into cbrd(mcomment, id, serial, pserial) values(?, ?, seq_cbrd.nextval, ?)";
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, comment);
			ps.setString(2, id);
			ps.setInt(3, serial);

			con.setAutoCommit(false);
			int cnt = ps.executeUpdate();

			if (cnt > 0) {
				b = true;
				sql = "update mbrd set cnt_comment  = cnt_comment +1 where serial = ?";
				ps=con.prepareStatement(sql);
				ps.setInt(1, serial);
				ps.executeUpdate();
				con.commit();
			} else {
				con.rollback();
			}
		} catch (Exception ex) {
			con.rollback();
		} finally {
			return b;
		}
	}

	public boolean insert(BrdVo vo, boolean picturechange) {
		boolean b = false;
		String sql = "insert into mbrd(id, title, mcontent, mdate, picture, serial, favorite, views, serial2, cnt_comment) values(?, ?, ?, sysdate, ?, seq_mbrd.nextval, 0, 0, seq_mbrd.currval, 0)";
		PreparedStatement ps = null;
		try {
			if (picturechange) {
				ps = con.prepareStatement(sql);
				ps.setString(1, vo.getId());
				ps.setString(2, vo.getTitle());
				ps.setString(3, vo.getContent());
				String temp = vo.getPicture();
				int pos = temp.lastIndexOf("\\");
				String fileName = temp.substring(pos + 1);
				ps.setString(4, fileName);
				con.setAutoCommit(false);

				int cnt = ps.executeUpdate();
				if (cnt > 0) {
					FileUploader fu = new FileUploader();
					fu.setFileName(vo.getPicture());
					fu.start();
					b = true;
					con.commit();
				} else {
					con.rollback();
				}
			} else {
				String sql2 = "insert into mbrd(id, title, mcontent, mdate, serial, favorite, views, serial2, cnt_comment) values(?, ?, ?, sysdate, seq_mbrd.nextval, 0, 0, seq_mbrd.currval, 0)";
				ps = con.prepareStatement(sql2);
				ps.setString(1, vo.getId());
				ps.setString(2, vo.getTitle());
				ps.setString(3, vo.getContent());
				con.setAutoCommit(false);
				int cnt = ps.executeUpdate();
				if(cnt>0) {
					con.commit();
					b = true;
				}else {
					con.rollback();
				}
			}
		} catch (Exception ex) {

		} finally {
			return b;
		}
	}
}
