package com.example.CrossTheRoad;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class LeftAndRightBoundaryTest {
    @Test
    public void moveLeftBoundaryTest() {
        Player player = new Player();
        Game game = new Game(1000, 1000, player);
        // Creating a game with the given Player also instantiates the player with the
        // default background, which is necessary when calling player.move() methods
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
            player.moveLeft(game);
        }

        assertTrue(player.getLeft() >= 0);
    }

    @Test
    public void moveRightBoundaryTest() {
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
            player.moveRight(game);
        }

        assertTrue(player.getRight() <= 1000);
    }
}
