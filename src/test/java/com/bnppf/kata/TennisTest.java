package com.bnppf.kata;

import com.bnppf.kata.game.Tennis;
import com.bnppf.kata.interfaces.TennisInterface;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TennisTest {
    private static final String LOVE = "Love";
    private static final String SPACE = " ";
    private static final String ALL = "All";
    private static final String FIRST_PLAYER_NAME = "Serena Williams";
    private static final String SECOND_PLAYER_NAME = "Maria Sharapova";
    private TennisInterface tennis;

    @Before
    public void initialSetup() {
        tennis = new Tennis(FIRST_PLAYER_NAME , SECOND_PLAYER_NAME);
    }

    @Test
    public void initializeNewTennisGame() {
        Assert.assertNotNull(tennis);
    }

    @Test
    public void startNewGameWithTwoPlayers() {
        Assert.assertEquals(FIRST_PLAYER_NAME , tennis.getFirstPlayerName());
        Assert.assertEquals(SECOND_PLAYER_NAME , tennis.getSecondPlayerName());
    }

    @Test
    public void initialScoreShouldBeLoveAll() {
        Assert.assertEquals(LOVE + SPACE + ALL , tennis.getScore());
    }
}
