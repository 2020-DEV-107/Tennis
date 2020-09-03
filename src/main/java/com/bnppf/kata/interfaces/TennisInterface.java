package com.bnppf.kata.interfaces;

import com.bnppf.kata.models.TennisPlayer;

public interface TennisInterface {
    TennisPlayer getFirstPlayer();
    TennisPlayer getSecondPlayer();
    String getScore();
    void increasePlayerScore(String pointScoringPlayer);
}