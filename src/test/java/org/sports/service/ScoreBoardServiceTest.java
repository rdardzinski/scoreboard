package org.sports.service;

import org.junit.jupiter.api.Test;
import org.sports.model.ScoreEntry;

import static org.junit.jupiter.api.Assertions.*;

public class ScoreBoardServiceTest {

    @Test
    public void testAddNewEntry() {
        // given
        ScoreBoardService service = new ScoreBoardService();

        // when
        service.addNewEntry("Team A", "Team B");

        // then
        assertEquals(1, service.getAllScores().size());
        assertEquals("Team A", service.getAllScores().get(0).getHomeScore().getTeam().getName());
        assertEquals(0, service.getAllScores().get(0).getHomeScore().getGoals());
        assertEquals("Team B", service.getAllScores().get(0).getAwayScore().getTeam().getName());
        assertEquals(0, service.getAllScores().get(0).getAwayScore().getGoals());
    }

    @Test
    public void testUpdateScore() {
        // given
        ScoreBoardService service = new ScoreBoardService();

        // when
        service.addNewEntry("Team A", "Team B");
        service.updateScore("Team A", "Team B", 3, 2);

        // then
        ScoreEntry entry = service.getAllScores().get(0);
        assertEquals(3, entry.getHomeScore().getGoals());
        assertEquals(2, entry.getAwayScore().getGoals());
    }

    @Test
    public void testFinishMatch() {
        // given
        ScoreBoardService service = new ScoreBoardService();
        // when
        service.addNewEntry("Team A", "Team B");
        service.finishMatch("Team A", "Team B");
        // then
        assertTrue(service.getAllScores().isEmpty());
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
    public void testUpdateNonExistentMatchThrowsException() {
        ScoreBoardService service = new ScoreBoardService();

        assertThrows(IllegalArgumentException.class, () -> service.updateScore("Team A", "Team B", 1, 1));
    }

    @Test
    public void testFinishNonExistentMatchThrowsException() {
        ScoreBoardService service = new ScoreBoardService();

        assertThrows(IllegalArgumentException.class, () -> service.finishMatch("Team A", "Team B"));
    }
}
