package com.bnppf.kata;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TennisTest {
    private Tennis tennis;

    @Before
    public void initialSetup() {
        tennis = new Tennis("Serena Williams" , "Maria Sharapova");
    }

    @Test
    public void initializeNewTennisGame() {
        Assert.assertNotNull(tennis);
    }

    @Test
    public void startNewGameWithTwoPlayers() {
        Assert.assertEquals("Serena Williams" , tennis.getFirstPlayerName());
        Assert.assertEquals("Maria Sharapova" , tennis.getSecondPlayerName());
    }

    @Test
    public void initialScoreShouldBeLoveAll() {
        Assert.assertEquals("Love All" , tennis.getScore());
    }
}
