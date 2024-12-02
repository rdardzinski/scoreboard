package org.sports;

import org.sports.mapper.ScoreEntryMapper;
import org.sports.model.ScoreEntry;
import org.sports.service.ScoreBoardService;

import java.time.LocalDateTime;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        ScoreBoardService service = new ScoreBoardService();
        service.addNewEntry("Mexico", "Canada");
        service.addNewEntry("Spain", "Brazil", LocalDateTime.now().plusSeconds(1));
        service.addNewEntry("Germany", "France", LocalDateTime.now().plusSeconds(2));
        service.addNewEntry("Uruguay", "Italy", LocalDateTime.now().plusSeconds(3));
        service.addNewEntry("Argentina", "Australia", LocalDateTime.now().plusSeconds(4));

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
