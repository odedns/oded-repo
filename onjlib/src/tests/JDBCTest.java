/**
 * JDBCTest.java
 * Feb 24, 2008
 */
package tests;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.util.Properties;

/**
 * @author Odedn
 *
 */
public class JDBCTest {

	
	public static Connection getConnection() throws Exception
	{
	//	jdbc:mysql://localhost:3306/test
			Connection conn=null;
			Properties props = new Properties();
			props.put("jdbc.driver","oracle.jdbc.driver.OracleDriver");			
			props.put("jdbc.url","jdbc:oracle:thin:@DB-TW:1521:tsmc1");
			props.put("user", "DV_TS_ODED");
			props.put("password", "DV_TS_ODED");
			Class.forName("oracle.jdbc.driver.OracleDriver");
		    conn = DriverManager.getConnection("jdbc:oracle:thin:@DB-TW:1521:tsmc1",props);

		    conn.setAutoCommit(true);

			return(conn);
		}
		
		
	
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		try {
			Connection conn = getConnection();
			conn.createStatement();
			System.out.println("got connection");
			conn.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
