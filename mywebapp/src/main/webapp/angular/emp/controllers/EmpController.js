//empController.j
  
app.controller('EmpController', function ($scope, empService) {
	
    //I like to have an init() for controllers that need to perform some initialization. Keeps things in
    //one place...not required though especially in the simple example below
    init();

    function init() {
    	
    	empService.getEmployees(function callback(data) {
    		console.log("in callback data length = " + data.length);
    		$scope.employees = data;
    	});
    	console.log("in EmpController.init() employees - " + $scope.employees);
    	console.log("emp = " + $scope.employees);
    	$scope.msg = "";
    
    }

    $scope.addEmp = function () {
        console.log("in add emp");
        empService.addEmp($scope.emp);
        $scope.msg = "employee added";
    };
    $scope.searchEmp = function () {
        console.log("in search emp");
        $scope.res = empService.search();
        console.log("got res from search = " + res);
    };
    $scope.listEmp = function (id) {
    	console.log("emp = " + $scope.employees);
    };
});

