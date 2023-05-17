package com.example.CrossTheRoad;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class CollisionsLeftRightTest {
    @Test
    public void LeftEdgeCollisionTest() {
        Player player = new Player();
        int left = player.getLeft();
        int right = player.getRight();

        Obstacle horse = new Obstacle(ObjectType.HORSE, left - 5, right - 5, true);

        assertTrue(horse.checkPlayerCollision(player));
    }

    @Test
    public void RightEdgeCollisionTest() {
        Player player = new Player();
        int left = player.getLeft();
        int right = player.getRight();

        Obstacle horse = new Obstacle(ObjectType.HORSE, left + 5, right + 5, true);

        assertTrue(horse.checkPlayerCollision(player));
    }
}
