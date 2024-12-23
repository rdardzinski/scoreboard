package org.sports.service;

import org.junit.jupiter.api.Test;
import org.sports.model.ScoreEntry;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ScoreBoardServiceTest {

    @Test
    public void testAddNewEntry() {
        // given
        ScoreBoardService service = new ScoreBoardService();

        // when
        service.addNewEntry("Team A", "Team B");

        // then
        assertEquals(1, service.getSortedScores().size());
        assertEquals("Team A", service.getSortedScores().get(0).getHomeScore().getTeam().getName());
        assertEquals(0, service.getSortedScores().get(0).getHomeScore().getGoals());
        assertEquals("Team B", service.getSortedScores().get(0).getAwayScore().getTeam().getName());
        assertEquals(0, service.getSortedScores().get(0).getAwayScore().getGoals());
    }

    @Test
    public void testUpdateScore() {
        // given
        ScoreBoardService service = new ScoreBoardService();

        // when
        service.addNewEntry("Team A", "Team B");
        service.updateScore("Team A", "Team B", 3, 2);

        // then
        ScoreEntry entry = service.getSortedScores().get(0);
        assertEquals(3, entry.getHomeScore().getGoals());
        assertEquals(2, entry.getAwayScore().getGoals());
    }

    @Test
    public void testUpdateScoreNegativeGoalsThrowsException() {
        // given
        ScoreBoardService service = new ScoreBoardService();

        // when
        service.addNewEntry("Team A", "Team B");

        // then
        assertThrows(IllegalArgumentException.class, () -> service.updateScore("Team A", "Team B", -3, 2));
    }

    @Test
    public void testFinishMatch() {
        // given
        ScoreBoardService service = new ScoreBoardService();
        // when
        service.addNewEntry("Team A", "Team B");
        service.finishMatch("Team A", "Team B");
        // then
        assertTrue(service.getSortedScores().isEmpty());
    }

    @Test
    public void testAddDuplicateEntryThrowsException() {
        // given
        ScoreBoardService service = new ScoreBoardService();
        // when
        service.addNewEntry("Team A", "Team B");
        // then
        assertThrows(IllegalArgumentException.class, () -> service.addNewEntry("Team A", "Team B"));
    }

    @Test
    public void testAddSameTeamsInEntryThrowsException() {
        // given
        ScoreBoardService service = new ScoreBoardService();
        // when
        // then
        assertThrows(IllegalArgumentException.class, () -> service.addNewEntry("Team A", "Team A"));
    }

    @Test
    public void testAddTeamsVerifyToSting() {
        // given
        ScoreBoardService service = new ScoreBoardService();
        // when
        service.addNewEntry("Team A", "Team B");
        // then
        assertEquals("Team A 0 - Team B 0", service.getSortedScores().get(0).toString());
    }

    @Test
    public void testUpdateNonExistentMatchThrowsException() {
        ScoreBoardService service = new ScoreBoardService();

        assertThrows(IllegalArgumentException.class, () -> service.updateScore("Team A", "Team B", 1, 1));
    }

    @Test
    public void testFinishNonExistentMatchThrowsException() {
        ScoreBoardService service = new ScoreBoardService();

        assertThrows(IllegalArgumentException.class, () -> service.finishMatch("Team A", "Team B"));
    }

    @Test
    public void testFinishNonExistentMatchForAwayThrowsException() {
        // given
        ScoreBoardService service = new ScoreBoardService();
        // when
        service.addNewEntry("Team A", "Team C");
        // then
        assertThrows(IllegalArgumentException.class, () -> service.finishMatch("Team A", "Team B"));
    }
    @Test
    public void testFinishNonExistentMatchForHomeThrowsException() {
        // given
        ScoreBoardService service = new ScoreBoardService();
        // when
        service.addNewEntry("Team A", "Team C");
        // then
        assertThrows(IllegalArgumentException.class, () -> service.finishMatch("Team B", "Team C"));
    }
    @Test
    public void testUpdateNonExistentMatchForHomeThrowsException() {
        // given
        ScoreBoardService service = new ScoreBoardService();
        // when
        service.addNewEntry("Team A", "Team C");
        // then
        assertThrows(IllegalArgumentException.class, () -> service.updateScore("Team B", "Team C", 1, 1));
    }
    @Test
    public void testUpdateNonExistentMatchForAwayThrowsException() {
        // given
        ScoreBoardService service = new ScoreBoardService();
        // when
        service.addNewEntry("Team A", "Team C");
        // then
        assertThrows(IllegalArgumentException.class, () -> service.updateScore("Team A", "Team B", 1, 1));
    }
    @Test
    public void testAddAlreadyPlayingAwayTeamThrowsException() {
        // given
        ScoreBoardService service = new ScoreBoardService();
        // when
        service.addNewEntry("Team A", "TeamC");
        // then
        assertThrows(IllegalArgumentException.class, () -> service.addNewEntry("Team B", "TeamC"));
    }

    @Test
    public void testSortingByGoalsAndStartDate() {
        ScoreBoardService service = new ScoreBoardService();

        // Add matches
        service.addNewEntry("Team A", "Team B");
        service.addNewEntry("Team C", "Team D");
        service.addNewEntry("Team E", "Team F");

        // Update scores
        service.updateScore("Team A", "Team B", 2, 1); // Total goals = 3
        service.updateScore("Team C", "Team D", 1, 1); // Total goals = 2
        service.updateScore("Team E", "Team F", 3, 2); // Total goals = 5

        // Retrieve and verify sorted results
        List<ScoreEntry> sortedScores = service.getSortedScores();
        assertEquals(3, sortedScores.size());

        // Verify order by total goals
        assertEquals("Team E", sortedScores.get(0).getHomeScore().getTeam().getName());
        assertEquals("Team F", sortedScores.get(0).getAwayScore().getTeam().getName());
        assertEquals("Team A", sortedScores.get(1).getHomeScore().getTeam().getName());
        assertEquals("Team B", sortedScores.get(1).getAwayScore().getTeam().getName());
        assertEquals("Team C", sortedScores.get(2).getHomeScore().getTeam().getName());
        assertEquals("Team D", sortedScores.get(2).getAwayScore().getTeam().getName());
    }

    @Test
    public void testSortingWithSameGoalsByStartDate() {
        ScoreBoardService service = new ScoreBoardService();

        // Add matches
        service.addNewEntry("Team A", "Team B", LocalDateTime.now());
        service.addNewEntry("Team C", "Team D", LocalDateTime.now().plusSeconds(1));
        // Update scores
        service.updateScore("Team C", "Team D", 2, 2);
        service.updateScore("Team A", "Team B", 2, 2);

        // Retrieve and verify sorted results
        List<ScoreEntry> sortedScores = service.getSortedScores();
        assertEquals(2, sortedScores.size());

        // Verify order by start date for matches with same total goals
        assertEquals("Team C", sortedScores.get(0).getHomeScore().getTeam().getName());
        assertEquals("Team D", sortedScores.get(0).getAwayScore().getTeam().getName());
        assertEquals("Team A", sortedScores.get(1).getHomeScore().getTeam().getName());
        assertEquals("Team B", sortedScores.get(1).getAwayScore().getTeam().getName());
    }

    @Test
    public void testAddNewEntryNullTeamNameThrowsException() {
        // given
        ScoreBoardService service = new ScoreBoardService();
        // when

        // then
        assertThrows(IllegalArgumentException.class, () -> service.addNewEntry(null, "Team C"));
    }

    @Test
    public void testAddNewEntryEmptyStringTeamNameThrowsException() {
        // given
        ScoreBoardService service = new ScoreBoardService();
        // when

        // then
        assertThrows(IllegalArgumentException.class, () -> service.addNewEntry("", "Team C"));
    }

}
