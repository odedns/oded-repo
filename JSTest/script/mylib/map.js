/**
 * New node file
 */
	
var Map = function() {
	this.table = new Object();
	this.length = 0;
	
	/*
	 * get object by key.
	 */
	Map.prototype.get= function(key) {
		return(this.table.hasOwnProperty(key) ? this.table[key] : undefined); 
	},
	
	/*
	 * put object by key.
	 */
	Map.prototype.put = function(key,value) {
		
		this.table[key] =value;
		this.length++;
	},

	/**
	 * remove item by key.
	 */
	Map.prototype.remove = function(key) {
		delete this.table[key];
		this.length--;
	},
	
	/**
	 * get all keys.
	 */
	Map.prototype.keys = function(){
		var kv =new Array();
		if(this.length > 0) {
			for(var k in this.table) {
				kv.push(k);
			}						
		}		
			
		return(kv);
	},
	
	/**
	 * iterate over all entries.
	 */
	Map.prototype.iterate = function(fn){
		if(this.length > 0) {
			for(var k in this.table) {
				var v = this.table[k];
				fn(v);
			}						
		}
	},

	/**
	 * get the map size.
	 */
	Map.prototype.size = function(){
		return(this.length);
	}
};	
	
	
	

var m = new Map();
m.put(1,"one");
m.put(2,"two");

console.log("got: " + m.get(1));
var kv = m.keys();
console.log("keys = " + kv);

m.iterate(function(v) {
	console.log("v = " +v);
});
