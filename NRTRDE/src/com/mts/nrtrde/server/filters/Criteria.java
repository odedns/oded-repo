package com.mts.nrtrde.server.filters;


public  class Criteria <T>{

	protected T argument;
	protected String column;
	
	public Criteria(T argument, String column) {
		super();
		this.argument = argument;
		this.column = column;
	}

	public T getArgument() {
		return argument;
	}
	
	public  String addSQLCriteria(){
		return column + "=" + argument.toString() + " "; 
	}
}
