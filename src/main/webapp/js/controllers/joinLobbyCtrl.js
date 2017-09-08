var wolfsta = angular.module("wolfsta");

wolfsta.controller('joinLobbyCtrl', function($scope, $location){
    
        $scope.playerList = [{
            player: 'Player #1',
            userName: 'Tom'
        },
        {
            player: 'Player #2',
            userName: 'Samantha'
        },
        {
            player: 'Player #3',
            userName: 'Betty'
        },
        {
            player: 'Player #4',
            userName: 'Roger'
        }];

        // define list of items
        $scope.readyState = ["Not Ready","Ready"];
    
        $scope.startGame = function(){
            $location.path('/game');
        }
     });