package com.ava.scoreboard;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

class ScoreboardTest {

    @Test
    void shouldStartMatch() {
        InMemoryScoreboard scoreboard = new InMemoryScoreboard();

        scoreboard.startMatch("Mexico", "Canada");

        assertEquals(1, scoreboard.getSummary().size());
    }

    @Test
    void shouldUpdateScoreForLatestMatch() {
        InMemoryScoreboard scoreboard = new InMemoryScoreboard();

        scoreboard.startMatch("Mexico", "Canada");
        scoreboard.updateScore(2, 1);

        assertEquals(List.of("Mexico 2 - Canada 1"),scoreboard.getSummary());
    }

    @Test
    void shouldFinishLatestMatch() {
        InMemoryScoreboard scoreboard = new InMemoryScoreboard();

        scoreboard.startMatch("Mexico", "Canada");
        scoreboard.updateScore(2, 1);

        scoreboard.startMatch("Spain", "Brazil");
        scoreboard.updateScore(10, 2);

        scoreboard.finishMatch();

        assertEquals(
                List.of("Mexico 2 - Canada 1"),
                scoreboard.getSummary()
        );
    }

    @Test
    void shouldReturnSummaryOrderByTotalScoreThenRecency() {
        InMemoryScoreboard scoreboard = new InMemoryScoreboard();

        scoreboard.startMatch("Mexico", "Canada");
        scoreboard.updateScore(0, 5);

        scoreboard.startMatch("Spain", "Brazil");
        scoreboard.updateScore(10, 2);

        scoreboard.startMatch("Germany", "France");
        scoreboard.updateScore(2, 2);

        scoreboard.startMatch("Uruguay", "Italy");
        scoreboard.updateScore(6, 6);

        scoreboard.startMatch("Argentina", "Australia");
        scoreboard.updateScore(3, 1);

        assertEquals(
                List.of(
                        "Uruguay 6 - Italy 6",
                        "Spain 10 - Brazil 2",
                        "Mexico 0 - Canada 5",
                        "Argentina 3 - Australia 1",
                        "Germany 2 - France 2"
                ),
                scoreboard.getSummary()
        );
    }

    @Test
    void shouldReturnEmptySummaryWhenNoMatchExist() {
        InMemoryScoreboard scoreboard = new InMemoryScoreboard();

        assertEquals(List.of(), scoreboard.getSummary());
    }

    @Test
    void shouldDoNothingWhenFinishingEmptyScoreboard() {
        InMemoryScoreboard scoreboard = new InMemoryScoreboard();

        scoreboard.finishMatch();

        assertEquals(List.of(), scoreboard.getSummary());
    }

    @Test
    void shouldIgnoreUpdateScoreWhenNoMatches() {
        InMemoryScoreboard scoreboard = new InMemoryScoreboard();

        scoreboard.updateScore(2, 1);

        assertEquals(List.of(), scoreboard.getSummary());
    }

    @Test
    void shouldOrderByRecencyWhenTotalsAreEqual() {
        InMemoryScoreboard scoreboard = new InMemoryScoreboard();

        scoreboard.startMatch("Mexico", "Canada");
        scoreboard.updateScore(2, 2);

        scoreboard.startMatch("Spain", "Brazil");
        scoreboard.updateScore(3, 1);

        scoreboard.startMatch("Germany", "France");
        scoreboard.updateScore(0, 4);

        assertEquals(
                List.of(
                        "Germany 0 - France 4",
                        "Spain 3 - Brazil 1",
                        "Mexico 2 - Canada 2"
                ),
                scoreboard.getSummary()
        );
    }

}