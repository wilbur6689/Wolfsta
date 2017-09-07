package com.teamwolf.logic.dummy;

import com.teamwolf.beans.*;
import com.teamwolf.logic.*;
import org.springframework.stereotype.*;

import java.util.*;

@Component
public class UserDummy implements UserLogic
{

    User user1 = new User();
    User user2 = new User();

    {
        user1.setUsername("cJohnson");
        user1.setGamesPlayed(1);
        user1.setGamesWon(5);
        user1.setUserid(1);
        user2.setUsername("GLaDOS");
        user2.setGamesPlayed(9001);
        user2.setGamesWon(9001);
        user2.setUserid(2);
    }

    @Override
    public User getUser(Integer uid)
    {
        if (uid.equals(1)) return user1;
        if (uid.equals(2)) return user2;
        return null;
    }

    @Override
    public List<User> getFirends(Integer uid)
    {
        if(uid.equals(1))
        {
            List<User> r = new ArrayList<>();
            r.add(user1);
            r.add(user2);
            return r;
        }
        else
            return null;
    }

    @Override
    public boolean addUser(User user)
    {
        if ("lemons".equals(user.getPassword()))
            return true;
        else
            return false;
    }
}
