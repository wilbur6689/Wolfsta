package com.teamwolf.controller;


import com.teamwolf.beans.*;
import com.teamwolf.controller.response.*;
import com.teamwolf.logic.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/user")
public class UserController extends BaseController
{
    @Autowired
    UserLogic logic;
    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST})
    public void notFound()
    {
        System.out.println("SessionController");
        throw new NotFoundException("url not found in UserController");
    }

    @RequestMapping(consumes = "*/*",
            produces = MediaType.APPLICATION_JSON_VALUE,
            path = "/{uid}",
            method = {RequestMethod.GET})
    public String get(@PathVariable Integer uid)
    {
        UserResponse resp = new UserResponse();
        resp.setUser(logic.getUser(uid));
        resp.setSuccess(true);
        return this.getGeeson().toJson(resp);
    }
    @RequestMapping(consumes = "*/*",
            produces = MediaType.APPLICATION_JSON_VALUE,
            path = "/{uid}/friends",
            method = {RequestMethod.GET})
    public String getFriends(@PathVariable Integer uid)
    {
        UserResponse resp = new UserResponse();
        resp.setFriends(logic.getFirends(uid));
        resp.setSuccess(true);
        return this.getGeeson().toJson(resp);
    }
    @RequestMapping(consumes = "*/*",
            produces = MediaType.APPLICATION_JSON_VALUE,
            path = "/new",
            method = {RequestMethod.POST})
    public String newUser(@RequestBody User u)
    {
        UserResponse resp = new UserResponse();
        resp.setSuccess(logic.addUser(u));
        return this.getGeeson().toJson(resp);
    }
}
