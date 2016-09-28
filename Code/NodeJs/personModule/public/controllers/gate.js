var myApp = angular.module('myApp', []);
myApp.controller('AppCtrl', ['$scope', '$http', function($scope, $http){

    $scope.navRegistration = function(){
	var open=1;
          $http.get("/open").success(function(response){});
    };

    $scope.navLogin = function(){
       var close=1;
          $http.get("/close").success(function(response){});
    };  
}]);
