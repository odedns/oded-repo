/*
 * Created on 07/06/2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package tests;

import onjlib.db.ConnectionManagerFactory;
import onjlib.db.DbUtil;
import junit.framework.TestCase;
import java.sql.*;
/**
 * @author P0006439
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class TestConnectionManager extends TestCase {

	public void testGetConnection() throws Exception {
		
		Connection conn = DbUtil.getConnection();
		assertTrue(!conn.isClosed());
		conn.close();
	}

}
