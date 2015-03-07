/**
 * New node file
 */
"use strict";

var Person = function(first,last) {
	this.first = first;
	this.last = last;
	
	Person.prototype.getFirst = function(){
		
		return(this.first);
	
	},
	Person.prototype.getLast = function(){
		return(this.last);
	}
	
};

var Emp = function(id, first, last) {
	Person.call(this,first,last);
	this.id = id;
	Emp.prototype.getId = function() {
		return(this.id);
	}

}
Emp.prototype = Object.create(Person.prototype);
Emp.prototype.constructor = Emp;


var p = new Person("Bob","Hope");
console.log( p.getFirst() + " " + p.getLast());
var e = new Emp(1,"Joe","cool");
console.log( e.getId() + " " + e.getFirst() + " " + e.getLast());

