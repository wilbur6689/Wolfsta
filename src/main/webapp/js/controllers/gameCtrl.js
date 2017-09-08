var wolfsta = angular.module("wolfsta");
/**
 * gameCtrl is connected to the game.html.
 * The function joinGame changes the view to joinGame.html.
 */
wolfsta.controller('gameCtrl', function($scope, $location){
    $scope.joinGame = function(){
        $location.path('/joinGame');
        //makes a call to DB to get current games and loads them
    }

 });