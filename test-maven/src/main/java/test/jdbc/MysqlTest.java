/**
 * 
 */
package test.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * @author I070659
 *
 */
public class MysqlTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String url = "jdbc:mysql://localhost:3306/alfresco";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = null;
			conn = DriverManager.getConnection(url,"alfresco", "alfresco");
			System.out.println("Connected");
			Statement st =  conn.createStatement();
			ResultSet rs = st.executeQuery("select @@hostname");
			rs.next();
			String host = rs.getString(1);
			System.out.println("db host name: " + host);
			conn.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
