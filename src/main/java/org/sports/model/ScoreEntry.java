package org.sports.model;
public class ScoreEntry {
    private final Score homeScore;
    private final Score awayScore;

    public ScoreEntry(Score homeScore, Score awayScore) {
        if (homeScore.getTeam().equals(awayScore.getTeam())) {
            throw new IllegalArgumentException("Home and away teams cannot be the same.");
        }
        this.homeScore = homeScore;
        this.awayScore = awayScore;
    }

    public Score getHomeScore() {
        return homeScore;
    }

    public Score getAwayScore() {
        return awayScore;
    }

    @Override
    public String toString() {
        return homeScore + " - " + awayScore;
    }
}

