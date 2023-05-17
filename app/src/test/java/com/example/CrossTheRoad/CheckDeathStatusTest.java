package com.example.CrossTheRoad;

import org.junit.Test;
import static org.junit.Assert.*;

public class CheckDeathStatusTest {

    @Test
    public void checkDeathStatusWaterTest() {
        Player player = new Player();
        Game game = new Game(200, 200, player);

        game.setDefaultBackground();

        player.moveUp(game);
        player.moveUp(game);
        player.moveUp(game);
        player.moveUp(game);

        assertFalse(game.checkDeathStatusWater());

        player.moveUp(game);
        assertTrue(game.checkDeathStatusWater());


    }


    @Test
    public void checkDeathStatusObstaclesTest() {
        Player player = new Player();
        Game game = new Game(200, 200, player);

        game.setDefaultBackground();

        player.moveUp(game);
        assertFalse(game.checkDeathStatusObstacles());
        player.moveUp(game);
        assertFalse(game.checkDeathStatusObstacles());
        player.moveUp(game);
        assertFalse(game.checkDeathStatusObstacles());
    }



}
