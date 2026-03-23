package com.ava.scoreboard;

import java.util.ArrayList;
import java.util.List;

public class InMemoryScoreboard {

    private final List<Match> matches = new ArrayList<>();

    public void startMatch(String homeTeam, String awayTeam) {
        matches.add(new Match(homeTeam, awayTeam));
    }

    public void updateScore(int homeScore, int awayScore) {
        // TODO: implement in next commit
    }

    public List<String> getSummary() {
        return matches.stream()
                .map(m -> m.homeTeam + " " + m.homeScore + " - " + m.awayTeam + " " + m.awayScore)
                .toList();
    }
}
