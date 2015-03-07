
var R = function(data,fn) {
	this.data = data;
	this.fn = fn;
	console.log("R data =" + this.data);
	
}



function foo(data,fn) {
	
	console.log("data = " + data);
	var rr = new R(data,fn);
	rr.fn();
}


foo("some string", function() {
	
	console.log("some func");
});