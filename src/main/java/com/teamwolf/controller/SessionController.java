package com.teamwolf.controller;


import com.google.gson.*;
import com.teamwolf.controller.request.*;
import com.teamwolf.controller.response.*;
import com.teamwolf.controller.sessionlogic.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping(path = "/session")
public class SessionController extends BaseController
{

    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST})
    public void notFound()
    {
        System.out.println("SessionController");
        throw new NotFoundException("url not found in SessionController");
    }

    @RequestMapping(consumes = "*/*",
            produces = MediaType.APPLICATION_JSON_VALUE,
            path = "/login",
            method = {RequestMethod.POST})
    public String login(@RequestBody SessionRequest request, Token token)
    {

        String tkn = token.authenticate(request.getUsername(),request.getPassword());

        SessionResponse resp = new SessionResponse("loggedin",tkn,token.getUser().getUserid(),"",true);

        return this.getGeeson().toJson(resp);
    }
    @RequestMapping(consumes = "*/*",
            produces = MediaType.APPLICATION_JSON_VALUE,
            path = "/logout",
            method = {RequestMethod.POST})
    public String logout(@RequestBody SessionRequest request)
    {

        System.out.println(this.getGeeson().toJson(request));
        SessionResponse resp = new SessionResponse("loggedin","you arent really loggedin",-1,"this is used for errors",true);

        return this.getGeeson().toJson(resp);
    }
    @RequestMapping(consumes = "*/*",
            produces = MediaType.APPLICATION_JSON_VALUE,
            path = "/check",
            method = {RequestMethod.POST})
    public String check(@RequestBody SessionRequest request)
    {

        System.out.println(this.getGeeson().toJson(request));
        SessionResponse resp = new SessionResponse("loggedin","you arent really loggedin",-1,"this is used for errors",true);

        return this.getGeeson().toJson(resp);
    }

}
