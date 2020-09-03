package com.bnppf.kata.enums;

import com.bnppf.kata.constants.TennisConstants;
import com.bnppf.kata.exceptions.TennisException;
import org.apache.log4j.Logger;

import java.util.Arrays;

public enum TennisScoreEnum {
    LOVE("Love" , 0), FIFTEEN("Fifteen" , 1), THIRTY("Thirty" , 2), FORTY("Forty" , 3);
    private static final Logger logger = Logger.getLogger(TennisScoreEnum.class);
    private final String score;
    private final int point;

    TennisScoreEnum(String score , int point) {
        this.score = score;
        this.point = point;
    }

    public static TennisScoreEnum getScoreFromPoints(int point) {
        if (point < TennisConstants.POINT_ZERO || point > TennisConstants.POINT_THREE) {
            logger.error(TennisConstants.TEXT_INVALID_POINT);
            throw new TennisException(TennisConstants.TEXT_INVALID_POINT);
        }
        return Arrays.stream(TennisScoreEnum.values()).
                filter(tennisScore -> tennisScore.point == point).findFirst().orElse(null);
    }

    public String fetchScore() {
        return score;
    }
}