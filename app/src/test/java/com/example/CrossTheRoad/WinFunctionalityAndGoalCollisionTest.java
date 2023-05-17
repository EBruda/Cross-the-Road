package com.example.CrossTheRoad;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class WinFunctionalityAndGoalCollisionTest {
    @Test
    public void WinFunctionalityTest() {
        Player player = new Player();
        Game game = new Game( 450, 450, player);
        // Force player to collide with Goal Obstacle
        player.setRow(1);
        // Set a high score
        player.setScore(1000);
        // Set a low score
        player.setScore(0);
        // Check for forced collision
        game.checkDeathStatusObstacles();
        assertTrue(player.getWonGame());
        // Verify that scoring regarding winning is functional
        assertEquals(player.getScore(), player.getMaxScore());
    }

    @Test
    public void GoalCollisionTest() {
        Player player = new Player();
        Game game = new Game( 450, 450, player);
        // Force player to collide with Goal Obstacle
        player.setRow(1);
        // Check all conditions to ensure collision
        assertFalse(game.checkDeathStatusObstacles());
        assertTrue(player.getWonGame());
    }
}
