package project_palace_guide;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnector {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
	String usr = "hr";
	String pwd = "hr";
	Connection con;
	
	public Connection getCon() {
		return con;
	}

	public DBConnector() {
		try {
			Class.forName(driver); 
			con = DriverManager.getConnection(url, usr, pwd);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		Connection con = new DBConnector().getCon();
		if(con != null) {
			System.out.println("디비가 정상적으로 연결됨");
		}
	}
}
