package com.mts.nrtrde.server.reports;

import java.util.Date;

import com.mts.nrtrde.shared.RecordType;

public class ERRRecord extends Record{

	private final String errFileName;
	private final int errorCode;
	private final RecordType recordType;
	private final Date eventDate;
	
	public ERRRecord(String vPMN, String nrFile, int recordNum,
			String errFileName, int errorCode, RecordType recordType,
			Date eventDate) {
		super(vPMN, nrFile, recordNum);
		this.errFileName = errFileName;
		this.errorCode = errorCode;
		this.recordType = recordType;
		this.eventDate = eventDate;
	}

	public String getErrFileName() {
		return errFileName;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public RecordType getRecordType() {
		return recordType;
	}

	public Date getEventDate() {
		return eventDate;
	}
	
	
}
