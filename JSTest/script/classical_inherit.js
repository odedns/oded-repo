/**
 * New node file
 */

// Parent class constructor
function Parent() {
  this.a = 42;
  
}

// Parent class method
Parent.prototype.method = function method() {
	console.log("parent.method()");
};

Parent.prototype.getA = function(){
	console.log("in getA()");
	return(this.a);
}

// Child class constructor
function Child() {
  Parent.call(this);
  this.b = 3.14159;
}

// Inherit from the parent class
Child.prototype = Object.create(Parent.prototype);
Child.prototype.constructor = Child;

// Child class method
Child.prototype.method = function method() {
  Parent.prototype.method.call(this);
};


// Instantiate
this.instance = new Child();
console.log(this.instance.b + " " + this.instance.a);
this.instance.method();
this.instance.getA();