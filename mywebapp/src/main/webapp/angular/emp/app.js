// main app file
var app = angular.module('empApp', []);

//This configures the routes and associates each route with a view and a controller
app.config(function ($routeProvider) {
    $routeProvider
        .when('/add',
            {
                controller: 'EmpController',
                templateUrl: 'partials/add.html'
            })
        //Define a route that has a route parameter in it (:customerID)
        .when('/list',
            {
                controller: 'EmpController',
                templateUrl: 'partials/list.html'
            })
        //Define a route that has a route parameter in it (:customerID)
        .when('/search',
            {
                controller: 'EmpController',
                templateUrl: 'partials/search.html'
            })
        .otherwise({ redirectTo: '/list' });
});




