/*
 * Created on 16/01/2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package onjlib.db;

import java.sql.Types;


/**
 * @author odedn
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class TableIdGenerator implements IdGenerator {

	private String m_tableName = "ON_PK_GENERATOR";
	private int m_chunkSize = 10;
	private long m_currNumerator=1;
	private int m_counter = 0;
	private final int DEF_NUMERATOR = 1;
	/**
	 * 
	 */
	public TableIdGenerator() {
		super();
		// TODO Auto-generated constructor stub
		m_counter = m_chunkSize;
	}

	/* (non-Javadoc)
	 * @see onjlib.db.IdGenerator#nextVal()
	 */
	public long nextVal() {
		
		return(nextVal(DEF_NUMERATOR));
	}
	
	/* (non-Javadoc)
	 * Get the nextVal from the numerator table.\
	 * @param numeratorId the id of the numerator to use.
	 * 
	 */
	public long nextVal(int numeratorId) {
		// TODO Auto-generated method stub		
		Number n = null;
		String sql = "SELECT NUMERATOR FROM " + m_tableName + " WHERE ID=" + numeratorId;
		String updSql = "UPDATE " + m_tableName+ " SET NUMERATOR=?";
		DbService db = null;
		
		/*
		 * if the chunk has not been used yet, 
		 * return a value from the chunk.
		 */
		if(m_counter >= m_chunkSize) {
		
			try {
				db = new DbService();
				db.setAutoCommit(true);
				n = (Number) db.executeQuerySingle(sql);
				m_currNumerator = n.longValue();
				m_counter=0;
				DbQuery query = new DbQuery(updSql);
				Long l = new Long(m_currNumerator + m_chunkSize);
				query.setParam(1,l,Types.INTEGER);
				db.executeUpdate(query);
			
			} catch(Exception e) {
				e.printStackTrace();
			} finally {
				db.closeQuitely();
			}
			
		} // if
		
		
		return(m_currNumerator + m_counter++);
	}
	
	
	
}
