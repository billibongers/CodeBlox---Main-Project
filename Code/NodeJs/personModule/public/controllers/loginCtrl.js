var myApp = angular.module('myLogin', ['ngMessages']);
myApp.controller('LoginCtrl', ['$scope', '$http', function($scope, $http){

    $scope.login = function(form){
        var email = $scope.person.email;
        var password = $scope.person.password1;
        getUser(email, password);
    };

    var getUser = function(email, pass){
        $http.get("/returnUser/" + email + "/" + pass).success(function(response){
            if(response != null){
                angular.element(document.querySelector('.message')).html("You have succesfully logged in");
                window.location = 'gate.html';
            } else {
                angular.element(document.querySelector('.message')).html("You have provided incorrect credentials");
            }
        });
    }
}]);
