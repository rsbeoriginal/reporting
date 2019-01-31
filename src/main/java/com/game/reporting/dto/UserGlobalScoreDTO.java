package com.game.reporting.dto;

public class UserGlobalScoreDTO {
    private String userId;
    private String userName;
    private double score;
    private Integer totalContestsPlayed;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public Integer getTotalContestsPlayed() {
        return totalContestsPlayed;
    }

    public void setTotalContestsPlayed(Integer totalContestsPlayed) {
        this.totalContestsPlayed = totalContestsPlayed;
    }

    public UserGlobalScoreDTO() {
    }

    public UserGlobalScoreDTO(String userId, double score, long totalContestsPlayed) {
        this.userId = userId;
        this.score = score;
        this.totalContestsPlayed = (int)totalContestsPlayed;
    }
}
