package com.teamwolf.beans;

import com.teamwolf.dataAccess.*;

import javax.persistence.*;
import java.io.*;

@Entity
@Table(name = "FRIEND_LOOKUP")
public class FriendLookUp implements DataObject
{

    private int friendshipId = 0;
    private int userId1 = 0;
    private int userId2 = 0;
    private int status;
    // protected Logger log = Logger.getRootLogger();

    /**
     * Constructor
     */
    public FriendLookUp() {

    }// public FriendLookUp()
    @Id
    @Column(name="FRIENDSHIP_ID")
    public int getFriendshipId() {
        return friendshipId;
    }// public int getFriendshipId()
    public void setFriendshipId(int friendshipId) {
        this.friendshipId = friendshipId;
    }// public void setFriendshipId

    @Column(name="USER_ID_1")
    public int getUserId1() {
        return userId1;
    }// public int getUserId1()

    public void setUserId1(int userId1) {
        this.userId1 = userId1;
    }// public void setUserId1(int userId1)


    @Column(name="USER_ID_2")
    public int getUserId2() {
        return userId2;
    }// public int getUserId2()

    public void setUserId2(int userId2) {
        this.userId2 = userId2;
    }// public void setUserId2(int userId2)

    public int getStatus()
    {
        return status;
    }

    public void setStatus(int status)
    {
        this.status = status;
    }

    @Transient
    @Override
    public Serializable getID()
    {
        return this.getFriendshipId();
    }
}// public class FriendLookUp
