var router = (function() {

if ("onhashchange" in window) { // event supported
    window.onhashchange = function () {
        //we cut of the actual hash
        console.log("hash= " + window.location.hash);
        var token = window.location.hash.split('#')[1];
        console.log("token= " + token);
        router.match(token);

    } 
} else    {
    //Support starts at IE8, however be very careful to have a correct DOCTYPE, 
    //because any IE going in Quircksmode will report having the event, but never fires it.
    console.log("hashchanging not supported!");
}

var Route = function(token,fn) {
	this.token = token;
	this.fn = fn;
	
};


// the router object
var Router = function() {
	this.routes = new Array();	
	this.add = function(token,fn) {
		var r = new Route(token,fn);		
		this.routes.push(r);
		console.log("add() : " + token);
		
	},
	this.match = function(token) {
		var m = false;
		for(var i = 0; i < this.routes.length; ++i) {
			var r = this.routes[i];
			if(token == r.token) {
				m = true;
				break;
			}
		}
		
		console.log("match() : " + r);
		if(m) {
			r.fn();
		}
		
	}
						
		
};

var r = new Router();
return(r);
// add as a gloabal.
//window.router = router;
})();