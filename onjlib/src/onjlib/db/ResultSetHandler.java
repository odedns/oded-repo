/*
 * Created on 11/01/2006
 */
package onjlib.db;

import java.sql.ResultSet;

/**
 * @author odedn
 * This is a callback interface for ResultSet.
 * It is used by DbService executeQuery() method to process
 * the ResultSet of the query. 
 * The handleRow method is called for each row in the 
 * ResultSet.
 */
public interface ResultSetHandler {

	/**
	 * This method is called for each row 
	 * in the result set.
	 * @param rs the ResultSet object to process.
	 */
	public void handleRow(ResultSet rs);
}
