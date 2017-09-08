var wolfsta = angular.module("wolfsta");


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