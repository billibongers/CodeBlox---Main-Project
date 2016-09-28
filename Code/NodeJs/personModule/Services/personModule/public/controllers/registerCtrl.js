
var myApp = angular.module('myReg', ['ngMessages']);
myApp.controller('RegCtrl', ['$scope', '$http', function($scope, $http){

    // $http.get('/personlist').success(function(response){
    //     console.log("I got the data I requested");
    //     $scope.personlist = response;
    // });

    $scope.addPerson = function(form){
        var email = $scope.person.email;
        $http.get("/returnUser/" + email).success(function(response){
            if(response){
                angular.element(document.querySelector('.message')).html("Person with that email has already been registered!");
            } else {
                $http.post('/registration', $scope.person).success(function(response){
                    console.log(response);
                    window.location = "/success.html";
                });
            }
        });
    };
}]);
