package com.teamwolf.logic;

import com.teamwolf.beans.*;

import java.util.*;

public interface UserLogic
{
    User getUser(Integer uid);
    List<User> getFirends(Integer uid);
    boolean addUser(User user);
}
