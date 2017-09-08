package com.teamwolf.controller.response;

import com.teamwolf.beans.*;

import java.util.*;

public class UserResponse extends Response
{
    private User user;
    private List<User> friends;



    public UserResponse()
    {
    }


    public UserResponse(String message, boolean success)
    {
        super(message, success);
    }

    public UserResponse(Exception ex)
    {
        super(ex);
    }


    public void setUser(User user)
    {
        this.user = user;
    }
    public User getUser()
    {
        return user;
    }

    public List<User> getFriends()
    {
        return friends;
    }

    public void setFriends(List<User> friends)
    {
        this.friends = friends;
    }
}
