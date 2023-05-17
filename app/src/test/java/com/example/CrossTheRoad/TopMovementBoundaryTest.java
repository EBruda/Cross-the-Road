package com.example.CrossTheRoad;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class TopMovementBoundaryTest {

    @Test
    public void moveUpBoundaryTest() {
        Player player = new Player();

        Game game = new Game(1000, 1000, player);

        for (int i = 0; i < 50; i++) {
            int direction = (int) (Math.random() * 4);
            switch (direction) {
            case 0: player.moveLeft(game);
            case 1: player.moveUp(game);
            case 2: player.moveRight(game);
            default: player.moveDown(game);
            }
        }

        for (int i = 0; i < 20; i++) {
            player.moveUp(game);
        }

        assertTrue(player.getTop() >= 0);
    }
}
