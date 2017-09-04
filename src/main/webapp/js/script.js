

var bankApp = angular.module("angularBankApp", ["ngRoute"]);

bankApp.config(function($routeProvider) {
    $routeProvider
    .when("/", {
        templateUrl : "views/login.html",
        controller:'loginCtrl'
    }) 
    .when('/home',{
        templateUrl : "views/home.html",
        controller : 'homeCtrl'
    })
    .when('/deposit',{
        templateUrl : "views/deposit.html",
        controller : 'depositCtrl'
    })
    .when('/withdraw',{
        templateUrl : "views/withdraw.html",
        controller : 'withdrawCtrl'
    })

    .when('/displayImage',{
        templateUrl : "views/displayImage.html",
        controller : 'displayImageCtrl'
    })

});


bankApp.controller('loginCtrl', function($scope, $location){
   
    $scope.login = function(){
        $location.path('/home');
    }
});

bankApp.controller('homeCtrl', function($scope, $location){
    //deposit function 
    $scope.deposit = function(){
        $location.path('/deposit');
    }
    //withdraw funciton
    $scope.withdraw = function(){
        $location.path('/withdraw');
    }

 });

 bankApp.controller('depositCtrl', function($scope, $location){
    //goHome function 
    $scope.depHome = function(){
        $location.path('/home');
    }

 });

 bankApp.controller('withdrawCtrl', function($scope, $location){
    //goHome function 
    $scope.home = function(){
        $location.path('/home');
    }

 });

 bankApp.controller('displayImageCtrl', function($scope, $location){
    //goHome function 
    $scope.displayImage = function(){
        $location.path('/displayImage');
    }

 });








