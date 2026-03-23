package com.ava.scoreboard;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
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
}