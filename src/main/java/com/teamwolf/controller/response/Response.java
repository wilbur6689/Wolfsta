package com.teamwolf.controller.response;

import java.util.*;

public class Response
{
    private boolean success;
    private String message;
    private List<StackTraceElement> stackTrace;

    public Response()
    {
    }
    public Response(String message)
    {
        this.message = message;
    }

    public Response(String message, boolean success)
    {
        this.message = message;
        this.success = success;
    }
    public Response(Exception ex)
    {
        this.message = ex.getMessage();
        this.stackTrace = Arrays.asList(ex.getStackTrace());
        this.success = false;
    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }

    public boolean getSuccess()
    {
        return success;
    }

    public void setSuccess(boolean success)
    {
        this.success = success;
    }

}
