package com.mts.nrtrde.server.reports;

import java.util.Date;

public class FDRRecord extends Record{

	private final String fdrFile;
	private final Date StartDate;
	private final Date toDate;
	private final Date recievedDate;
	
	public FDRRecord(String fdrFile, String vPMN, Date startDate, Date toDate,
			String nrFile, Date recievedDate, int recordNum) {
		super(vPMN, nrFile, recordNum);
		this.fdrFile = fdrFile;
		StartDate = startDate;
		this.toDate = toDate;
		this.recievedDate = recievedDate;
	}

	public String getFdrFile() {
		return fdrFile;
	}

	public Date getStartDate() {
		return StartDate;
	}


	public Date getToDate() {
		return toDate;
	}


	public Date getRecievedDate() {
		return recievedDate;
	}




}
