
var Person = {
		
		first: '',
		last: '',
		getFirst: function() {
			return(this.first);
		},
		getLast: function() {
			return(this.last);
		},		
		constructor : function(first,last){
			this.first = first;
			this.last = last;
		}
		
};

var Employee = Object.create(Person,{
		id: { value: 100}		
		
				
});

Employee.prototype.getId = function(){
	return(this.id);
}

function Car(make, model, level, color, warranty) {
    this.make     = make;
    this.model    = model;
    this.level    = level;
    this.color    = color;
    this.warranty = warranty;
}
 
Car.prototype = {
    getInfo: function () {
      return this.make + ', ' + this.model + ', ' + this.level + ', '+ this.color + ', ' + this.warranty;
    }
};

//var p1 = new Person("Joe","Webb");
var p2 = Object.create(Person,{first: {value: "Joe"},last: {value: "Webb"}});

//console.log(p1.getFirst() + " " + p1.getLast());
console.log(p2.getFirst() + " " + p2.getLast());

var e1 = Object.create(Employee);
console.log("e1 = " + e1.getId()+ " " + e1.getFirst() + " " + e1.getLast());

var c = new Car("Nissan","Tida",1,"Blue,",3);
console.log(c.getInfo());
