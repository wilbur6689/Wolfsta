var wolfsta = angular.module("wolfsta");
/**
 * createGameCtrl is connected to createGame.html.
 * The function joinGame changes the view to joinGame.html.
*/
wolfsta.controller('createGameCtrl', function($scope, $location){
    
        $scope.joinGame = function(){
            $location.path('/joinGame');
            //makes a call to DB to get current games and loads them
        }
        $scope.createGame = function(game){
            $http({method : 'POST', url : '/games/new', data : JSON.stringify(game)}).then(function (response){
            });
        }
     });