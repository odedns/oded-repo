/*
 * Created on 10/01/2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package onjlib.db;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.*;


/**
 * @author odedn
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class Page implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Map m_colMap;
	private List m_rows;
	private int m_currIndex;
	
	/**
	 * Empty default constructor.
	 */
	public Page() {
		super();
		// TODO Auto-generated constructor stub
		init();
	}
	
	/**
	 * Create a Page passing a ResultSet
	 * @param rs the ResultSet to use.
	 */
	public Page(ResultSet rs) throws DbException
	{
		init();
		try {
			initPage(rs);
		} catch(SQLException se) {
			throw new DbException("Error creating Page",se);
		}
	}
	
	/**
	 * init the Page object.
	 *
	 */
	private void init()
	{
		m_colMap = new HashMap();
		m_rows = new ArrayList();
		m_currIndex = -1;
	}
	
	/**
	 * initialize the Page
	 * @param rs The ResultSet to process.
	 * @throws SQLException
	 */
	private void initPage(ResultSet rs ) throws SQLException
	{
		ResultSetMetaData rsMeta = rs.getMetaData();
		int numCols = rsMeta.getColumnCount();
		/*
		 * store the colum names in a hash table.
		 */
		for(int i=1; i <= numCols; ++i) {
			String name = rsMeta.getColumnName(i);
			m_colMap.put(name, new Integer(i));
		}
		while(rs.next()) {
			ArrayList row = new ArrayList(numCols);
			for(int i=1; i <= numCols; ++i) {
				Object o = rs.getObject(i);
				row.add(o);								
			} // for 
			m_rows.add(row);
			
		} // while
		rs.close();
	}
	
	/**
	 * move to the next row in the page.
	 * @return boolean true if there is a next row
	 * false otherwise.
	 */
	public boolean next()
	{		
		return(m_currIndex++ < m_rows.size() ? true :  false);
	}
	
	
	/**
	 * Rewind the page to the first
	 * data row.
	 */
	public void rewind()
	{
		m_currIndex = 0;
	}
	
	/**
	 * Return the object value of the column in
	 * the current row.
	 * @param colNum the column number.
	 * @return Object the col value.
	 */
	public Object getObject(int colNum) throws DbException 
	{
		
		if(m_currIndex >= m_rows.size()) {
			throw new DbException("invalid row index: " + m_currIndex);
		}
		ArrayList row = (ArrayList ) m_rows.get(m_currIndex);
		if(colNum > row.size()) {
			throw new DbException("invalid column index: " + colNum);
		}
		Object o = row.get(colNum-1);
		return(o);
		
	}
	
	/**
	 * Return the object value of the column in
	 * the current row.
	 * @param colName the column name.
	 * @return Object the col value.
	 */
	public Object getObject(String  colName) throws DbException 
	{
		
		Integer n = (Integer) m_colMap.get(colName);
		if(n == null) {
			throw new DbException("invalid column name: " + colName);
		}
		int colNum = n.intValue();
		
		if(m_currIndex >= m_rows.size()) {
			throw new DbException("invalid row index: " + m_currIndex);
		}
		ArrayList row = (ArrayList ) m_rows.get(m_currIndex);
		if(colNum > row.size()) {
			throw new DbException("invalid column index: " + colNum);
		}		
		Object o = row.get(colNum-1);
		return(o);
		
	}
	
	/**
	 * Return the object value of the column in
	 * the current row.
	 * @param colNum the column number.
	 * @return Object the col value.
	 */
	public String getString(int colNum) throws DbException
	{
		return((String) getObject(colNum));		
	}
	
	/**
	 * Return the object value of the column in
	 * the current row.
	 * @param colName the column name.
	 * @return Object the col value.
	 */
	public String getString(String  colName) throws DbException
	{
		return((String) getObject(colName));
		
	}

	/**
	 * Return the object value of the column in
	 * the current row.
	 * @param colNum the column number.
	 * @return Object the col value.
	 */
	public Integer getInteger(int colNum) throws DbException
	{
		return((Integer) getObject(colNum));		
	}
	
	/**
	 * Return the object value of the column in
	 * the current row.
	 * @param colName the column name.
	 * @return Object the col value.
	 */
	public Integer getInteger(String  colName) throws DbException
	{
		return((Integer) getObject(colName));
		
	}
	
	/**
	 * Return the object value of the column in
	 * the current row.
	 * @param colNum the column number.
	 * @return Object the col value.
	 */
	public int getInt(int colNum) throws DbException
	{
		Number n = (Number) getObject(colNum);
		return(n == null ? 0 : n.intValue());		
	}
	
	/**
	 * Return the object value of the column in
	 * the current row.
	 * @param colName the column name.
	 * @return Object the col value.
	 */
	public int getInt(String  colName) throws DbException
	{
		Number n = (Number) getObject(colName);
		return(n == null ? 0 : n.intValue());		
		
	}

	/**
	 * Return the object value of the column in
	 * the current row.
	 * @param colNum the column number.
	 * @return Object the col value.
	 */
	public long getLong(int colNum) throws DbException
	{				
		Number n = (Number) getObject(colNum);
		return(n == null ? 0 : n.longValue());
	}
	
	/**
	 * Return the object value of the column in
	 * the current row.
	 * @param colName the column name.
	 * @return Object the col value.
	 */
	public long getLong(String  colName) throws DbException
	{
		Number n = (Number) getObject(colName);
		return(n == null ? 0 : n.longValue());			
		
	}
	
	
	/**
	 * Return the object value of the column in
	 * the current row.
	 * @param colNum the column number.
	 * @return Object the col value.
	 */
	public short getShort(int colNum) throws DbException
	{
		Number n = (Number) getObject(colNum);
		return(n == null ? 0 : n.shortValue());				
	}
	
	/**
	 * Return the object value of the column in
	 * the current row.
	 * @param colName the column name.
	 * @return Object the col value.
	 */
	public short getShort(String  colName) throws DbException
	{
		Number n = (Number) getObject(colName);
		return(n == null ? 0 : n.shortValue());		
		
	}
	
	/**
	 * Return the object value of the column in
	 * the current row.
	 * @param colNum the column number.
	 * @return Object the col value.
	 */
	public float getFloat(int colNum) throws DbException
	{
		Number n = (Number) getObject(colNum);
		return(n == null ? 0 : n.floatValue());
			
	}
	
	/**
	 * Return the object value of the column in
	 * the current row.
	 * @param colName the column name.
	 * @return Object the col value.
	 */
	public float getFloat(String  colName) throws DbException
	{
		Number n = (Number) getObject(colName);
		return(n == null ? 0 : n.floatValue());				
	}
	
	/**
	 * Return the object value of the column in
	 * the current row.
	 * @param colNum the column number.
	 * @return Object the col value.
	 */
	public double getDouble(int colNum) throws DbException
	{
		Number n = (Number) getObject(colNum);
		return(n == null ? 0 : n.doubleValue());				
	}
	
	/**
	 * Return the object value of the column in
	 * the current row.
	 * @param colName the column name.
	 * @return Object the col value.
	 */
	public double getDouble(String  colName) throws DbException
	{
		Number n = (Number) getObject(colName);
		return(n == null ? 0 : n.doubleValue());				
	}
	
	
	/**
	 * Return the object value of the column in
	 * the current row.
	 * @param colNum the column number.
	 * @return Object the col value.
	 */
	public Date getDate(int colNum) throws DbException
	{
		return((Date) getObject(colNum));		
	}
	
	/**
	 * Return the object value of the column in
	 * the current row.
	 * @param colName the column name.
	 * @return Object the col value.
	 */
	public Date getDate(String  colName) throws DbException
	{
		return((Date) getObject(colName));		
	}
	
	/**
	 * Return the object value of the column in
	 * the current row.
	 * @param colNum the column number.
	 * @return Object the col value.
	 */
	public Timestamp getTimestamp(int colNum) throws DbException
	{
		return((Timestamp) getObject(colNum));		
	}
	
	/**
	 * Return the object value of the column in
	 * the current row.
	 * @param colName the column name.
	 * @return Object the col value.
	 */
	public Timestamp getTimestamp(String  colName) throws DbException
	{
		return((Timestamp) getObject(colName));		
	}
	
	/**
	 * Return the object value of the column in
	 * the current row.
	 * @param colNum the column number.
	 * @return Object the col value.
	 */
	public byte[] getBytes(int colNum) throws DbException
	{
		return((byte[]) getObject(colNum));		
	}
	
	/**
	 * Return the object value of the column in
	 * the current row.
	 * @param colName the column name.
	 * @return Object the col value.
	 */
	public byte[] getBytes(String  colName) throws DbException
	{
		return((byte[]) getObject(colName));		
	}
	
	/**
	 * Return the object value of the column in
	 * the current row.
	 * @param colNum the column number.
	 * @return Object the col value.
	 */
	public Boolean getBoolean(int colNum) throws DbException
	{
		return((Boolean) getObject(colNum));		
	}
	
	/**
	 * Return the object value of the column in
	 * the current row.
	 * @param colName the column name.
	 * @return Object the col value.
	 */
	public Boolean getBoolean(String  colName) throws DbException
	{
		return((Boolean) getObject(colName));		
	}
	
	/**
	 * Return the object value of the column in
	 * the current row.
	 * @param colNum the column number.
	 * @return Object the col value.
	 */
	public BigDecimal getBigDecimal(int colNum) throws DbException
	{
		return((BigDecimal) getObject(colNum));		
	}
	
	/**
	 * Return the object value of the column in
	 * the current row.
	 * @param colName the column name.
	 * @return Object the col value.
	 */
	public BigDecimal getBigDecimal(String  colName) throws DbException
	{
		return((BigDecimal) getObject(colName));		
	}
	/**
	 * @return Returns the m_colMap.
	 */
	public Map getColumnMap() {
		return m_colMap;
	}
	/**
	 * @return Returns the m_currIndex.
	 */
	public int getCurrentRowIndex() {
		return m_currIndex;
	}
	/**
	 * @return Returns the m_rows.
	 */
	public List getRows() {
		return m_rows;
	}
}
