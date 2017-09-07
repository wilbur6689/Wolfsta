

var wolfsta = angular.module("wolfsta", ["ngRoute"]);

wolfsta.config(function($routeProvider) {
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

        .when('/rules',{
            templateUrl : "views/rules.html",
            controller : 'rulesCtrl'
        })

});


wolfsta.controller('loginCtrl', function($scope, $http, $location){
   
    $scope.login = function(player){
        $http({method : 'POST', url : '/ers_app/signIn/json', data : JSON.stringify(player)}).then(function (response){
            if(response.data != null){
                $location.path('/mainMenu');
            }
            else {
                alert(response.data);
            }
        });
    }
});

wolfsta.controller('mainMenuCtrl', function($scope, $location){

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

    $scope.rules = function(){
        $location.path('/rules');
    }

 });

 wolfsta.controller('createGameCtrl', function($scope, $location){

    $scope.mainMenu = function(){
        $location.path('/mainMenu');
    }
 });

 wolfsta.controller('friendListCtrl', function($scope, $location){
    $scope.mainMenu = function(){
        $location.path('/mainMenu');
    }
    $scope.friend_list = [
        {avatar : '1', username : 'person_one', rank : '3'},
        {avatar : '1', username : 'person_two', rank : '2' }
        ];


 });

 wolfsta.controller('tourCtrl', function($scope, $location){
    $scope.mainMenu = function(){
        $location.path('/mainMenu');
    }

 });

 wolfsta.controller('gameCtrl', function($scope, $location){
    $scope.mainMenu = function(){
        $location.path('/mainMenu');
    }

 });

 wolfsta.controller('joinCtrl', function($scope, $location){
    $scope.mainMenu = function(){
        $location.path('/mainMenu');
    }

 });

 wolfsta.controller('rulesCtrl', function($scope, $location){
     $scope.mainMenu = function(){
         $location.path('/mainMenu');
     }

  });









