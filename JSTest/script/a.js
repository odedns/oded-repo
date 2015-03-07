// define class
var apple = {
    type: "macintosh",
    color: "red",
    getInfo: function () {
        return this.color + ' ' + this.type + ' apple';
    }
}
apple.color = "reddish";
console.log(apple.getInfo())

// another way
function Apple (type) {
    this.type = type;
    this.color = "red";
    this.getInfo = function() {
        return this.color + ' ' + this.type + ' apple';
    };
}

var app = new Apple("foo");
console.log(app.getInfo());

