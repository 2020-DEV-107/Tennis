package com.bnppf.kata.interfaces;

public interface TennisInterface {
    String getFirstPlayerName();
    String getSecondPlayerName();
    String getScore();
    void increasePlayerScore(String pointScoringPlayer);
    int getFirstPlayerScore();
    int getSecondPlayerScore();
}