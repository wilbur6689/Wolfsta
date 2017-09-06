package com.teamwolf.controller.response;

import java.util.*;

public class Response
{
    private String message;
    private List<StackTraceElement> stackTrace;

    public Response()
    {
    }

    public Response(String message)
    {
        this.message = message;
    }
    public Response(Exception ex)
    {
        this.message = ex.getMessage();
        this.stackTrace = Arrays.asList(ex.getStackTrace());
    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }
}
