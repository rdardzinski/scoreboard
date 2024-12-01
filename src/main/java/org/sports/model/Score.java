package org.sports.model;

public class Score {
    private final Team team;
    private final int goals;

    public Score(Team team, int goals) {
        if (goals < 0) {
            throw new IllegalArgumentException("Goals cannot be negative.");
        }
        this.team = team;
        this.goals = goals;
    }

    public Team getTeam() {
        return team;
    }

    public int getGoals() {
        return goals;
    }

    @Override
    public String toString() {
        return team + " " + goals;
    }
}

