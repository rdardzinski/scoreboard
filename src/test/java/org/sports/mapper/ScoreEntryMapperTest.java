package org.sports.mapper;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.sports.model.ScoreEntry;

import static org.junit.jupiter.api.Assertions.*;

public class ScoreEntryMapperTest {

    @Test
    public void testValidMapping() {
        String input = "Mexico 0 - Canada 1";
        ScoreEntry entry = ScoreEntryMapper.mapFromString(input);

        assertEquals("Mexico", entry.getHomeScore().getTeam().getName());
        assertEquals(0, entry.getHomeScore().getGoals());
        assertEquals("Canada", entry.getAwayScore().getTeam().getName());
        assertEquals(1, entry.getAwayScore().getGoals());
    }

    @Test
    public void testInvalidInputFormat() {
        assertThrows(IllegalArgumentException.class, () -> ScoreEntryMapper.mapFromString("Mexico - Canada 1"));
        assertThrows(IllegalArgumentException.class, () -> ScoreEntryMapper.mapFromString("Mexico 0 Canada 1"));
        assertThrows(IllegalArgumentException.class, () -> ScoreEntryMapper.mapFromString("Mexico 0-Canada 1"));
    }

    @Test
    public void testNullAndEmptyInput() {
        assertThrows(IllegalArgumentException.class, () -> ScoreEntryMapper.mapFromString(null));
        assertThrows(IllegalArgumentException.class, () -> ScoreEntryMapper.mapFromString(""));
    }

    @Test
    public void testSameTeamThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> ScoreEntryMapper.mapFromString("Mexico 1 - Mexico 1"));
    }
}