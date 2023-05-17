package com.example.CrossTheRoad;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

public class PlayerHorizontalMovementTest {

    @Test
    public void moveLeftCoordinateTest() {
        Player player = new Player();
        int prevTop = player.getTop();
        int prevBottom = player.getBottom();
        int prevLeft = player.getLeft();
        int prevRight = player.getRight();

        player.moveLeft(30);

        int newTop = player.getTop();
        int newBottom = player.getBottom();
        int newLeft = player.getLeft();
        int newRight = player.getRight();

        assertEquals(prevTop, newTop);
        assertEquals(prevBottom, newBottom);

        assertNotEquals(prevRight, newRight);
        assertNotEquals(prevLeft, newLeft);

        assertEquals(prevLeft - 30, newLeft);
        assertEquals(prevRight - 30, newRight);

    }

    @Test
    public void moveRightCoordinateTest() {
        Player player = new Player();
        int prevTop = player.getTop();
        int prevBottom = player.getBottom();
        int prevLeft = player.getLeft();
        int prevRight = player.getRight();

        player.moveRight(30, 1000);

        int newTop = player.getTop();
        int newBottom = player.getBottom();
        int newLeft = player.getLeft();
        int newRight = player.getRight();

        assertEquals(prevTop, newTop);
        assertEquals(prevBottom, newBottom);

        assertNotEquals(prevRight, newRight);
        assertNotEquals(prevLeft, newLeft);

        assertEquals(prevLeft + 30, newLeft);
        assertEquals(prevRight + 30, newRight);

    }
}
