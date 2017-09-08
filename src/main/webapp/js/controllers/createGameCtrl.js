var wolfsta = angular.module("wolfsta");
/**
 * createGameCtrl is connected to createGame.html.
 * The function joinGame changes the view to joinGame.html.
*/
wolfsta.controller('createGameCtrl', function($scope, $location){
    
        $scope.joinGame = function(){
            $location.path('/joinGame');
           
        }

         // define the list of number of player options
         $scope.numPlayers = ["4 Player","6 Player","8 Player"];

         // define the list of timer options
         $scope.timerList = ["3 Minutes", "5 Minutes", "8 Minutes"]

        $scope.createGame = function(game){
            $http({method : 'POST', url : '/games/new', data : JSON.stringify(game)}).then(function (response){
            });
        }
     });