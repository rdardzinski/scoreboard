package org.sports.mapper;

import org.sports.model.Score;
import org.sports.model.ScoreEntry;
import org.sports.model.Team;

public class ScoreEntryMapper {

    private ScoreEntryMapper() {}

    /**
     * Maps an input string to a ScoreEntry structure.
     *
     * @param input The match string in the format "HomeTeam HomeScore - AwayTeam AwayScore".
     * @return A ScoreEntry object representing the match.
     * @throws IllegalArgumentException if the input format is invalid or contains incorrect data.
     */
    public static ScoreEntry mapFromString(String input) {
        if (input == null || input.trim().isEmpty()) {
            throw new IllegalArgumentException("Input string cannot be null or empty.");
        }

        String regex = "^([a-zA-Z ]+)\\s(\\d+)\\s+-\\s([a-zA-Z ]+)\\s(\\d+)$";
        if (!input.matches(regex)) {
            throw new IllegalArgumentException("Invalid input format. Expected: 'HomeTeam HomeScore - AwayTeam AwayScore'.");
        }

        String[] parts = input.split("\\s-\\s");
        String[] homeComponents = parts[0].split("\\s(?=\\d+$)");
        String[] awayComponents = parts[1].split("\\s(?=\\d+$)");

        String homeTeamName = homeComponents[0].trim();
        int homeScore = Integer.parseInt(homeComponents[1].trim());
        String awayTeamName = awayComponents[0].trim();
        int awayScore = Integer.parseInt(awayComponents[1].trim());

        if (homeTeamName.equalsIgnoreCase(awayTeamName)) {
            throw new IllegalArgumentException("Home and away teams cannot be the same.");
        }

        Team homeTeam = new Team(homeTeamName);
        Team awayTeam = new Team(awayTeamName);
        Score homeTeamScore = new Score(homeTeam, homeScore);
        Score awayTeamScore = new Score(awayTeam, awayScore);

        return new ScoreEntry(homeTeamScore, awayTeamScore);
    }
}


