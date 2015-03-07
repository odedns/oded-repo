package com.mts.nrtrde.server.filters;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import com.mts.nrtrde.server.reports.ERRRecord;
import com.mts.nrtrde.shared.RecordType;

public class ErrFilter extends Filter{

	public ErrFilter(ArrayList<Criteria<?>> criterias, String tableName) {
		super(criterias, "ERROR_REPORT");
	}

	@Override
	public ArrayList<ERRRecord> match() throws SQLException {
		ArrayList<ERRRecord> records = new ArrayList<ERRRecord>();
		ResultSet rs = getAllRecordsFromDB();
		while (rs.next()) {
//			ERRRecord r = createERRRecord(rs);
//			records.add((ERRRecord) isMatch(r));
		}
		return records;
	}

	private ERRRecord createERRRecord(String vPMN, String nrFile, int recordNum,
			String errFileName, int errorCode, RecordType recordType, Date eventDate) {
		return new ERRRecord(vPMN, nrFile, recordNum, errFileName, errorCode, recordType, eventDate);
	}

}
