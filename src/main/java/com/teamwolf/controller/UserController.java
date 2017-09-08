package com.teamwolf.controller;


import com.teamwolf.beans.*;
import com.teamwolf.controller.response.*;
import com.teamwolf.logic.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

@Controller
//@RestController("userController")
@RequestMapping(path="/services/user")
public class UserController extends BaseController
{
    @Autowired
    public void setUserLogic(@Qualifier("userImpl") UserLogic userLogic)
    {
        this.userLogic = userLogic;
    }


    private UserLogic userLogic;
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
        resp.setUser(userLogic.getUser(uid));
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
        resp.setFriends(userLogic.getFirends(uid));
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
        resp.setSuccess(userLogic.addUser(u));
        return this.getGeeson().toJson(resp);
    }
}
