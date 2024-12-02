package org.sports.model;

import java.time.LocalDateTime;

public class ScoreEntry {
    private final Score homeScore;
    private final Score awayScore;
    private final LocalDateTime startDate;

    public ScoreEntry(Score homeScore, Score awayScore) {
        this.startDate = LocalDateTime.now();
        this.homeScore = homeScore;
        this.awayScore = awayScore;
    }

    public ScoreEntry(Score homeScore, Score awayScore, LocalDateTime startDate) {

        if (homeScore.getTeam().equals(awayScore.getTeam())) {
            throw new IllegalArgumentException("Home and away teams cannot be the same.");
        }
        this.startDate = startDate;
        this.homeScore = homeScore;
        this.awayScore = awayScore;
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

