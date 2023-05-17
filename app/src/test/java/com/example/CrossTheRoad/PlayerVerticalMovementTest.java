package com.example.CrossTheRoad;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class PlayerVerticalMovementTest {

    @Test
    public void moveUpCoordinateTest() {
        Player player = new Player();
        Game game = new Game(1000, 1000, player);
        // This is to set the player's starting coords

        int prevTop = player.getTop();
        int prevBottom = player.getBottom();
        int prevLeft = player.getLeft();
        int prevRight = player.getRight();

        player.moveUp(game);

        int newTop = player.getTop();
        int newBottom = player.getBottom();
        int newLeft = player.getLeft();
        int newRight = player.getRight();

        assertNotEquals(prevTop, newTop);
        assertNotEquals(prevBottom, newBottom);

        assertEquals(prevRight, newRight);
        assertEquals(prevLeft, newLeft);

        assertEquals(prevTop - game.tileHeight, newTop);
        assertEquals(prevBottom - game.tileHeight, newBottom);

    }


    @Test
    public void moveDownCoordinateTest() {
        Player player = new Player();
        Game game = new Game(1000, 1000, player);
        // This is so that the player's starting coords are set

        int prevTop = player.getTop();
        int prevBottom = player.getBottom();
        int prevLeft = player.getLeft();
        int prevRight = player.getRight();

        player.moveDown(game);

        int newTop = player.getTop();
        int newBottom = player.getBottom();
        int newLeft = player.getLeft();
        int newRight = player.getRight();

        assertNotEquals(prevTop, newTop);
        assertNotEquals(prevBottom, newBottom);

        assertEquals(prevRight, newRight);
        assertEquals(prevLeft, newLeft);

        assertEquals(prevTop + game.tileHeight, newTop);
        assertEquals(prevBottom + game.tileHeight, newBottom);

    }
}
