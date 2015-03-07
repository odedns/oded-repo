package com.mts.nrtrde.server.filters;

public class DateRangeCriteria extends Criteria<DateRange>{

	public DateRangeCriteria(DateRange argument, String column) {
		super(argument, column);
	}

	@Override
	public String addSQLCriteria() {
		return column + " between " + argument.getStartDate() + " and " + argument.getEndDate() + " ";
	}
	
	

}
