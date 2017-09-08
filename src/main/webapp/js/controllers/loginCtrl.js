var wolfsta = angular.module("wolfsta");

/**
 * loginCtrl is connected to the login.html.
 * The login functions sends the player's username and password input to the java backend
 * using the $http /session/login then it takes the response and checks if it is not null.
 * If it is not null it changes the view to joinGame.html and stores the token and id from the response
 * else it alerts the user that they have inputted invalid information.
 */
wolfsta.controller('loginCtrl', function($scope, $http, $location){
    
     $scope.login = function(player){
         $http({method : 'POST', url : '/session/login', data : JSON.stringify(player)}).then(function (response){
             if(response.data.response != null){
                 $rootScope.token = response.data.token;
                 $rootScope.id = response.data.id;
                 $location.path('/joinGame');
             }
             else {
                 alert("Invalid input");
             }
         });
     }
 });