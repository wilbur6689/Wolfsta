package com.teamwolf.controller.response;

public class SessionResponse extends Response
{
    private String status;
    private String success;
    private String token;

    private Integer id;

    public SessionResponse()
    {
    }


    public SessionResponse(String status, String success, String token, Integer id, String message)
    {
        super(message);
        this.status = status;
        this.success = success;
        this.token = token;
        this.id = id;

    }
    public SessionResponse(String message)
    {
        super(message);
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getSuccess()
    {
        return success;
    }

    public void setSuccess(String success)
    {
        this.success = success;
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
