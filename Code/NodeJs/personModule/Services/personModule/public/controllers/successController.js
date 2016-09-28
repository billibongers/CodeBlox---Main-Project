var myApp = angular.module('successApp', []);
myApp.controller('successCtrl', ['$scope', '$http', function($scope, $http){
    $scope.navLogin = function(){
        window.location = 'login.html';
    };
}]);
