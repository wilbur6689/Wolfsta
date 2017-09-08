var wolfsta = angular.module("wolfsta");

/**
 * friendListCtrl is connected to friendList.html.
 * The function joinGame changes the view to joinGame.html.
 * The $http to ./user/{id}/friends is used to access the database and get the list
 * of friends for the user.
*/
wolfsta.controller('friendListCtrl', function($scope, $location){
    
    $scope.joinGame = function(){
        $location.path('/joinGame'); 
    }

    $scope.friend_list = [
        {avatar : '1', username : 'person_one', rank : '3'},
        {avatar : '1', username : 'person_two', rank : '2' }
        ];
    $scope.input = '';

    // $http({method : 'POST', url : './user/{id}/friends', data : JSON.stringify(input)})
    // .then(function (response){
    //     if(response.data != null){
    //         friend_list.push(response.data.friends);
    //     }
    //     else{
    //         alert("Could not get friends");
    //     }
    // });

 });