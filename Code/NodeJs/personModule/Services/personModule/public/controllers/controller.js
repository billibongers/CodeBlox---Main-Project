

var myApp = angular.module('myApp', []);
myApp.controller('AppCtrl', ['$scope', '$http', function($scope, $http){

    $scope.navRegistration = function(){
        window.location = 'register.html';
    };

    $scope.navLogin = function(){
        window.location = 'login.html';
    };
}]);
