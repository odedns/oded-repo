/**
 * SingletonConnectionManager.java
 * Mar 5, 2008
 */
package onjlib.db;

import java.sql.Connection;
import java.util.Properties;

/**
 * @author Oded Nissan
 *
 */
public class SingletonConnectionManager extends DriverConnectionManager {
	private Connection conn;
	
	public SingletonConnectionManager(Properties props) throws DbException {
		super(props);
		// TODO Auto-generated constructor stub
		conn = null;
	}

	public SingletonConnectionManager(String driver, String url, String user,
			String password) throws DbException {
		super(driver, url, user, password);
		conn = null;
	}

	/**
	 *  empty constructor.
	 */
	public SingletonConnectionManager() {
		// TODO Auto-generated constructor stub
		conn = null;
	}

	/* (non-Javadoc)
	 * @see onjlib.db.ConnectionManager#getConnection()
	 */
	@Override
	public Connection getConnection() throws DbException
	{
		// TODO Auto-generated method stub
		if(this.conn == null) {
			this.conn = super.getConnection();
		} else {
			if(!validate(this.conn)) {
				this.conn = super.getConnection();
			}
		}
		return(this.conn);
	}
	
	
	/**
	 * close the singleton connection.
	 */
	public void close()
	{
		DbUtil.closeConnectionQuitely(this.conn);
	}

	

}
