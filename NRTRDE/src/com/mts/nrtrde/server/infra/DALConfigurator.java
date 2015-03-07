package com.mts.nrtrde.server.infra;

import java.sql.Connection;
import java.sql.Statement;

import javax.naming.ConfigurationException;

public class DALConfigurator {

	
	public DALConfigurator(	String ftpIp,
			int ftpPort,
			String ftpUser,
			String ftpPassword,
			String ftpPath,
			String hostPath,
			String hostBackup,
			String hostGenerated,
			int history,
			int timespan,
			int volumeThreshold,
			int usageThreshold,
			int smsThreshold,
			String provisionIp,
			int provisionPort,
			String phones) throws ConfigurationException {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO nrtrde_configuration (");
		sql.append("ftp_ip,ftp_port,ftp_user,ftp_password,ftp_path,host_path,host_backup,host_generated,history,");
		sql.append("timespan,volume_threshold,usage_threshold,sms_threshold,provision_ip,provision_port,phones) ");
		sql.append("VALUES (");
		sql.append(ftpIp);
		sql.append(",");
		sql.append(ftpPort);
		sql.append(",");
		sql.append(ftpUser);
		sql.append(",");
		sql.append(ftpIp);
		sql.append(",");
		sql.append(hostPath);
		sql.append(",");
		sql.append(hostBackup);
		sql.append(",");
		sql.append(hostGenerated);
		sql.append(",");
		sql.append(history);
		sql.append(",");
		sql.append(timespan);
		sql.append(",");
		sql.append(volumeThreshold);
		sql.append(",");
		sql.append(usageThreshold);
		sql.append(",");
		sql.append(smsThreshold);
		sql.append(",");
		sql.append(provisionIp);
		sql.append(",");
		sql.append(provisionPort);
		sql.append(",");
		sql.append(phones);
		sql.append(")");		
		Connection c = DBConnection.getInstance().getConnection("user");
		int result;
		try {
			Statement s = c.createStatement();
			result = s.executeUpdate(sql.toString());
			if (result != 1){
				throw new ConfigurationException();
			}
		} catch (Exception e) {
			throw new ConfigurationException();
		}
	}
}
