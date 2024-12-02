package org.sports.model;

import java.time.LocalDateTime;

public class ScoreEntry {
    private final Score homeScore;
    private final Score awayScore;
    private final LocalDateTime startDate;

    public ScoreEntry(Score homeScore, Score awayScore) {
        this.startDate = LocalDateTime.now();
        if (homeScore.getTeam().equals(awayScore.getTeam())) {
            throw new IllegalArgumentException("Home and away teams cannot be the same.");
        }
        this.homeScore = homeScore;
        this.awayScore = awayScore;
    }

    public ScoreEntry(Score updatedHomeScore, Score updatedAwayScore, LocalDateTime startDate) {

        this.startDate = startDate;
        this.homeScore = updatedHomeScore;
        this.awayScore = updatedAwayScore;
    }

    public Score getHomeScore() {
        return homeScore;
    }

    public Score getAwayScore() {
        return awayScore;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public int getTotalGoals() {
        return homeScore.getGoals() + awayScore.getGoals();
    }

    @Override
    public String toString() {
        return homeScore + " - " + awayScore;
    }

}

