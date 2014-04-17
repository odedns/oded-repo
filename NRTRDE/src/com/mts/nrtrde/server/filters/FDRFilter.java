package com.mts.nrtrde.server.filters;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import com.mts.nrtrde.server.reports.FDRRecord;

public class FDRFilter extends Filter{

	public FDRFilter(ArrayList<Criteria<?>> criterias, String tableName) {
		super(criterias, "FDR_REPORT_TABLE");
	}

	@Override
	public ArrayList<FDRRecord> match() throws SQLException {
		ArrayList<FDRRecord> records = new ArrayList<FDRRecord>();
		ResultSet rs = getAllRecordsFromDB();
		while (rs.next()) {
//			FDRRecord r = createFDRRecord(rs);
//			records.add((FDRRecord) isMatch(r));
		}
		return records;
	}


	private FDRRecord createFDRRecord(String fdrFile, String vPMN, Date startDate, Date toDate,
			String nrFile, Date recievedDate, int recordNum) {
		return new FDRRecord(fdrFile, vPMN, startDate, toDate, nrFile, recievedDate, recordNum);
	}


}
