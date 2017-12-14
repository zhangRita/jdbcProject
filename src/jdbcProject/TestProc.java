package jdbcProject;

import java.sql.*;

//例三（2.3.3）
public class TestProc {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/xue_xiao?user=root&password=root");
		CallableStatement cstmt = conn.prepareCall("{call simpleproc(?, ?)}");//用来执行存储过程
		cstmt.registerOutParameter(2, Types.VARCHAR);
		cstmt.setString(1, "张三");
	
		cstmt.execute();
		System.out.println(cstmt.getString(2));
		cstmt.close();
		conn.close();
	}

}
