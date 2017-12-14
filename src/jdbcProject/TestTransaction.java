package jdbcProject;
import java.sql.*;

//例四：（事务处理）
public class TestTransaction {


	public static void main(String[] args) {
		
		Connection conn = null;
		Statement stmt = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost/mydb?user=root&password=root");
			conn.setAutoCommit(false);//不自动提交
			stmt = conn.createStatement();
			stmt.addBatch("insert into dept2 values (51, '500', 'haha')");
			stmt.addBatch("insert into dept2 values (52, '500', 'haha')");
			stmt.addBatch("insert into dept2 values (53, '500', 'haha')");
			stmt.executeBatch();
			conn.commit();//手动提交
			conn.setAutoCommit(true);//恢复自动提交
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch(SQLException e) {
			
			e.printStackTrace();
			
			try {
				if(conn != null)
				{
					conn.rollback();//回滚
					conn.setAutoCommit(true);
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}finally {
			try {
				if(stmt != null)
					stmt.close();
				if(conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		

	}

}

