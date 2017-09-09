var wolfsta = angular.module("wolfsta");

wolfsta.service("sessionService", function($http, loginService)
{
    var service = {};

    service.login = function(username, password, callback)
            {
                $http.get('/session/login', {'username': username, 'password': password}).success(function (response)
                {
                    token = response.data.token;
                    id = response.data.id
                    callback(token, id);
                })
            }

    service.logout = function(token, callback)
            {
                    $http.get('./session/logout', {'token': token}).success(function (response)
                    {
                        success = response.data.success;
                        callback(success);
                    });
            }

    service.check = function(token, callback)
                {
                    $http.get('/session/check', {'token': token}).success(function (response)
                    {
                        status = response.data.status;
                        user = response.data.user
                        callback(status, user);
                    })
                }

    return service;
});