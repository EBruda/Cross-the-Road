package com.example.CrossTheRoad;

import static com.example.CrossTheRoad.Obstacle.getSize;

import android.graphics.Canvas;

import java.util.ArrayList;

public class Row {
    private ObjectType tileType;
    private ObjectType obstacleType;
    private Tile[] tiles;
    private boolean goingLeft;
    private ArrayList<Obstacle> obstacles;
    private Obstacle newestObstacle;
    private int tileWidth;
    private int numTiles;

    public Row(ObjectType tileType, ObjectType obstacleType, int numTiles, int tileWidth) {
        this.tileType = tileType;
        this.obstacleType = obstacleType;
        tiles = new Tile[numTiles];
        for (int i = 0; i < tiles.length; i++) {
            tiles[i] = new Tile(tileType);
        }
        goingLeft = Math.random() < 0.5;

        obstacles = new ArrayList<>();

        newestObstacle = null;

        this.numTiles = numTiles;
        this.tileWidth = tileWidth;
    }

    public Row(ObjectType tileType, ObjectType obstacleType,
               int numTiles, int tileWidth, boolean goingLeft) {
        this(tileType, obstacleType, numTiles, tileWidth);
        this.goingLeft = goingLeft;
    }

    public void draw(Canvas c, int rowNumber, int tileHeight, int tileWidth) {
        for (int i = 0; i < tiles.length; i++) {
            tiles[i].draw(c, tileHeight, tileWidth, i, rowNumber);
        }
        for (Obstacle o : obstacles) {
            o.draw(c, rowNumber * tileHeight,
                    (rowNumber + 1) * tileHeight, goingLeft);
        }
    }

    public void addNewObstacle() {
        if (goingLeft) {
            newestObstacle = new Obstacle(obstacleType, tileWidth * numTiles,
                    tileWidth * numTiles + getSize(obstacleType) * tileWidth,
                    true);
        } else {
            newestObstacle = new Obstacle(obstacleType,
                    -1 * (getSize(obstacleType) * tileWidth), 0, false);
        }
        obstacles.add(newestObstacle);
    }

    public ObjectType getTileType() {
        return tileType;
    }

    public ArrayList<Obstacle> getObstacles() {
        return obstacles;
    }

    public Obstacle getNewestObstacle() {
        return newestObstacle;
    }

    public ObjectType getObstacleType() {
        return obstacleType;
    }

    public boolean isGoingLeft() {
        return goingLeft;
    }

    public Tile[] getAllTiles() {
        return tiles;
    }

    public void setTileType(ObjectType tileType) {
        this.tileType = tileType;
    }
}
