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
    private static final String DEUCE = "Deuce";
    private static final String ADVANTAGE = "Advantage";
    private static final String WINNER = "Winner";
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

    @Test
    @Parameters({
            "3, 3" ,
            "4, 4" ,
            "5, 5" ,
            "15, 15" ,
            "26, 26"
    })
    public void checkForDeuceSituationInGame(int firstPlayerPoints , int secondPlayerPoints) {
        prepareScore(firstPlayerPoints , secondPlayerPoints);

        Assert.assertEquals(DEUCE , tennis.getScore());
    }

    @Test
    @Parameters({
            "3, 4," + SECOND_PLAYER_NAME ,
            "4, 5," + SECOND_PLAYER_NAME ,
            "5, 6," + SECOND_PLAYER_NAME ,
            "6, 7," + SECOND_PLAYER_NAME ,
            "14, 15," + SECOND_PLAYER_NAME ,
            "27, 28," + SECOND_PLAYER_NAME ,
            "4, 3," + FIRST_PLAYER_NAME ,
            "5, 4," + FIRST_PLAYER_NAME ,
            "6, 5," + FIRST_PLAYER_NAME ,
            "7, 6," + FIRST_PLAYER_NAME ,
            "15, 14," + FIRST_PLAYER_NAME ,
            "22, 21," + FIRST_PLAYER_NAME
    })
    public void checkAdvantageSituationForHighScorer(int firstPlayerPoints , int secondPlayerPoints, String highScoringPlayer) {
        prepareScore(firstPlayerPoints , secondPlayerPoints);

        Assert.assertEquals(ADVANTAGE + COLON + highScoringPlayer , tennis.getScore());
    }

    @Test
    @Parameters({
            "4, 0," + FIRST_PLAYER_NAME ,
            "4, 2," + FIRST_PLAYER_NAME ,
            "5, 3," + FIRST_PLAYER_NAME ,
            "6, 4," + FIRST_PLAYER_NAME ,
            "7, 5," + FIRST_PLAYER_NAME ,
            "8, 6," + FIRST_PLAYER_NAME ,
            "15, 13," + FIRST_PLAYER_NAME ,
            "22, 20," + FIRST_PLAYER_NAME ,
            "0, 4," + SECOND_PLAYER_NAME ,
            "1, 4," + SECOND_PLAYER_NAME ,
            "2, 4," + SECOND_PLAYER_NAME ,
            "3, 5," + SECOND_PLAYER_NAME ,
            "4, 6," + SECOND_PLAYER_NAME ,
            "5, 7," + SECOND_PLAYER_NAME ,
            "13, 15," + SECOND_PLAYER_NAME ,
            "20, 22," + SECOND_PLAYER_NAME
    })
    public void shouldReturnWinningPlayer(int firstPlayerPoints , int secondPlayerPoints , String gameWinningPlayer) {
        prepareScore(firstPlayerPoints , secondPlayerPoints);

        Assert.assertEquals(WINNER + COLON + gameWinningPlayer , tennis.getScore());
    }

    private void prepareScore(int firstPlayerPoints , int secondPlayerPoints) {
        IntStream.range(0 , firstPlayerPoints).forEach(counter -> tennis.increasePlayerScore(FIRST_PLAYER_NAME));
        IntStream.range(0 , secondPlayerPoints).forEach(counter -> tennis.increasePlayerScore(SECOND_PLAYER_NAME));
    }
}
