var wolfsta = angular.module("wolfsta");

wolfsta.controller('rulesCtrl', function($scope, $location){
    $scope.joinGame = function(){
        $location.path('/joinGame');
        //makes a call to DB to get current games and loads them
    }

  });