package org.sports.service;

import org.sports.model.Score;
import org.sports.model.ScoreEntry;
import org.sports.model.Team;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ScoreBoardService {
    private final List<ScoreEntry> scoreEntries;

    public ScoreBoardService() {
        this.scoreEntries = new ArrayList<>();
    }

    /**
     * Adds a new entry with an initial score of 0 - 0.
     * Avoids adding duplicate entries for the same teams.
     *
     * @param homeTeamName The name of the home team.
     * @param awayTeamName The name of the away team.
     * @throws IllegalArgumentException if teams are the same or entry already exists.
     */
    public void addNewEntry(String homeTeamName, String awayTeamName) {
        if (homeTeamName.equalsIgnoreCase(awayTeamName)) {
            throw new IllegalArgumentException("Home and away teams cannot be the same.");
        }

        Team homeTeam = new Team(homeTeamName);
        Team awayTeam = new Team(awayTeamName);

        boolean exists = checkIfExist(homeTeamName, awayTeamName);

        if (exists) {
            throw new IllegalArgumentException("Entry already exists for these teams.");
        }

        Score homeScore = new Score(homeTeam);
        Score awayScore = new Score(awayTeam);
        scoreEntries.add(new ScoreEntry(homeScore, awayScore));
    }

    private boolean checkIfExist(String homeTeamName, String awayTeamName) {
        return scoreEntries.stream().anyMatch(entry ->
                entry.getHomeScore().getTeam().getName().equalsIgnoreCase(homeTeamName) &&
                        entry.getAwayScore().getTeam().getName().equalsIgnoreCase(awayTeamName)
        );
    }

    /**
     * Updates the score for an existing match.
     *
     * @param homeTeamName The name of the home team.
     * @param awayTeamName The name of the away team.
     * @param homeGoals    The updated goals for the home team.
     * @param awayGoals    The updated goals for the away team.
     * @throws IllegalArgumentException if the entry does not exist.
     */
    public void updateScore(String homeTeamName, String awayTeamName, int homeGoals, int awayGoals) {
        ScoreEntry match = findMatch(homeTeamName, awayTeamName)
                .orElseThrow(() -> new IllegalArgumentException("Match entry does not exist."));

        // Update the score by replacing the existing entry
        scoreEntries.remove(match);
        Score updatedHomeScore = new Score(new Team(homeTeamName), homeGoals);
        Score updatedAwayScore = new Score(new Team(awayTeamName), awayGoals);
        scoreEntries.add(new ScoreEntry(updatedHomeScore, updatedAwayScore, match.getStartDate()));
    }

    private Optional<ScoreEntry> findMatch(String homeTeamName, String awayTeamName) {
        return scoreEntries.stream()
                .filter(entry -> entry.getHomeScore().getTeam().getName().equalsIgnoreCase(homeTeamName)
                        && entry.getAwayScore().getTeam().getName().equalsIgnoreCase(awayTeamName))
                .findFirst();
    }

    /**
     * Removes an entry from the scoreboard when the match is finished.
     *
     * @param homeTeamName The name of the home team.
     * @param awayTeamName The name of the away team.
     * @throws IllegalArgumentException if the entry does not exist.
     */
    public void finishMatch(String homeTeamName, String awayTeamName) {
        boolean removed = scoreEntries.removeIf(entry ->
                entry.getHomeScore().getTeam().getName().equalsIgnoreCase(homeTeamName) &&
                        entry.getAwayScore().getTeam().getName().equalsIgnoreCase(awayTeamName)
        );

        if (!removed) {
            throw new IllegalArgumentException("Match entry does not exist.");
        }
    }

    /**
     * Retrieves all current scores, sorted by total goals and start date.
     *
     * @return Sorted list of all score entries.
     */
    public List<ScoreEntry> getSortedScores() {
        return scoreEntries.stream()
                .sorted(Comparator.comparingInt(ScoreEntry::getTotalGoals)
                        .thenComparing(ScoreEntry::getStartDate).reversed())
                .collect(Collectors.toList());
    }
}
