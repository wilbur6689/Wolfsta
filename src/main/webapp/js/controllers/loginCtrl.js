var wolfsta = angular.module("wolfsta");


wolfsta.controller('loginCtrl', function($scope, $http, $location){
    
     $scope.login = function(player){
         $http({method : 'POST', url : '/ers_app/signIn/json', data : JSON.stringify(player)}).then(function (response){
             if(response.data != null){
                 $location.path('/mainMenu');
             }
             else {
                 alert(response.data);
             }
         });
     }
 });