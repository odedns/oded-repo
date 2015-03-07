package com.mts.nrtrde.server.filters;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mts.nrtrde.server.infra.DBConnection;
import com.mts.nrtrde.server.reports.Record;

public abstract class Filter {

	private ArrayList<Criteria<?>> criteria;
	
	private String tableName;
	
	public Filter(ArrayList<Criteria<?>> criterias, String tableName){
		criteria = criterias;
		this.tableName = tableName;
	}
	
	public abstract ArrayList<? extends Record> match() throws SQLException;
	
	protected ResultSet getAllRecordsFromDB() throws SQLException{
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM ");
		sql.append(tableName);
		sql.append(" WHERE ");
		for (int i = 0; i < criteria.size();i++){
			if (i > 0){
				sql.append(" AND " );
			}
		sql.append(criteria.get(i).addSQLCriteria());
		}
		return DBConnection.getInstance().sendSQLQuery(sql.toString());
	}
	
	protected ArrayList<Criteria<?>> getCriteria() {
		return criteria;
	}
	

}
