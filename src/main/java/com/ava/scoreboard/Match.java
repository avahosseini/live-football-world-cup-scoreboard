package com.ava.scoreboard;

public class Match {

    String homeTeam;
    String awayTeam;

    int homeScore;
    int awayScore;

    long startTime;

    public Match(String homeTeam, String awayTeam) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;

    }
}
