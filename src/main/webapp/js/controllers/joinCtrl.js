var wolfsta = angular.module("wolfsta");

wolfsta.controller('joinCtrl', function($scope, $location){
    
        $scope.gamesList = [{
            name: 'Game #374'
        },
        {
            name: 'Game #256'
        },
        {
            name: 'Game #145'
        },
        {
            name: 'Game #182'
        },
        {
            name: 'Game #263'
        },
        ];

    
        $scope.createGame = function(){
            $location.path('/createGame');
        }
     });