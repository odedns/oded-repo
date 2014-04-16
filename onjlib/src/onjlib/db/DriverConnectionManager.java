package onjlib.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created on 07/06/2005
 * @author Oded Nissan
 *
 * DriverConnection manager. Implementation of ConnectionManager using
 * a JDBC driver.
 */
public class DriverConnectionManager extends ConnectionManager {

	private String driver;
	private String url;
	private String user;
	private String password;

	/**
	 * empty constructor.
	 * to be invoked by the ConnectionManagerFactory.
	 */
	public DriverConnectionManager()
	{		
	}
	
	/**
	 * constructor initialize DriverConnectionManager
	 * with Properties
	 * @param props Properties.
	 */
	public DriverConnectionManager(Properties props)throws DbException
	{
		init(props);
	}
	
	/**
	 * Construct a DriverConnection manager programatically.
	 * @param driver the driver class
	 * @param url the JDBC url
	 * @param user db user
	 * @param password the db password.
	 */
	public DriverConnectionManager(String driver, String url, String user, String password)
		throws DbException
	{
		this.driver = driver;
		this.url = url;
		this.user = user;
		this.password = password;
		loadDriver();
		
	}
	/**
	 * initialize the ConnectionManager.
	 * @param props the Properties to use.
	 */
	public void init(Properties props) throws DbException
	{
		
		this.driver = props.getProperty(JDBC_DRIVER);
		this.url = props.getProperty(JDBC_URL);
		this.user = props.getProperty(JDBC_USER);
		this.password = props.getProperty(JDBC_PASSWORD);
		loadDriver();
		
	}
	
	/**
	 * load the JDBC driver.
	 * @throws DbException in case of error.
	 */
	private void loadDriver() throws DbException
	{
		try {
			Class.forName(this.driver);
		} catch(ClassNotFoundException ce) {
			throw new DbException("Error loading driver.", ce);
		}
		
	}
	
	/* (non-Javadoc)
	 * @see onjlib.db.ConnectionManager#getConnection()
	 */
	public Connection getConnection() throws DbException {
		// TODO Auto-generated method stub
		Connection conn = null;
		try {
			if(user != null && !user.equals("")) {
				conn = DriverManager.getConnection(this.url,this.user,this.password);
			} else {
				conn = DriverManager.getConnection(this.url);
			}
		} catch(SQLException se) {
			se.printStackTrace();
			throw new DbException("Error getting connection",se);
		}
		return (conn);
	}


	/**
	 * get the driver class.
	 * @return String the driver class.
	 */
	public String getDriver()
	{
		return driver;
	}


	/**
	 * set the driver class.
	 * @param driver String the driver class.
	 */
	public void setDriver(String driver)
	{
		this.driver = driver;
	}


	/**
	 * get the jdbc url.
	 * @return Strign the url
	 */
	public String getUrl()
	{
		return url;
	}


	/**
	 * set the jdbc url.
	 * @param url String the jdbc url.
	 */
	public void setUrl(String url)
	{
		this.url = url;
	}


	/**
	 * get the db user.
	 * @return String the db user.
	 */
	public String getUser()
	{
		return user;
	}

	/**
	 * set the db user
	 * @param user String the db user.
	 */
	public void setUser(String user)
	{
		this.user = user;
	}

	/**
	 * get the db passord.
	 * @return String the db password.
	 */
	public String getPassword()
	{
		return password;
	}


	/**
	 * set the db password.
	 * @param password String the db password.
	 */
	public void setPassword(String password)
	{
		this.password = password;
	}


}
