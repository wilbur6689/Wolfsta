var wolfsta = angular.module("wolfsta");
/**
 * tourCtrl is connected to the tour.html.
 * The function joinGame changes the view to joinGame.html.
 */
wolfsta.controller('tourCtrl', function($scope, $location){
    $scope.joinGame = function(){
        $location.path('/joinGame');
        //makes a call to DB to get current games and loads them
    }

 });