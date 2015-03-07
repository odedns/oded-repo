package tests;

import java.sql.Connection;
import java.sql.Statement;

import junit.framework.TestCase;

import onjlib.db.jdbc.HADataSource;

import org.junit.Test;

public class TestHADataSource extends TestCase {
	HADataSource ds = null;

	 /*
	 * @see TestCase#setUp()
	 */
	protected void setUp() throws Exception {
		super.setUp();
		ds = new HADataSource();
		ds.setDatabase("jdbc:hsqldb:hsql://localhost/xdb");
		ds.setUser("sa");
		ds.setPassword("");
		
	}
	
	@Test
	public void testGetConnection() throws Exception {
		Connection conn = ds.getConnection();
		System.out.println("got connection");
		assertNotNull(conn);
		Statement st = conn.createStatement();
		assertNotNull(st);
		conn.close();
		st.close();
	}

	
	public static void main(String[] args) {
		junit.textui.TestRunner.run(TestHADataSource.class);
	}

}
