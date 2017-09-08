var wolfsta = angular.module("wolfsta", ["ngRoute"]);
/**
 * The config file for all of the controllers.
 */
wolfsta.config(function($routeProvider) {
    $routeProvider

    .when('/createGame',{
        templateUrl : "views/createGame.html",
        controller : 'createGameCtrl'
    })

    .when('/friendList',{
        templateUrl : "views/friendList.html",
        controller : 'friendListCtrl'
    })

    .when('/gameCtrl',{
        templateUrl : 'views/game.html',
        controller : 'gameCtrl.js'
    })

    .when('/joinGame',{
        templateUrl : "views/joinGame.html",
        controller : 'joinCtrl'
    })

    .when("/", {
        templateUrl : "views/login.html",
        controller:'loginCtrl'
    }) 

    .when('/rules',{
        templateUrl : "views/rules.html",
        controller : 'rulesCtrl'
    })

    .when('/tour',{
        templateUrl : "views/tour.html",
        controller : 'tourCtrl'
    })


});

