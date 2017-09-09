var wolfsta = angular.module("wolfsta");

wolfsta.service("gameMgrService", function($http, loginService)
{
    var service = {};

    service.createNewGame = function(callback)
            {
                $http.get('./services/user/' + id + '/friends').success(function (response)
                {
                    friends = response.data.friends;
                    callback(friends);

                })


            }

    service.getGames = function(callback)
            {
                    $http.get('./services/user/' + id).success(function (response)
                    {
                        user = response.data.user;
                        callback(user);
                    });
            }

    return service;
});