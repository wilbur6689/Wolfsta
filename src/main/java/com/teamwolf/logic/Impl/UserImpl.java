package com.teamwolf.logic.Impl;

import com.teamwolf.beans.*;
import com.teamwolf.data.*;
import com.teamwolf.logic.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import java.util.*;

@Service
public class UserImpl implements UserLogic
{
    @Autowired
    private UserData userData;
    @Autowired
    private FriendLookUpData friendLookUpData;


    @Override
    public User getUser(Integer uid)
    {
        User u = userData.findOne(uid);
        if (u != null)
        {
            u.setPassword(null);
            return u;
        }
        return null;
    }

    @Override
    public List<User> getFirends(Integer uid)
    {
        List<FriendLookUp> listA = friendLookUpData.findAllByUserId1OrUserId2(uid);
        List<User> listB = new LinkedList<>();
        for (FriendLookUp fl:listA)
        {
            User u = null;
            if(uid.equals(fl.getUserId1()))
                u = userData.findOne(fl.getUserId2());
            else
                u = userData.findOne(fl.getUserId1());
            listB.add(u);
        }


        return listB;
    }


    @Override
    public boolean addUser(User user) //TODO: implment add user
    {
        return false;
    }
}
