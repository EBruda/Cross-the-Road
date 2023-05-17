package com.example.CrossTheRoad;


import static com.example.CrossTheRoad.ObjectType.CARRIAGE;
import static com.example.CrossTheRoad.ObjectType.HORSE;
import static com.example.CrossTheRoad.ObjectType.WAGON;
import static com.example.CrossTheRoad.Obstacle.getSize;

import org.junit.Test;
import static org.junit.Assert.*;



public class HorseAndCarriageGenerationTest {
    @Test
    public void horseGenerationTest() {
        Obstacle obstacle = new Obstacle(HORSE, 400, 600, true);
        assertEquals(Obstacle.getSpeed(HORSE), obstacle.getSpeed());
        assertEquals(400, obstacle.getLeft());
        assertEquals(600, obstacle.getRight());
        assertTrue(obstacle.isGoingLeft());
        assertTrue(obstacle.isWalkable());
        assertTrue(obstacle.isMovable());
        assertEquals(1, getSize(HORSE));
    }

    @Test
    public void carriageGenerationTest() {
        Obstacle carriage = new Obstacle(CARRIAGE, -400, 0, false);
        assertEquals(Obstacle.getSpeed(CARRIAGE), carriage.getSpeed());
        assertEquals(-400, carriage.getLeft());
        assertEquals(0, carriage.getRight());
        assertFalse(carriage.isGoingLeft());
        assertTrue(carriage.isWalkable());
        assertTrue(carriage.isMovable());
        assertEquals(2, getSize(CARRIAGE));
    }
    @Test
    public void horseMovementTest() {
        Obstacle obstacle = new Obstacle(HORSE, 100, 200, true);
        int prevLeft = obstacle.getLeft();
        int afterLeft = prevLeft - obstacle.getSpeed();
        obstacle.move();
        assertEquals(afterLeft, obstacle.getLeft());

    }
    @Test
    public void carriageMovementTest() {
        Obstacle obstacle = new Obstacle(CARRIAGE, 100, 200, true);
        int prevLeft = obstacle.getLeft();
        int afterLeft = prevLeft - obstacle.getSpeed();
        obstacle.move();
        assertEquals(afterLeft, obstacle.getLeft());

    }
}
