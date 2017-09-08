package com.teamwolf.data;

import com.teamwolf.beans.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

import java.util.*;

@Repository
public interface FriendLookUpData extends JpaRepository<FriendLookUp,Integer>
{
    default List<FriendLookUp> findAllByUserId1OrUserId2(Integer userId)
    {
        return findAllByUserId1OrUserId2(userId,userId);
    }

    List<FriendLookUp> findAllByUserId1OrUserId2(Integer userId1 ,Integer userId2);

}
