package com.teamwolf.beans;


import javax.persistence.*;
import java.io.*;

@Entity
@Table(name = "REWARD")
public class Reward {
    private int rewardId = 0;
    private int userId = 0;
    private int reward_rule = 0;
    // protected Logger log = Logger.getRootLogger();

    /**
     * Constructor
     */
    public Reward() {

    }// public Reward()

    @Id
    @Column(name="REWARD_ID")
    public int getRewardId() {
        return rewardId;
    }
    public void setRewardId(int rewardId) {
        this.rewardId = rewardId;
    }

    @Column(name="USER_ID")
    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Column(name="REWARD_RULE")
    public int getReward_rule() {
        return reward_rule;
    }
    public void setReward_rule(int reward_rule) {
        this.reward_rule = reward_rule;
    }


}// public class Reward
