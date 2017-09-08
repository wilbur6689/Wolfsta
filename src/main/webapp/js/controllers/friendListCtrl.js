var wolfsta = angular.module("wolfsta");

wolfsta.controller('friendListCtrl', function($scope, $location){
    $scope.joinGame = function(){
        $location.path('/joinGame');
        //makes a call to DB to get current games and loads them
    }

    $scope.friend_list = [
        {avatar : '1', username : 'person_one', rank : '3'},
        {avatar : '1', username : 'person_two', rank : '2' }
        ];
    $scope.input = '';
    $http({method : 'POST', url : './user/{id}/friends', data : JSON.stringify(input)})
    .then(function (response){
        if(response.data != null){
            friend_list.push(response.data.friends);
        }
        else{
            alert("Could not get friends");
        }
    });

 });