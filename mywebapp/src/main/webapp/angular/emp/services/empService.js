// empService.js
	var e = [];

app.service('empService', function ($q,$http) {
    this.addEmp = function(emp){
    	console.log("e = " + e);
        e.push({name: emp.firstname, lastname: emp.lastname});
    	console.log("passed to service: " + emp);
    	console.log("e = " + e);
    };
    this.getEmployees = function (callback) {
    	if(e.length == 0) {
    		$http.get('/angular/emp/emp.json').success(function(data) {
    			console.log("data= " + data.e);
    			e = data.e;
    			callback(e);
        
    		});
          	
    	} else {
    		callback(e);
    	}
    };
    this.search = function() {
    	var deferred = $q.defer();
    	$http.get('/angular/emp/search.json').success(function(data) {
			console.log("data= " + data.e);
			deferred.resolve(data.e);
    
		});
    	return(deferred.promise);
    };
	
});

