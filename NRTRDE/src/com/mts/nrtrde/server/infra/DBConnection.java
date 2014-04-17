package com.mts.nrtrde.server.infra;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Properties;
import java.util.TreeSet;

public class DBConnection {

	private HashMap<String, Connection> connections = new HashMap<String, Connection>();
	private static DBConnection theInstance = new DBConnection();
	private boolean isInitialized = false;
	private  String server = null;
	private  String database = null;
	private TreeSet<Vendor> vendors = new TreeSet<Vendor>(); 
	
	private DBConnection(){
		Properties p = new Properties();
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			p.load(new FileInputStream(new File("cfg/nrtrde.properties")));
			 server = p.getProperty("server");
			 database = p.getProperty("database");
		} catch (Exception e) {
			// put log statement here
			e.printStackTrace();
			System.exit(-1);
		} 
	}
	
	public static DBConnection getInstance(){
		return theInstance;
	}
	
	public void createConnection(String user, String password) throws SQLException{
		Connection c = DriverManager.getConnection("jdbc:oracle:thin:" + user + "/" + password + "@" + server + ":" + database);
		connections.put(user, c);
	}
	
	public Connection getConnection(String user){
		return connections.get(user);
	}
	
	public void logout(String user){
		connections.remove(user);
	}
	

	
	public void login(String user, String password) throws SQLException{
		createConnection(user, password);
		if (! isInitialized){
			initialized();
			isInitialized = true;
		}
		authroized(user);
	}
	
	private void authroized(String user) throws SQLException {
		String sql = "select account_status from dba_users where username = upper(' " + user + "') and  account_status = 'OPEN'";
		ResultSet rs = sendSQLQuery(sql);
		if (! rs.next()){
			// here we need to throw exception
		}
		String sql2 = "select access_mode from security_privileges where access_mode = 'Full Access' and sec_group_key_num = (select sec_group_key_num from users where upper("
			+ user + ") = upper('tcprodsecurity')) and resources_key_num in (select key_num from resources where resource_name = 'NRTRDE_LOGIN_RES');";
		ResultSet rs2 = sendSQLQuery(sql2);
		if (! rs2.next()){
			// here we need to throw exception
		}		
	}

	
	public void initialized() throws SQLException {
		String sql = "select cdc.key_code,cdc.decode,value commercial_ind from ref_dynamic_attributes rda, codedecodes cdc where cdc.prof_name = rda.prof_name and rda.di_name = 'COMMERCIAL_IND_DI'and cdc.prof_name = 'HW_ROAMING_GROUP_FE_PROF'and rda.key_code = cdc.key_code";
		ResultSet rs = sendSQLQuery(sql);
		while (rs.next()){
			vendors.add(new Vendor(rs.getString("Code"), rs.getString("Description"), rs.getBoolean("isCommercial")));
		}
	}

	
	
	public TreeSet<Vendor> getVendors() {
		return vendors;
	}

	public ResultSet sendSQLQuery(String sql) throws SQLException {
		Connection c = getConnection("user");
		Statement s = c.createStatement();
		return s.executeQuery(sql);
	}
}
