/*
 * Created on 01/08/2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package tests;

import java.sql.ResultSet;

import onjlib.db.*;
import junit.framework.TestCase;

/**
 * @author odedn
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class TestDbService extends TestCase {

	DbService m_dbSrv = null;
	
	public static void main(String[] args) {
		junit.textui.TestRunner.run(TestDbService.class);
	}

	/*
	 * @see TestCase#setUp()
	 */
	protected void setUp() throws Exception {
		super.setUp();
		m_dbSrv = new DbService();
	}

	/*
	 * @see TestCase#tearDown()
	 */
	protected void tearDown() throws Exception {
		super.tearDown();
		m_dbSrv.close();
	}

	public void testExecuteQuery() throws Exception {
		DbQuery query = m_dbSrv.createQuery("SELECT * FROM ON_TEAMS WHERE TEAM_ID=?");
		query.setParam(1,new Integer(1),java.sql.Types.INTEGER);
		ResultSet rs = m_dbSrv.executeQuery(query);
		if(!rs.next()) {
			fail();
		}
		String name = rs.getString("TEAM_NAME");
		assertEquals(name,"Redskins");		
		rs.close();
	}

	public void testExecuteQueryEx() throws Exception {
		DbQuery query = m_dbSrv.createQuery("SELECT * FROM ON_TEAMS WHERE TEAM_ID=?");
		query.setParam(1,new Integer(1),java.sql.Types.INTEGER);
		Page p = m_dbSrv.executeQueryEx(query);
		if(!p.next()) {
			fail();
		}
		String name = p.getString("TEAM_NAME");
		assertEquals(name,"Redskins");		
		
	}

	public void testExecuteUpdate() throws Exception {
		DbQuery query = m_dbSrv.createQuery("INSERT INTO ON_TEAMS VALUES (?,?,?)");
		query.setParam(1,new Integer(4),java.sql.Types.INTEGER);
		query.setParam(2,new String("Raiders"),java.sql.Types.VARCHAR);
		query.setParam(3,new String("Oakland"),java.sql.Types.VARCHAR);
		m_dbSrv.beginTransaction();
		m_dbSrv.executeUpdate(query);
		m_dbSrv.commit();
		
	}

}
