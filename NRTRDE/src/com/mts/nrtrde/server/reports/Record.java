package com.mts.nrtrde.server.reports;


public class Record {
	private final String VPMN;
	private final String nrFile;
	private final int recordNum;
	
	public Record(String vPMN,String nrFile, int recordNum) {
		super();
		VPMN = vPMN;
		this.nrFile = nrFile;
		this.recordNum = recordNum;
	}


	public String getVPMN() {
		return VPMN;
	}

	public String getNrFile() {
		return nrFile;
	}

	public int getRecordNum() {
		return recordNum;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + recordNum;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FDRRecord other = (FDRRecord) obj;
		if (recordNum != other.getRecordNum())
			return false;
		return true;
	}


}
