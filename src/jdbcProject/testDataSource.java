package jdbcProject;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public class testDataSource {
	
	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			//Class.forName("com.mysql.jdbc.Driver");//注册JDBC驱动程序

			//conn = DriverManager.getConnection("jdbc:mysql://localhost/mydb?user=root&password=root");//创建数据库连接
			conn = getMysqlConnectionThruDataSource();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select * from pet");//执行查询SQL语句(返回结果集)
			while (rs.next()) {
				//通过index获取字段的值
				System.out.println(rs.getString(1));
				//通过字段名获取字段的值
				System.out.println(rs.getString("name"));
			}

		} catch (SQLException ex) {
			// handle any errors
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		} finally {
			try {
				if(rs != null) {
					rs.close();
					rs = null;
				}
				if(stmt != null) {
					stmt.close();
					stmt = null;
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
	public static Connection getMysqlConnectionThruDataSource() 
   			throws SQLException {
		MysqlDataSource ds = new MysqlDataSource();
		ds.setURL("jdbc:mysql://localhost/mydb");
		ds.setUser("root");
		ds.setPassword("root");
		return ds.getConnection();
	}
}
