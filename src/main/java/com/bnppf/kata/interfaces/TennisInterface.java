package com.bnppf.kata.interfaces;

public interface TennisInterface {
    String getFirstPlayerName();
    String getSecondPlayerName();
    String getScore();
    void increaseAPointForFirstPlayer();
    int getFirstPlayerScore();
    int getSecondPlayerScore();
    void increaseAPointForSecondPlayer();
}