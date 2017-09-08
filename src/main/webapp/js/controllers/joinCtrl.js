var wolfsta = angular.module("wolfsta");

wolfsta.controller('joinCtrl', function($scope, $location){
    
        $scope.game1 = "game1";
        $scope.game2 = "garbage"
    
        $scope.createGame = function(){
            $location.path('/createGame');
        }
     });