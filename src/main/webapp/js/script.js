

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

});


bankApp.controller('loginCtrl', function($scope, $location){
   
    $scope.mainMenu = function(){
        $location.path('/mainMenu');
    }
});

bankApp.controller('mainMenuCtrl', function($scope, $location){
    //deposit function 
    $scope.deposit = function(){
        $location.path('/deposit');
    }
    //withdraw funciton
    $scope.withdraw = function(){
        $location.path('/withdraw');
    }

 });

 bankApp.controller('createGameCtrl', function($scope, $location){
    //goHome function 
    $scope.depHome = function(){
        $location.path('/home');
    }

 });

 bankApp.controller('friendListCtrl', function($scope, $location){
    //goHome function 
    $scope.home = function(){
        $location.path('/home');
    }
    var friend_list = [
        {'avatar' : '1', 'username' : 'person_one', 'rank' : '3' },
        {'avatar' : '1', 'username' : 'person_two', 'rank' : '2' }
        ];


 });

 bankApp.controller('tourCtrl', function($scope, $location){
    //goHome function 
    $scope.displayImage = function(){
        $location.path('/displayImage');
    }

 });

 // Get the modal
var modal = document.getElementById('friends');

// Get the button that opens the modal
var btn = document.getElementById("myBtn");

// Get the <span> element that closes the modal
var span = document.getElementsByClassName("close")[0];

// When the user clicks on the button, open the modal 
btn.onclick = function() {
    modal.style.display = "block";
}

// When the user clicks on <span> (x), close the modal
span.onclick = function() {
    modal.style.display = "none";
}

// When the user clicks anywhere outside of the modal, close it
window.onclick = function(event) {
    if (event.target == modal) {
        modal.style.display = "none";
    }
}








