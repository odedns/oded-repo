package onjlib.patterns.examples;

import java.sql.*;

import onjlib.patterns.Singleton;



/**
 * @author odedn
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class DbConnection extends Singleton {

	/**
	 * @see onjlib.patterns.Singleton#createInstance(Object)
	 */
	protected Object createInstance(Object param) throws Exception
	{
		String url = (String) param;
		Driver drv = (Driver) Class.forName("COM.ibm.db2.jdbc.app.DB2Driver").newInstance();
    	Connection c =java.sql.DriverManager.getConnection(url);
        return(c);
	
	}

	public static void main(String[] args) 
	{
		System.out.println("in DbConenction test");
		try {
			DbConnection dc = new DbConnection();
			Connection conn = (Connection) dc.getInstance("jdbc:db2:MYDB");
			System.out.println("got connection " + conn);
			conn.close();
		} catch(Exception e) {
			e.printStackTrace();	
		}
		
	}
}
