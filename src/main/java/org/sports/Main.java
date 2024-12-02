package org.sports;

import org.sports.service.ScoreBoardService;

public class Main {
    public static void main(String[] args) {
        ScoreBoardService service = new ScoreBoardService();
        service.addNewEntry("Mexico", "Canada");
        service.addNewEntry("Spain", "Brazil");
        service.addNewEntry("Germany", "France");
        service.addNewEntry("Uruguay", "Italy");
        service.addNewEntry("Argentina", "Australia");

        // Update scores
        service.updateScore("Mexico", "Canada", 0, 5);
        service.updateScore("Spain", "Brazil", 10, 2);
        service.updateScore("Germany", "France", 2, 2);
        service.updateScore("Uruguay", "Italy", 6, 6);
        service.updateScore("Argentina", "Australia", 3, 1);

        // Display sorted scores
        service.getSortedScores().forEach(System.out::println);

        // Finish a match
        service.finishMatch("Spain", "Brazil");

        System.out.println("Spain vs Brazil is finished.");
        // Display sorted scores after finishing a match
        service.getSortedScores().forEach(System.out::println);
    }
}
