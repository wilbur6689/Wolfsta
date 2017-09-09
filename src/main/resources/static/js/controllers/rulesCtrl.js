var wolfsta = angular.module("wolfsta");
/**
 * rulesCtrl is connected to the rules.html.
 * The function joinGame changes the view to joinGame.html.
 */
wolfsta.controller('rulesCtrl', function($scope, $location){
    $scope.joinGame = function(){
        $location.path('/joinGame');
        //makes a call to DB to get current games and loads them
    }

  });