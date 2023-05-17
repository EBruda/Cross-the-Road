package com.example.CrossTheRoad;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class WaterAndUpMovementCollisionTest {
    @Test
    public void WaterCollisionTest() {
        Player player = new Player();
        Game game = new Game( 270, 60, player);

        Row[] rows = new Row[game.numRows];
        rows[0] = new Row(ObjectType.GOAL, null, game.numCols, game.tileWidth);
        rows[1] = new Row(ObjectType.GRASS, null, game.numCols, game.tileWidth);
        rows[2] = new Row(ObjectType.WATER, null, game.numCols, game.tileWidth);
        rows[3] = new Row(ObjectType.WATER, null, game.numCols, game.tileWidth);
        rows[4] = new Row(ObjectType.WATER, null, game.numCols, game.tileWidth);
        rows[5] = new Row(ObjectType.GRASS, null, game.numCols, game.tileWidth);
        rows[6] = new Row(ObjectType.ROAD, null, game.numCols, game.tileWidth);
        rows[7] = new Row(ObjectType.ROAD, null, game.numCols, game.tileWidth);
        rows[8] = new Row(ObjectType.GRASS, null, game.numCols, game.tileWidth);
        rows[9] = new Row(ObjectType.WATER, null, game.numCols, game.tileWidth);
        rows[10] = new Row(ObjectType.WATER, null, game.numCols, game.tileWidth);
        rows[11] = new Row(ObjectType.WATER, null, game.numCols, game.tileWidth);
        rows[12] = new Row(ObjectType.GRASS, null, game.numCols, game.tileWidth);
        rows[13] = new Row(ObjectType.ROAD, null, game.numCols, game.tileWidth);
        rows[14] = new Row(ObjectType.ROAD, null, game.numCols, game.tileWidth);
        rows[15] = new Row(ObjectType.WATER, null, game.numCols, game.tileWidth);
        rows[16] = new Row(ObjectType.WATER, null, game.numCols, game.tileWidth);
        rows[17] = new Row(ObjectType.GRASS, null, game.numCols, game.tileWidth);

        game.setBackground(rows);
        player.setAllRows(rows);

        assertTrue(player.onWaterTile());
    }

    @Test
    public void UpMovementCollisionTest() {
        Player player = new Player();
        Game game = new Game( 180, 60, player);
        int prevLeft = player.getLeft();
        int prevRight = player.getRight();

        Obstacle horse = new Obstacle(ObjectType.HORSE, prevLeft, prevRight, true);
        horse.setSpeed(0);

        player.moveUp(game.tileHeight);
        player.moveUp(game.tileHeight);
        player.moveUp(game.tileHeight);

        assertTrue(horse.checkPlayerCollision(player));
    }
}
