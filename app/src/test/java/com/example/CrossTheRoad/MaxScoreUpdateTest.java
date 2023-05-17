package com.example.CrossTheRoad;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MaxScoreUpdateTest {

    @Test
    public void maxScoreUpdate() {
        Player player = new Player();
        player.updateScore();

        assertEquals(player.getMaxScore(), 0);

        player.die();


        assertTrue((player.getMaxScore() > 0));
    }

    @Test
    public void maxScoreUpdateTwoLives() {
        Player player = new Player();

        //first life
        player.updateScore();
        assertEquals(player.getMaxScore(), 0);
        player.die();
        assertTrue((player.getMaxScore() > 0));

        int firstMax = player.getMaxScore();

        player.updateScore();
        player.updateScore();
        assertEquals(player.getMaxScore(), firstMax);

        player.die();
        assertTrue(player.getMaxScore() > firstMax);

    }


}

