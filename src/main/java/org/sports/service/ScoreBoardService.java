package org.sports.service;

import org.sports.model.ScoreEntry;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;
import java.util.List;

public class ScoreBoardService {
    private final List<ScoreEntry> scoreEntries;

    public ScoreBoardService() {
        this.scoreEntries = new ArrayList<>();
    }


    public void addNewEntry(String teamA, String teamB) {
        throw new NotImplementedException();
    }

    public void finishMatch(String teamA, String teamB) {
        throw new NotImplementedException();
    }

    public List<ScoreEntry> getAllScores() {
        return new ArrayList<>(scoreEntries);
    }

    public void updateScore(String teamA, String teamB, int goalsA, int goalsB) {
        throw new NotImplementedException();
    }

}
