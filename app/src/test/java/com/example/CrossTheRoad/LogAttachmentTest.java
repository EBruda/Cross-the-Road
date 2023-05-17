package com.example.CrossTheRoad;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class LogAttachmentTest {
    @Test
    public void testPlayerLogAttachment() {
        Player player = new Player();
        Game game = new Game(200, 200, player);

        // Put player on water tile

        player.moveUp(game);
        player.moveUp(game);
        player.moveUp(game);
        player.moveUp(game);

        game.getBackground()[player.getRow() - 1].getObstacles().add(
                new Obstacle(ObjectType.LONGLOG, player.getLeft() - 100,
                        player.getRight() + 100, false));

        player.moveUp(game);
        game.update();
        assertTrue(player.isOnLog());
    }

    @Test
    public void testLogDetachment() {
        Player player = new Player();
        Game game = new Game(200, 200, player);

        // Put player on water tile

        player.moveUp(game);
        player.moveUp(game);
        player.moveUp(game);
        player.moveUp(game);

        game.getBackground()[player.getRow() - 1].getObstacles().add(
                new Obstacle(ObjectType.LONGLOG, player.getLeft() - 100,
                        player.getRight() + 100, false));

        player.moveUp(game);
        // DON'T UPDATE GAME
        assertFalse(player.isOnLog());
    }


}
