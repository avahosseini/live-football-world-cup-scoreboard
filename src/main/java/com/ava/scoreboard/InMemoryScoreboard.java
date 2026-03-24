package com.ava.scoreboard;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class InMemoryScoreboard {

    private final List<Match> matches = new ArrayList<>();
    private long sequence = 0;

    public void startMatch(String homeTeam, String awayTeam) {
        matches.add(new Match(homeTeam, awayTeam, ++sequence));
    }

    public void updateScore(int homeScore, int awayScore) {
        if (homeScore < 0 || awayScore < 0) {
            throw new IllegalArgumentException("Scores cannot be negative");
        }

        if (matches.isEmpty()) {
            return;
        }

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
        Comparator<Match> byTotalThenRecency =
                Comparator.comparingInt((Match m) -> m.homeScore + m.awayScore).thenComparingLong(m -> m.startTime).reversed();

        return matches.stream()
                .sorted(byTotalThenRecency)
                .map(m -> m.homeTeam + " " + m.homeScore + " - " + m.awayTeam + " " + m.awayScore)
                .toList();
    }
}
