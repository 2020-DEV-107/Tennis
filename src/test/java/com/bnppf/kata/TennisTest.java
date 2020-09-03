package com.bnppf.kata;

import org.junit.Assert;
import org.junit.Test;

public class TennisTest {
    @Test
    public void initializeNewTennisGame() {
        Tennis tennis = new Tennis();

        Assert.assertNotNull(tennis);
    }
}
