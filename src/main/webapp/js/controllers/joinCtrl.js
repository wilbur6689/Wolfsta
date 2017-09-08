var wolfsta = angular.module("wolfsta");

wolfsta.controller('joinCtrl', function($scope, $location){
    
        $scope.gamesList = [{
            name: 'Game #374 - public',
            type: 'public, all'
        },
        {
            name: 'Game #256 - public',
            type: 'public, all'
        },
        {
            name: 'Game #145 - public',
            type: 'public, all'
        },
        {
            name: 'Game #182 - private',
            type: 'private, all'
        },
        {
            name: 'Game #263 - private',
            type: 'private, all'
        }
        ];

        // define list of items
        $scope.gametypes = ["all","public","private"];

        // initialize filter object
        $scope.filter = {};

        // reset the filter
        $scope.resetFilter = function() {
        // set filter object as blank
        $scope.filter = {}; 
        }

    
        $scope.createGame = function(){
            $location.path('/createGame');
        }
     });