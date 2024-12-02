package org.sports;

import org.sports.mapper.ScoreEntryMapper;
import org.sports.model.ScoreEntry;
import org.sports.service.ScoreBoardService;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        ScoreBoardService service = new ScoreBoardService();
        service.addNewEntry("Mexico", "Canada");
        service.addNewEntry("Spain", "Brazil");
        service.addNewEntry("Germany", "France");
        service.addNewEntry("Uruguay", "Italy");
        service.addNewEntry("Argentina", "Australia");

        try {
            updateScore(service, "Mexico 0 - Canada 5");
            updateScore(service, "Spain 10 - Brazil 2");
            updateScore(service, "Germany 2 - France 2");
            updateScore(service, "Uruguay 6 - Italy 6");
            updateScore(service, "Argentina 3 - Australia 1");
            List<ScoreEntry> sortedScores = service.getSortedScores();
            System.out.println("Current Matches:");
            sortedScores.forEach(System.out::println);

            service.finishMatch("Uruguay", "Italy");
            System.out.println("\nAfter finishing Uruguay vs Italy:");
            sortedScores = service.getSortedScores();
            sortedScores.forEach(System.out::println);

        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void updateScore(ScoreBoardService service, String update) {
        ScoreEntry scoreUpdate1 = ScoreEntryMapper.mapFromString(update);
        service.updateScore(
                scoreUpdate1.getHomeScore().getTeam().getName(),
                scoreUpdate1.getAwayScore().getTeam().getName(),
                scoreUpdate1.getHomeScore().getGoals(),
                scoreUpdate1.getAwayScore().getGoals()
        );
    }
}
