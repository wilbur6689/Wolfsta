

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

bankApp.service('logoutService', function($scope, $http, $location){

    $scope.logout = function(){
        $http({method : 'POST', url : './services/session/logout', data : { token : logoutTokenVar}})
        .then(function(response){
            if(response.data.success == true){
                $location.path('/');
            }
            else{
                alert(response.data.message);
            }
        })
    }
});

bankApp.controller('loginCtrl', function($scope, $rootScope, $http, $location){
   
    $scope.login = function(player){
        $http({method : 'POST', url : './services/session/login', data : JSON.stringify(player)})
        .then(function (response){
            if(response.data == true){
                $rootScope.logoutTokenVar = response.data.token;
                $location.path('/mainMenu');
            }
            else {
                alert(response.data);
            }
        });
    }
});

bankApp.controller('mainMenuCtrl', function($scope, $location, logout){

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

 bankApp.controller('createGameCtrl', function($scope, $location, logout){

    $scope.mainMenu = function(){
        $location.path('/mainMenu');
    }
 });

 bankApp.controller('friendListCtrl', function($scope, $location, logout){
    $scope.mainMenu = function(){
        $location.path('/mainMenu');
    }
    $scope.friend_list = [
        {avatar : '1', username : 'person_one', rank : '3'},
        {avatar : '1', username : 'person_two', rank : '2' }
        ];


 });

 bankApp.controller('tourCtrl', function($scope, $location, logout){
    $scope.mainMenu = function(){
        $location.path('/mainMenu');
    }

 });

 bankApp.controller('gameCtrl', function($scope, $location, logout){
    $scope.mainMenu = function(){
        $location.path('/mainMenu');
    }

 });

 bankApp.controller('joinCtrl', function($scope, $location, logout){
    $scope.mainMenu = function(){
        $location.path('/mainMenu');
    }

 });









