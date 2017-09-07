package com.teamwolf.controller;

public class AuthorizationException extends RuntimeException
{
    public AuthorizationException()
    {
    }

    public AuthorizationException(String s)
    {
        super(s);
    }

    public AuthorizationException(String s, Throwable throwable)
    {
        super(s, throwable);
    }

    public AuthorizationException(Throwable throwable)
    {
        super(throwable);
    }

    public AuthorizationException(String s, Throwable throwable, boolean b, boolean b1)
    {
        super(s, throwable, b, b1);
    }
}
