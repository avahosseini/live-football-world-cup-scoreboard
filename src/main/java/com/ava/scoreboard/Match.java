package com.ava.scoreboard;

public class Match {

    private final String homeTeam;
    private final String awayTeam;

    private int homeScore;
    private int awayScore;

    private final long startTime;

    public Match(String homeTeam, String awayTeam, Long startTime) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.homeScore = 0;
        this.awayScore = 0;
        this.startTime = startTime;
    }

    public String getHomeTeam() {
        return homeTeam;
    }

    public String getAwayTeam() {
        return awayTeam;
    }

    public int getHomeScore() {
        return homeScore;
    }

    public int getAwayScore() {
        return awayScore;
    }

    public long getStartTime() {
        return startTime;
    }

    public void updateScore(int homeScore, int awayScore) {
        this.homeScore = homeScore;
        this.awayScore = awayScore;
    }
}
