# Live Football World Cup Scoreboard

A simple in-memory Java library for tracking live football matches and producing a ranked summary of matches currently
in progress.

## Supported operations

1. **Start a new match**  
   `startMatch(homeTeam, awayTeam)`
    - Starts a match with an initial score of `0-0`
    - Marks the match as in progress

2. **Update score**  
   `updateScore(homeScore, awayScore)`
    - Updates the score of the most recently started match in progress

3. **Finish match**  
   `finishMatch()`
    - Removes the most recently started match from the scoreboard

4. **Get ordered summary**  
   `getSummary()`
    - Returns all matches currently in progress
    - Ordered by:
        1. Total score `(homeScore + awayScore)` descending
        2. Most recently started match first (if totals are equal)

## Summary format

Each match is represented as:  
`<HomeTeam> <homeScore> - <AwayTeam> <awayScore>`

Example:  
`Uruguay 6 - Italy 6`

## Design Assumptions & Constraints

The task clearly defines how matches should be summarized and ordered. However, it does not specify how
`updateScore(...)` and `finishMatch()` should select a specific match when multiple matches are in progress at the same
time.

Since neither method accepts a match identifier, this creates ambiguity.

To ensure deterministic and predictable behavior, this implementation applies both operations to the most recently
started match that is still in progress.

This approach:

- Provides a clear and consistent rule for handling multiple matches
- Keeps behavior aligned across operations
- Fits naturally with the task’s use of recency in summary ordering

An alternative design could introduce a match identifier or use team names to target specific matches, but such
mechanisms are not part of the given method interface.

This behavior is verified through automated tests, ensuring that finishing a match correctly promotes
the previous match to the latest active match.

## Technical details

- In-memory implementation (no persistence)
- Java (Maven project)
- JUnit 5 for testing
- Developed incrementally using TDD (Red -> Green -> Refactor)

## Run tests

```bash
mvn test