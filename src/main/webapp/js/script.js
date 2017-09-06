

var bankApp = angular.module("wolfsta", ["ngRoute"]);

bankApp.config(function($routeProvider) {
    $routeProvider
    .when("/", {
        templateUrl : "views/login.html",
        controller:'loginCtrl'
    }) 
    .when('/mainMenu',{
        templateUrl : "views/mainMenu.html",
        controller : 'mainMenuCtrl'
    })
    .when('/createGame',{
        templateUrl : "views/createGame.html",
        controller : 'createGameCtrl'
    })
    .when('/friendList',{
        templateUrl : "views/friendList.html",
        controller : 'friendListCtrl'
    })

    .when('/tour',{
        templateUrl : "views/tour.html",
        controller : 'tourCtrl'
    })

    .when('/joinGame',{
        templateUrl : "views/joinGame.html",
        controller : 'joinCtrl'
    })

});


bankApp.controller('loginCtrl', function($scope, $location){
   
    $scope.login = function(){
        $location.path('/mainMenu');
    }
});

bankApp.controller('mainMenuCtrl', function($scope, $location){

    $scope.joinGame = function(){
        $location.path('/joinGame');
        //makes a call to DB to get current games and loads them
    }

    $scope.createGame = function(){
        $location.path('/createGame');
    }

    $scope.friendList = function(){
        $location.path('/friendList');
    }

    $scope.tour = function(){
        $location.path('/tour');
    }

 });

 bankApp.controller('createGameCtrl', function($scope, $location){

    $scope.mainMenu = function(){
        $location.path('/mainMenu');
    }
 });

 bankApp.controller('friendListCtrl', function($scope, $location){
    $scope.mainMenu = function(){
        $location.path('/mainMenu');
    }
    $scope.friend_list = [
        {avatar : '1', username : 'person_one', rank : '3'},
        {avatar : '1', username : 'person_two', rank : '2' }
        ];


 });

 bankApp.controller('tourCtrl', function($scope, $location){
    $scope.mainMenu = function(){
        $location.path('/mainMenu');
    }

 });

 bankApp.controller('gameCtrl', function($scope, $location){
    $scope.mainMenu = function(){
        $location.path('/mainMenu');
    }

 });

 bankApp.controller('joinCtrl', function($scope, $location){
    $scope.mainMenu = function(){
        $location.path('/mainMenu');
    }

 });









