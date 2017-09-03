package com.teamwolf.beans;

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

    public int getRewardId() {
        return rewardId;
    }

    public void setRewardId(int rewardId) {
        this.rewardId = rewardId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getReward_rule() {
        return reward_rule;
    }

    public void setReward_rule(int reward_rule) {
        this.reward_rule = reward_rule;
    }
}// public class Reward
