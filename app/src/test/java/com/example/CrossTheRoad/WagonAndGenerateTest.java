package com.example.CrossTheRoad;


import static com.example.CrossTheRoad.ObjectType.CARRIAGE;
import static com.example.CrossTheRoad.ObjectType.HORSE;
import static com.example.CrossTheRoad.ObjectType.WAGON;
import static com.example.CrossTheRoad.Obstacle.getSize;

import org.junit.Test;
import static org.junit.Assert.*;



public class WagonAndGenerateTest {

    @Test
    public void wagonGenerationTest() {
        Obstacle obstacle = new Obstacle(WAGON, 400, 1000, true);
        assertEquals(Obstacle.getSpeed(WAGON), obstacle.getSpeed());
        assertEquals(400, obstacle.getLeft());
        assertEquals(1000, obstacle.getRight());
        assertTrue(obstacle.isGoingLeft());
        assertTrue(obstacle.isWalkable());
        assertTrue(obstacle.isMovable());
        assertEquals(3, getSize(WAGON));
    }

    @Test
    public void wagonMovementTest() {
        Obstacle obstacle = new Obstacle(WAGON, 100, 200, true);
        int prevLeft = obstacle.getLeft();
        int afterLeft = prevLeft - obstacle.getSpeed();
        obstacle.move();
        assertEquals(afterLeft, obstacle.getLeft());
    }
}
