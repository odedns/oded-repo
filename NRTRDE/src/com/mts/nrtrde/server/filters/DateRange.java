package com.mts.nrtrde.server.filters;

import java.util.Date;

public class DateRange {

	private final Date startDate;
	
	private final Date endDate;

	public DateRange(Date startDate, Date endDate) {
		super();
		if (endDate.before(startDate)){
			throw new IllegalArgumentException("can't create negative date range" );
		}
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public Date getStartDate() {
		return startDate;
	}

	public DateRange setStartDate(Date startDate) {
		return new DateRange(startDate, endDate);
	}

	public Date getEndDate() {
		return endDate;
	}

	public DateRange setEndDate(Date endDate) {
		return new DateRange(startDate, endDate);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((endDate == null) ? 0 : endDate.hashCode());
		result = prime * result
				+ ((startDate == null) ? 0 : startDate.hashCode());
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
		DateRange other = (DateRange) obj;
		if (endDate == null) {
			if (other.endDate != null)
				return false;
		} else if (!endDate.equals(other.endDate))
			return false;
		if (startDate == null) {
			if (other.startDate != null)
				return false;
		} else if (!startDate.equals(other.startDate))
			return false;
		return true;
	}
	
	
	
}
