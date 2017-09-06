package com.teamwolf.controller;


import com.google.gson.*;
import com.teamwolf.controller.request.*;
import com.teamwolf.controller.response.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/session")
public class SessionController
{
    private Gson geeson = new Gson(); //TODO: dependency injection


    @RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE,
            path = "/login",
            method = RequestMethod.POST)
    public String login(@RequestBody String json)
    {
        SessionRequest req = geeson.fromJson(json, SessionRequest.class);


        SessionResponse resp = new SessionResponse("loggedin","true","you arent really loggedin",-1,"this is used for errors");

        return geeson.toJson(resp);
    }

}
