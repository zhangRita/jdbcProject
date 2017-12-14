package jdbcProject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
//例二（2.3.2）
public class TestPrepStmt {

	public static void main(String[] args) {
		//ResultSet rs = null;
		PreparedStatement pstmt = null;
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost/mydb?user=root&password=root");

			String sql = "INSERT INTO login(username,password) VALUES(?,?)";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, "张丹");
			pstmt.setString(2, "root");
			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null) {
					pstmt.close();
					pstmt = null;
				}
				if(conn != null) {
					conn.close();
					conn = null;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}

