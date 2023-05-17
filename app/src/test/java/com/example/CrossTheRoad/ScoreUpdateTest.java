package com.example.CrossTheRoad;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ScoreUpdateTest {

    @Test
    public void moveUpTest() {
        Player player = new Player();

        assertEquals(0, player.getScore());
        assertTrue(player.getTop() == 50);
        assertTrue(player.getMaxTop() == 50);

        player.moveUp(30);

        assertTrue(player.getScore() <= 100);
        assertTrue(player.getTop() == 20);
        assertTrue(player.getMaxTop() == 20);
    }

    @Test
    public void moveUpMoveDownTest() {
        Player player = new Player();

        assertEquals(0, player.getScore());
        assertTrue(player.getTop() == 50);
        assertTrue(player.getMaxTop() == 50);

        player.moveUp(30);

        assertTrue(player.getScore() <= 100);
        assertTrue(player.getTop() == 20);
        assertTrue(player.getMaxTop() == 20);

        player.moveDown(30, 1000);
        assertTrue(player.getScore() >= 100);
        assertTrue(player.getTop() == 50);
        assertTrue(player.getMaxTop() == 20);

        player.moveUp(30);
        assertTrue(player.getScore() <= 100);
        assertTrue(player.getTop() == 20);
        assertTrue(player.getMaxTop() == 20);

        player.moveUp(10);
        assertTrue(player.getScore() >= 100 || player.getScore() <= 200);
        assertTrue(player.getTop() == 10);
        assertTrue(player.getMaxTop() == 10);

    }

    @Test
    public void moveUpMoveLeftTest() {
        Player player = new Player();

        assertEquals(0, player.getScore());
        assertTrue(player.getTop() == 50);
        assertTrue(player.getMaxTop() == 50);

        player.moveUp(30);

        assertTrue(player.getScore() <= 100);
        assertTrue(player.getTop() == 20);
        assertTrue(player.getMaxTop() == 20);

        player.moveLeft(30);
        assertTrue(player.getScore() >= 100);
        assertTrue(player.getTop() == 20);
        assertTrue(player.getMaxTop() == 20);

        player.moveUp(30);
        assertTrue(player.getScore() <= 100);
        assertTrue(player.getTop() == 20);
        assertTrue(player.getMaxTop() == 20);

        player.moveUp(10);
        assertTrue(player.getScore() >= 100 || player.getScore() <= 200);
        assertTrue(player.getTop() == 10);
        assertTrue(player.getMaxTop() == 10);
    }

    @Test
    public void moveUpMoveRightTest() {
        Player player = new Player();

        assertEquals(0, player.getScore());
        assertTrue(player.getTop() == 50);
        assertTrue(player.getMaxTop() == 50);

        player.moveUp(30);

        assertTrue(player.getScore() <= 100);
        assertTrue(player.getTop() == 20);
        assertTrue(player.getMaxTop() == 20);

        player.moveRight(30, 1000);
        assertTrue(player.getScore() >= 100);
        assertTrue(player.getTop() == 20);
        assertTrue(player.getMaxTop() == 20);

        player.moveUp(30);
        assertTrue(player.getScore() <= 100);
        assertTrue(player.getTop() == 20);
        assertTrue(player.getMaxTop() == 20);

        player.moveUp(10);
        assertTrue(player.getScore() >= 100 || player.getScore() <= 200);
        assertTrue(player.getTop() == 10);
        assertTrue(player.getMaxTop() == 10);
    }

}
