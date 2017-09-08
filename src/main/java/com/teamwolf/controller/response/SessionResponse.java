package com.teamwolf.controller.response;

public class SessionResponse extends Response
{
    private String status;
    private String token;

    private Integer id;

    public SessionResponse()
    {
    }


    public SessionResponse(String status, String token, Integer id, String message, boolean success)
    {
        super(message,success);
        this.status = status;
        this.token = token;
        this.id = id;

    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getToken()
    {
        return token;
    }

    public void setToken(String token)
    {
        this.token = token;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }
}
