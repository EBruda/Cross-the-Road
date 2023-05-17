package com.example.CrossTheRoad;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class ScoreOnCollisionTest {

    @Test
    public void ScoreToZero() {
        Player player = new Player();
        Game game = new Game( 180, 60, player);
        int prevLeft = player.getLeft();
        int prevRight = player.getRight();

        Obstacle horse = new Obstacle(ObjectType.HORSE, prevLeft, prevRight, true);
        horse.setSpeed(0);

        while (horse.checkPlayerCollision(player) != true) {
            player.moveUp(game.tileHeight);
        }

        assertEquals(0, player.getScore());
    }

    @Test
    public void ScoreIncreaseAfterCollision() {
        Player player = new Player();
        Game game = new Game( 180, 60, player);
        int prevLeft = player.getLeft();
        int prevRight = player.getRight();

        Obstacle horse = new Obstacle(ObjectType.HORSE, prevLeft, prevRight, true);
        horse.setSpeed(0);

        int rowsBeforeCollision = 0;

        while (horse.checkPlayerCollision(player) != true) {
            player.moveUp(game.tileHeight);
            rowsBeforeCollision++;
        }

        for (int i = 0; i<=rowsBeforeCollision; i++) {
            player.moveUp(game.tileHeight);
        }

        assertTrue(player.getScore() > 0);
    }


}
