var wolfsta = angular.module("wolfsta");
/**
 * createGameCtrl is connected to createGame.html.
 * The function joinGame changes the view to joinGame.html.
*/
wolfsta.controller('createGameCtrl', function($scope, $location){
    
        $scope.joinGame = function(){
            $location.path('/joinGame');
           
        }
        $scope.createGame = function(game){
            $http({method : 'POST', url : '/games/new', data : JSON.stringify(game)}).then(function (response){
            });
        }
     });