package com.example.CrossTheRoad;

import org.junit.Test;
import static org.junit.Assert.*;

public class FullCollisionTest {

    @Test
    public void fullCollision() {
        Player player = new Player();
        int left = player.getLeft();
        int right = player.getRight();

        Obstacle log = new Obstacle(ObjectType.LONGLOG, left - 20, right + 20, true);

        assertTrue(log.checkPlayerFullOverlap(player));
    }

    @Test
    public void onlyCollisionOnLeft() {
        Player player = new Player();
        int left = player.getLeft();
        int right = player.getRight();

        Obstacle log = new Obstacle(ObjectType.LONGLOG, left + 20, right + 20, true);

        assertFalse(log.checkPlayerFullOverlap(player));
    }

    @Test
    public void onlyCollisionOnRight() {
        Player player = new Player();
        int left = player.getLeft();
        int right = player.getRight();

        Obstacle log = new Obstacle(ObjectType.LONGLOG, left - 20, right - 20, true);

        assertFalse(log.checkPlayerFullOverlap(player));
    }

}
