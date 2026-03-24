package com.ava.scoreboard;

import java.util.ArrayList;
import java.util.List;

public class InMemoryScoreboard {

    private final List<Match> matches = new ArrayList<>();

    public void startMatch(String homeTeam, String awayTeam) {
        matches.add(new Match(homeTeam, awayTeam));
    }

    public void updateScore(int homeScore, int awayScore) {
        Match latest = matches.get(matches.size() - 1);
        latest.homeScore = homeScore;
        latest.awayScore = awayScore;
    }

    public void finishMatch() {
        if (matches.isEmpty()) {
            return; // no-op when there is nothing to finish
        }

        matches.remove(matches.size() - 1);
    }

    public List<String> getSummary() {
        return matches.stream()
                .map(m -> m.homeTeam + " " + m.homeScore + " - " + m.awayTeam + " " + m.awayScore)
                .toList();
    }
}
