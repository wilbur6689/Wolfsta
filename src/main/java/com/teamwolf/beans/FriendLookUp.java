package com.teamwolf.beans;

public class FriendLookUp {

    private int friendshipId = 0;
    private int userId1 = 0;
    private int userId2 = 0;

    /**
     * Constructor
     */
    public FriendLookUp() {

    }// public FriendLookUp()
    public int getFriendshipId() {
        return friendshipId;
    }// public int getFriendshipId()

    public void setFriendshipId(int friendshipId) {
        this.friendshipId = friendshipId;
    }// public void setFriendshipId

    public int getUserId1() {
        return userId1;
    }// public int getUserId1()

    public void setUserId1(int userId1) {
        this.userId1 = userId1;
    }// public void setUserId1(int userId1)

    public int getUserId2() {
        return userId2;
    }// public int getUserId2()

    public void setUserId2(int userId2) {
        this.userId2 = userId2;
    }// public void setUserId2(int userId2)
}// public class FriendLookUp
