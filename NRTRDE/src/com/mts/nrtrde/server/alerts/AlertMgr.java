package com.mts.nrtrde.server.alerts;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import com.mts.nrtrde.server.infra.DBConnection;

public class AlertMgr {

	public ArrayList<Alert> getAllAlerts() throws SQLException{
		ArrayList<Alert> alerts = new ArrayList<Alert>();
		ResultSet rs = getAlertsFromDB();
		while (rs.next()){
			String deviation = rs.getString("Deviation_factor_thresh");
			ThresholdParam volume = new ThresholdParam(rs.getInt("volume_thresh"), deviation.contains("gprs"));
			ThresholdParam sms = new ThresholdParam(rs.getInt("sms_messages_thresh"), deviation.contains("mse"));
			ThresholdParam usage = new ThresholdParam(rs.getInt("usage_thresh"), deviation.contains("moc"));
			int timespan = rs.getInt("timespan_thresh");
			Date deviationDate = rs.getDate("Deviation_Date");
			String vendor = rs.getString("Vendors");
			String[] vendors = vendor.split("_");
			String IMSI = rs.getString("IMSI");
			alerts.add(new Alert(volume, usage, sms, deviationDate, timespan, vendors, IMSI));
		}
		return alerts;
	}

	public static AlertContainer getAllMockAlerts() throws SQLException{
		ArrayList<Alert> alerts = new ArrayList<Alert>();
		Alert a = new Alert(new ThresholdParam(20, true), new ThresholdParam(10, false), new ThresholdParam(0, true),
				new Date(), 10, new String[]{"ATT"}, "37538");
		Alert b = new Alert(new ThresholdParam(30, true), new ThresholdParam(100, true), new ThresholdParam(5, false),
				new Date(), 10, new String[]{"ATT"}, "375382");
		
		alerts.add(a);
		alerts.add(b);
		AlertContainer container = new AlertContainer(alerts, 10, 10, 10);
		return(container);
	}
	
	
	private ResultSet getAlertsFromDB() throws SQLException {
		return DBConnection.getInstance().sendSQLQuery("SELECT * FROM alerts");
	}
}
