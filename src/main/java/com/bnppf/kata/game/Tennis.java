package com.bnppf.kata.game;

import com.bnppf.kata.constants.TennisConstants;
import com.bnppf.kata.exceptions.TennisException;
import com.bnppf.kata.interfaces.TennisInterface;
import com.bnppf.kata.models.TennisPlayer;
import org.apache.log4j.Logger;

public class Tennis implements TennisInterface {
    private final Logger logger = Logger.getLogger(Tennis.class);
    private final TennisPlayer firstPlayer;
    private final TennisPlayer secondPlayer;

    public Tennis(TennisPlayer firstPlayer , TennisPlayer secondPlayer) {
        this.firstPlayer = firstPlayer;
        this.secondPlayer = secondPlayer;
    }

    @Override
    public void increasePlayerScore(String pointScoringPlayer) {
        if (!isValidPlayerName(pointScoringPlayer)) {
            logger.error(TennisConstants.TEXT_INVALID_PLAYER);
            throw new TennisException(TennisConstants.TEXT_INVALID_PLAYER);
        }
        if (pointScoringPlayer.equalsIgnoreCase(firstPlayer.getName())) {
            firstPlayer.setPoints(firstPlayer.getPoints() + TennisConstants.POINT_ONE);
        } else {
            secondPlayer.setPoints(secondPlayer.getPoints() + TennisConstants.POINT_ONE);
        }
    }

    @Override
    public TennisPlayer getFirstPlayer() {
        return firstPlayer;
    }

    @Override
    public TennisPlayer getSecondPlayer() {
        return secondPlayer;
    }

    @Override
    public String getScore() {
        String score;
        String firstPlayerTennisScore = getTennisFormatScore(firstPlayer.getPoints());
        String secondPlayerTennisScore = getTennisFormatScore(secondPlayer.getPoints());
        if (firstPlayer.getPoints() == secondPlayer.getPoints()) {
            score = firstPlayerTennisScore + TennisConstants.TEXT_SPACE + TennisConstants.TEXT_ALL;
        } else {
            score = firstPlayerTennisScore + TennisConstants.TEXT_COLON + secondPlayerTennisScore;
        }
        return score;
    }

    private String getTennisFormatScore(int points) {
        String tennisFormatScore = "";
        if (points == TennisConstants.POINT_ZERO) {
            tennisFormatScore = TennisConstants.TEXT_LOVE;
        } else if (points == TennisConstants.POINT_ONE) {
            tennisFormatScore = TennisConstants.TEXT_FIFTEEN;
        }
        return tennisFormatScore;
    }

    private boolean isValidPlayerName(String playerName) {
        return !isPlayerNullOrEmpty(playerName) && isFirstOrSecondPlayer(playerName);
    }

    private boolean isPlayerNullOrEmpty(String playerName) {
        return null == playerName || "".equals(playerName);
    }

    private boolean isFirstOrSecondPlayer(String playerName) {
        return playerName.equalsIgnoreCase(getFirstPlayer().getName()) || playerName.equalsIgnoreCase(getSecondPlayer().getName());
    }
}
