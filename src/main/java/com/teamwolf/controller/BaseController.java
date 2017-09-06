package com.teamwolf.controller;

import com.google.gson.*;
import com.teamwolf.controller.response.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
public class BaseController
{
    private Gson geeson = new Gson(); //TODO: dependency injection

    //@RequestMapping(path = "/*", produces= MediaType.APPLICATION_JSON_VALUE, method = {RequestMethod.GET, RequestMethod.POST})
    public String defaultPage(){
        System.out.println("BaseController");
        return this.getGeeson().toJson(new Response(MediaType.APPLICATION_JSON_VALUE));
    }

    @ExceptionHandler(Throwable.class)
    public String error(Exception ex)
    {
        return geeson.toJson(new Response(ex));
    }

    protected Gson getGeeson()
    {
        return geeson;
    }

    protected void setGeeson(Gson geeson)
    {
        this.geeson = geeson;
    }
}
