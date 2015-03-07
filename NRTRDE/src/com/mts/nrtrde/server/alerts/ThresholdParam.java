package com.mts.nrtrde.server.alerts;

public class ThresholdParam {

	private final int value;
	private final boolean isColored;
	
	
	
	public ThresholdParam(int value, boolean isColored) {
		super();
		this.value = value;
		this.isColored = isColored;
	}
	public int getValue() {
		return value;
	}
	public boolean isColored() {
		return isColored;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + value;
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
		ThresholdParam other = (ThresholdParam) obj;
		if (value != other.value)
			return false;
		return true;
	}
	
	
}
