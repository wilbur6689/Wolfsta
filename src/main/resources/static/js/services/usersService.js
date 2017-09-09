var wolfsta = angular.module("wolfsta");

wolfsta.service("usersService", function($http, loginService)
{
    var service = {};

    service.getUserFriends = function(id, callback)
            {
                $http.get('./services/user/' + id + '/friends').success(function (response)
                {
                    friends = response.data.friends;
                    callback(friends);

                })


            }

    service.getUser = function(id, callback)
            {
                    $http.get('./services/user/' + id).success(function (response)
                    {
                        user = response.data.user;
                        callback(user);
                    });
            }

    return service;
});