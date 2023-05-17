package com.example.CrossTheRoad;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import android.content.BroadcastReceiver;

import org.junit.Test;

public class RowConstructionTest {

    @Test
    public void rowConstruction() {
        Row row = new Row(ObjectType.ROAD, ObjectType.CARRIAGE, 10, 10, false);
        assertEquals(ObjectType.ROAD, row.getTileType());
        assertEquals(ObjectType.CARRIAGE, row.getObstacleType());
        assertTrue(!row.isGoingLeft());
    }

    @Test
    public void getTileEdgeCase() {
        Row row = new Row(ObjectType.ROAD, ObjectType.CARRIAGE, 10, 10, false);

        Tile[] tiles = new Tile[10];
        for (int i = 0; i < tiles.length; i++) {
            tiles[i] = new Tile(ObjectType.ROAD);
        }
        assertEquals(tiles.length, row.getAllTiles().length);

    }

}
