package com.bnppf.kata.game;

import com.bnppf.kata.constants.TennisConstants;
import com.bnppf.kata.enums.TennisScoreEnum;
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
        if (isDeuce()) {
            score = TennisConstants.TEXT_DEUCE;
        } else if (Math.abs(secondPlayer.getPoints() - firstPlayer.getPoints()) == TennisConstants.POINT_ONE && (secondPlayer.getPoints() > TennisConstants.POINT_THREE || firstPlayer.getPoints() > TennisConstants.POINT_THREE)) {
            return TennisConstants.TEXT_ADVANTAGE + TennisConstants.TEXT_COLON + secondPlayer.getName();
        } else {
            score = formatScore();
        }
        return score;
    }

    private String formatScore() {
        String score;
        TennisScoreEnum firstPlayerScoreEnum = getTennisFormatScore(firstPlayer.getPoints());
        TennisScoreEnum secondPlayerScoreEnum = getTennisFormatScore(secondPlayer.getPoints());
        score = isBothPlayersScoredSame() ?
                firstPlayerScoreEnum.fetchScore() + TennisConstants.TEXT_SPACE + TennisConstants.TEXT_ALL :
                firstPlayerScoreEnum.fetchScore() + TennisConstants.TEXT_COLON + secondPlayerScoreEnum.fetchScore();
        return score;
    }

    private boolean isDeuce() {
        return isBothPlayersScoredSame() && isAnyPlayerBeyondThirty();
    }

    private boolean isAnyPlayerBeyondThirty() {
        return firstPlayer.getPoints() > TennisConstants.POINT_TWO || secondPlayer.getPoints() > TennisConstants.POINT_TWO;
    }

    private boolean isBothPlayersScoredSame() {
        return firstPlayer.getPoints() == secondPlayer.getPoints();
    }

    private TennisScoreEnum getTennisFormatScore(int points) {
        return TennisScoreEnum.getScoreFromPoints(points);
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
