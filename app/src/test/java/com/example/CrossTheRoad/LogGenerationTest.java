package com.example.CrossTheRoad;


import static com.example.CrossTheRoad.ObjectType.LONGLOG;
import static com.example.CrossTheRoad.ObjectType.MEDIUMLOG;
import static com.example.CrossTheRoad.ObjectType.SHORTLOG;
import static com.example.CrossTheRoad.Obstacle.getSize;

import org.junit.Test;
import static org.junit.Assert.*;

public class LogGenerationTest {
    @Test
    public void shortLogGenerationTest() {
        Obstacle obstacle = new Obstacle(SHORTLOG, 400, 600, true);
        assertEquals(Obstacle.getSpeed(SHORTLOG), obstacle.getSpeed());
        assertEquals(400, obstacle.getLeft());
        assertEquals(600, obstacle.getRight());
        assertTrue(obstacle.isGoingLeft());
        assertTrue(obstacle.isWalkable());
        assertTrue(obstacle.isMovable());
        assertEquals(2, getSize(SHORTLOG));
    }
    @Test
    public void mediumLogGenerationTest() {
        Obstacle obstacle = new Obstacle(MEDIUMLOG, 400, 600, true);
        assertEquals(Obstacle.getSpeed(MEDIUMLOG), obstacle.getSpeed());
        assertEquals(400, obstacle.getLeft());
        assertEquals(600, obstacle.getRight());
        assertTrue(obstacle.isGoingLeft());
        assertTrue(obstacle.isWalkable());
        assertTrue(obstacle.isMovable());
        assertEquals(3, getSize(MEDIUMLOG));
    }
    @Test
    public void longLogGenerationTest() {
        Obstacle obstacle = new Obstacle(LONGLOG, 400, 600, true);
        assertEquals(Obstacle.getSpeed(LONGLOG), obstacle.getSpeed());
        assertEquals(400, obstacle.getLeft());
        assertEquals(600, obstacle.getRight());
        assertTrue(obstacle.isGoingLeft());
        assertTrue(obstacle.isWalkable());
        assertTrue(obstacle.isMovable());
        assertEquals(4, getSize(LONGLOG));
    }
}
