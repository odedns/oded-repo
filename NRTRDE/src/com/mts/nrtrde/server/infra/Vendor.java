package com.mts.nrtrde.server.infra;

public class Vendor {

	private final String code;
	private final String description;
	private final boolean isCommercial;
	
	public Vendor(String code, String description, boolean isCommercial) {
		super();
		this.code = code;
		this.description = description;
		this.isCommercial = isCommercial;
	}

	public String getCode() {
		return code;
	}

	public String getDescription() {
		return description;
	}

	public boolean isCommercial() {
		return isCommercial;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
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
		Vendor other = (Vendor) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		return true;
	}
	
	
}
