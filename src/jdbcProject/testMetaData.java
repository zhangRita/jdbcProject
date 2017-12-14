package jdbcProject;

import java.sql.*;

public class testMetaData {
	public static void main(String[] args) {
		Connection conn = null;
	    PreparedStatement pstmt=null;
	    ResultSet rs = null;
	    try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost/mydb?user=root&password=root");
			DatabaseMetaData dbmd=conn.getMetaData(); 
            //获取数据库元数据
            System.out.println(dbmd.getDatabaseProductName());
            System.out.println(dbmd.getDatabaseProductVersion());
            System.out.println(dbmd.getDriverName());
            System.out.println(dbmd.getDriverVersion());
            pstmt=conn.prepareStatement("select username,password from login");
            rs=pstmt.executeQuery();
            //获取结果集元数据
            ResultSetMetaData rsmd=rs.getMetaData();
            System.out.println(rsmd.getColumnCount());
            System.out.println(rsmd.getColumnName(1));
            System.out.println(rsmd.getColumnName(2));
	    }catch(ClassNotFoundException e) {
			e.printStackTrace();
	    }catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
}
