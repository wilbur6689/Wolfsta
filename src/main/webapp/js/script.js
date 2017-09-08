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