package com.bnppf.kata;

import org.junit.Assert;
import org.junit.Test;

public class TennisTest {
    @Test
    public void initializeNewTennisGame() {
        Tennis tennis = new Tennis("Serena Williams" , "Maria Sharapova");

        Assert.assertNotNull(tennis);
    }

    @Test
    public void startNewGameWithTwoPlayers() {
        Tennis tennis = new Tennis("Serena Williams" , "Maria Sharapova");

        Assert.assertEquals("Serena Williams" , tennis.getFirstPlayerName());
        Assert.assertEquals("Maria Sharapova" , tennis.getSecondPlayerName());
    }
}
