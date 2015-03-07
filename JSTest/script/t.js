  
  
  
  var RouteEntry = function(token,fn) {
		this.token = token;
		this.fn = fn;
		console.log("token = " + token);
		
	};

  
  var Router = function() {
		this.routes = new Array();
		
		this.add = function(token,fn) {
			var re = new RouteEntry(token,fn);		
			this.routes.push(re);
			console.log("add() : " + token);
			
		},
		this.match = function(token) {
			var m = false;
			var r = null;
			for(var i = 0; i < this.routes.length; ++i) {
				 r = this.routes[i];
				 console.log("r.token= " + r.token);
				if(token == r.token) {
					m = true;
					break;
				}
			}
			
			console.log("match() : " + r.token);
			//r.fn();
			return(m);
			
		}
							
			
	};

	var router = new Router();
	router.add("/about", function() {
			console.log("about callback");
	});
	
	router.match("/about");
