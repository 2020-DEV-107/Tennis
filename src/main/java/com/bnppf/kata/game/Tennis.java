package com.bnppf.kata.game;

import com.bnppf.kata.constants.TennisConstants;
import com.bnppf.kata.exceptions.TennisException;
import com.bnppf.kata.interfaces.TennisInterface;

public class Tennis implements TennisInterface {
    private final String firstPlayerName;
    private final String secondPlayerName;
    private int firstPlayerScore;
    private int secondPlayerScore;

    public Tennis(String firstPlayerName , String secondPlayerName) {
        this.firstPlayerName = firstPlayerName;
        this.secondPlayerName = secondPlayerName;
    }

    @Override
    public String getFirstPlayerName() {
        return firstPlayerName;
    }

    @Override
    public String getSecondPlayerName() {
        return secondPlayerName;
    }

    @Override
    public void increasePlayerScore(String pointScoringPlayer) {
        if (null == pointScoringPlayer || "".equals(pointScoringPlayer) || !(pointScoringPlayer.equalsIgnoreCase(firstPlayerName) || pointScoringPlayer.equalsIgnoreCase(secondPlayerName))) {
            throw new TennisException(TennisConstants.TEXT_INVALID_PLAYER);
        }
        if (pointScoringPlayer.equalsIgnoreCase(firstPlayerName)) {
            firstPlayerScore++;
        } else {
            secondPlayerScore++;
        }
    }

    @Override
    public int getFirstPlayerScore() {
        return firstPlayerScore;
    }

    @Override
    public int getSecondPlayerScore() {
        return secondPlayerScore;
    }

    @Override
    public String getScore() {
        return TennisConstants.TEXT_LOVE + TennisConstants.TEXT_SPACE + TennisConstants.TEXT_ALL;
    }
}
