package com.teamwolf.dataAccess;

public class NotOneObjectException extends RuntimeException
{
    public NotOneObjectException()
    {
    }

    public NotOneObjectException(String s)
    {
        super(s);
    }

    public NotOneObjectException(String s, Throwable throwable)
    {
        super(s, throwable);
    }

    public NotOneObjectException(Throwable throwable)
    {
        super(throwable);
    }

    public NotOneObjectException(String s, Throwable throwable, boolean b, boolean b1)
    {
        super(s, throwable, b, b1);
    }
}
