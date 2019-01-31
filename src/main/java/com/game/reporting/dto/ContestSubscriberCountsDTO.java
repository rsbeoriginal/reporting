package com.game.reporting.dto;

public class ContestSubscriberCountsDTO {
    private String contestId;
    private String contestName;
    private Integer subscriberCount;
    private String type;

    public String getContestId() {
        return contestId;
    }

    public void setContestId(String contestId) {
        this.contestId = contestId;
    }

    public String getContestName() {
        return contestName;
    }

    public void setContestName(String contestName) {
        this.contestName = contestName;
    }

    public Integer getSubscriberCount() {
        return subscriberCount;
    }

    public void setSubscriberCount(long subscriberCount) {
        this.subscriberCount = (int)subscriberCount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    public ContestSubscriberCountsDTO(String contestId, String contestName, String type, long subscriberCount) {
        this.contestId = contestId;
        this.contestName = contestName;
        this.subscriberCount = (int)subscriberCount;
        this.type = type;
    }
}
