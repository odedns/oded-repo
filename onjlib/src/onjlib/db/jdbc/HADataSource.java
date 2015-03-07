package onjlib.db.jdbc;

import java.io.PrintWriter;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.SQLException;
import java.util.Properties;

import javax.naming.NamingException;
import javax.naming.Reference;
import javax.naming.Referenceable;
import javax.sql.DataSource;

public class HADataSource implements Serializable, DataSource,
		Referenceable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6076949233424174436L;
	private static final String JDBC_DRIVER = "org.hsqldb.jdbcDriver";
	private Driver jdbcDriver;
	private String user;
	private String password;
	private String database;
	
	/**
	 * get the connection
	 * @return Connection the JDBC connection.
	 */
	public Connection getConnection() throws SQLException {
        return (getConnection(user, password));
	}

	/**
	 * getConnection 
	 * @param userName the user name.
	 * @param password the password.
	 * @return Connection the JDBC connection. 
	 */
	public Connection getConnection(String username, String password)
			throws SQLException {
		// TODO Auto-generated method stub
		try {
			jdbcDriver = (Driver) Class.forName(JDBC_DRIVER).newInstance();
		} catch(Exception e) {
			e.printStackTrace();
			throw new SQLException("Error loading driver:" + e.getMessage());
		}

        Properties props = new Properties();
        if (user != null) {
            props.put("user", user);
        }
        if (password != null) {
            props.put("password", password);
        }
        return (jdbcDriver.connect(database, props));
	}

	/**
	 * get the log writer.
	 */
	public PrintWriter getLogWriter() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	public int getLoginTimeout() throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	public void setLogWriter(PrintWriter out) throws SQLException {
		// TODO Auto-generated method stub

	}

	public void setLoginTimeout(int seconds) throws SQLException {
		// TODO Auto-generated method stub

	}

	public Reference getReference() throws NamingException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * get the db url.
	 * @return String the db url.
	 */
	public String getDatabase() {
		return database;
	}

	/**
	 * set the db url.
	 * @param database String the db url.
	 */
	public void setDatabase(String database) {
		this.database = database;
	}

	public Driver getJdbcDriver() {
		return jdbcDriver;
	}

	public void setJdbcDriver(Driver jdbcDriver) {
		this.jdbcDriver = jdbcDriver;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public boolean isWrapperFor(Class<?> iface) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	public <T> T unwrap(Class<T> iface) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
