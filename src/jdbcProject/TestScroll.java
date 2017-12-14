package jdbcProject;
import java.sql.*;

//可滚动可更新的结果集（5.1.1）
public class TestScroll {
	public static void main(String args[]) throws Exception{

		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/mydb2?user=root&password=root");
			Statement stmt = conn.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);//（返回的结果集对数据库中的的数据变动是不敏感的，只读）
			ResultSet rs = stmt.executeQuery("select * from pet order by wei");
			rs.next();
			System.out.println(rs.getString(1));
			rs.last();
			System.out.println(rs.getString(1));
			System.out.println(rs.isLast());
			System.out.println(rs.isAfterLast());
			System.out.println(rs.getRow());
			rs.previous();
			System.out.println(rs.getString(1));
			rs.absolute(4);//允许你指定结果集的绝对位置，光标定位到4行
			System.out.println(rs.getString(1));
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
