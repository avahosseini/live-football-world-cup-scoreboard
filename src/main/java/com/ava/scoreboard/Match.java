package com.ava.scoreboard;

public class Match {

    String homeTeam;
    String awayTeam;

    int homeScore;
    int awayScore;

    long startTime;

    public Match(String homeTeam, String awayTeam, Long startTime) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.homeScore = 0;
        this.awayScore = 0;
        this.startTime = startTime;

    }
}
