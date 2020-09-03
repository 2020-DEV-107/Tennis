package com.bnppf.kata;

import com.bnppf.kata.exceptions.TennisException;
import com.bnppf.kata.game.Tennis;
import com.bnppf.kata.interfaces.TennisInterface;
import com.bnppf.kata.models.TennisPlayer;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;

import java.util.stream.IntStream;

@RunWith(JUnitParamsRunner.class)
public class TennisTest {
    private static final String LOVE = "Love";
    private static final String FIFTEEN = "Fifteen";
    private static final String COLON = ":";
    private static final String SPACE = " ";
    private static final String ALL = "All";
    private static final String FIRST_PLAYER_NAME = "Serena Williams";
    private static final String SECOND_PLAYER_NAME = "Maria Sharapova";
    private static final String INVALID_PLAYER_NAME = "Invalid Player Name";
    private static final String RANDOM_PLAYER = "Random Player";
    private static final int ONE_POINT = 1;
    private TennisInterface tennis;

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    @Before
    public void initialSetup() {
        tennis = new Tennis(new TennisPlayer(FIRST_PLAYER_NAME) , new TennisPlayer(SECOND_PLAYER_NAME));
    }

    @Test
    public void initializeNewTennisGame() {
        Assert.assertNotNull(tennis);
    }

    @Test
    public void startNewGameWithTwoPlayers() {
        Assert.assertEquals(FIRST_PLAYER_NAME , tennis.getFirstPlayer().getName());
        Assert.assertEquals(SECOND_PLAYER_NAME , tennis.getSecondPlayer().getName());
    }

    @Test
    public void initialScoreShouldBeLoveAll() {
        Assert.assertEquals(LOVE + SPACE + ALL , tennis.getScore());
    }

    @Test
    public void firstPlayerScoreShouldIncreaseAfterWinningAPoint() {
        tennis.increasePlayerScore(FIRST_PLAYER_NAME);

        Assert.assertEquals(ONE_POINT , tennis.getFirstPlayer().getPoints());
    }

    @Test
    public void secondPlayerScoreShouldIncreaseAfterWinningAPoint() {
        tennis.increasePlayerScore(SECOND_PLAYER_NAME);

        Assert.assertEquals(ONE_POINT , tennis.getSecondPlayer().getPoints());
    }

    @Test
    public void shouldNotAllowInvalidPlayerName() {
        exceptionRule.expect(TennisException.class);
        exceptionRule.expectMessage(INVALID_PLAYER_NAME);

        tennis.increasePlayerScore(RANDOM_PLAYER);
    }

    @Test
    public void scoreShouldBeLoveFifteenIfSecondPlayerScoresFirstPoint() {
        tennis.increasePlayerScore(SECOND_PLAYER_NAME);

        Assert.assertEquals(LOVE + COLON + FIFTEEN , tennis.getScore());
    }

    @Test
    public void scoreShouldBeFifteenAllIfBothPlayerScoresFirstPoint() {
        prepareScore(ONE_POINT , ONE_POINT);

        Assert.assertEquals(FIFTEEN + SPACE + ALL , tennis.getScore());
    }

    @Test
    @Parameters({
            "0, 0, Love All" ,
            "0, 1, Love:Fifteen" ,
            "0, 2, Love:Thirty" ,
            "0, 3, Love:Forty" ,
            "1, 0, Fifteen:Love" ,
            "1, 1, Fifteen All" ,
            "1, 2, Fifteen:Thirty" ,
            "1, 3, Fifteen:Forty" ,
            "2, 0, Thirty:Love" ,
            "2, 1, Thirty:Fifteen" ,
            "2, 2, Thirty All" ,
            "2, 3, Thirty:Forty" ,
            "3, 0, Forty:Love" ,
            "3, 1, Forty:Fifteen" ,
            "3, 2, Forty:Thirty"
    })
    public void scoreShouldBeAsPerParameters(int firstPlayerPoints , int secondPlayerPoints , String score) {
        prepareScore(firstPlayerPoints , secondPlayerPoints);

        Assert.assertEquals(score , tennis.getScore());
    }

    private void prepareScore(int firstPlayerPoints , int secondPlayerPoints) {
        IntStream.range(0 , firstPlayerPoints).forEach(counter -> tennis.increasePlayerScore(FIRST_PLAYER_NAME));
        IntStream.range(0 , secondPlayerPoints).forEach(counter -> tennis.increasePlayerScore(SECOND_PLAYER_NAME));
    }
}
