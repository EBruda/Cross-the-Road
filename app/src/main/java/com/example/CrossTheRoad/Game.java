package com.example.CrossTheRoad;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Game {
    protected final int screenHeight;
    protected final int screenWidth;
    protected final int numRows = 18;
    protected final int numCols = 9;
    protected final int tileHeight;
    protected final int tileWidth;
    private Row[] background;
    private InfoBar infoBar;
    private Player player;

    public Game(int screenHeight, int screenWidth, Player player) {
        this.screenHeight = screenHeight;
        this.screenWidth = screenWidth;
        tileHeight = screenHeight / numRows;
        tileWidth = screenWidth / numCols;

        this.player = player;
        resetPlayerPosition();
        infoBar = new InfoBar(player);

        setDefaultBackground();
        player.setAllRows(background);
        Obstacle goal = new Obstacle(ObjectType.GOAL, player.getLeft(), player.getRight(), false);
        background[1].getObstacles().add(goal);
    }

    public void draw(Canvas c) {
        Paint paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.FILL);
        c.drawPaint(paint);

        for (int i = 0; i < background.length; i++) {
            Row row = background[i];
            row.draw(c, i, tileHeight, tileWidth);
        }

        player.draw(c);
        infoBar.draw(c, screenWidth);

        paint.setColor(Color.BLACK);
        paint.setTextSize(60);
        c.drawText(diffMaker(player.getDifficulty()), 20, screenHeight - 50, paint);
    }




    public void setDefaultBackground() {
        Row[] output = new Row[numRows];
        output[0] = new Row(ObjectType.GRASS, null, numCols, tileWidth);
        output[1] = new Row(ObjectType.GRASS, null, numCols, tileWidth);
        output[2] = new Row(ObjectType.WATER, ObjectType.LONGLOG, numCols, tileWidth);
        output[3] = new Row(ObjectType.WATER, ObjectType.SHORTLOG, numCols, tileWidth);
        output[4] = new Row(ObjectType.WATER, ObjectType.MEDIUMLOG, numCols, tileWidth);
        output[5] = new Row(ObjectType.GRASS, null, numCols, tileWidth);
        output[6] = new Row(ObjectType.ROAD, ObjectType.HORSE, numCols, tileWidth);
        output[7] = new Row(ObjectType.ROAD, ObjectType.WAGON, numCols, tileWidth);
        output[8] = new Row(ObjectType.GRASS, null, numCols, tileWidth);
        output[9] = new Row(ObjectType.WATER, ObjectType.SHORTLOG, numCols, tileWidth);
        output[10] = new Row(ObjectType.WATER, ObjectType.MEDIUMLOG, numCols, tileWidth);
        output[11] = new Row(ObjectType.WATER, ObjectType.LONGLOG, numCols, tileWidth);
        output[12] = new Row(ObjectType.GRASS, null, numCols, tileWidth);
        output[13] = new Row(ObjectType.ROAD, ObjectType.CARRIAGE, numCols, tileWidth);
        output[14] = new Row(ObjectType.ROAD, ObjectType.HORSE, numCols, tileWidth);
        output[15] = new Row(ObjectType.GRASS, null, numCols, tileWidth);
        output[16] = new Row(ObjectType.GRASS, null, numCols, tileWidth);
        output[17] = new Row(ObjectType.GRASS, null, numCols, tileWidth);

        background = output;
    }

    public static String diffMaker(int diff) {
        switch (diff) {
        case 1: return "INTERMEDIATE";
        case 2: return "AVERAGE";
        case 3: return "HARD";
        case 4: return "EXPERT";
        default: return "EASY";
        }
    }

    public void generateNewObstacles() {
        for (int i = 0; i < background.length; i++) {
            Row row = background[i];
            if (row.getTileType() == ObjectType.ROAD
                    || row.getTileType() == ObjectType.WATER
                    || row.getTileType() == ObjectType.WATER2) {
                if (row.getNewestObstacle() == null
                        || (row.getNewestObstacle().getLeft() > 400 && !row.isGoingLeft())
                        || (row.getNewestObstacle().getRight() < screenWidth - 400
                        && row.isGoingLeft())) {
                    row.addNewObstacle();
                }
                if (row.getObstacles() != null) {
                    row.getObstacles().removeIf(n -> (!inBounds(n)));
                }
            }
        }
    }

    public void update() {
        generateNewObstacles();
        player.setOnLog(false);
        boolean needsDeath = false;

        needsDeath = checkDeathStatusObstacles() || checkDeathStatusWater();

        if (needsDeath) {
            die();
        }
        if (player.getWonGame()) {
            player.win();
        }
    }

    public boolean checkDeathStatusObstacles() {
        for (int i = 0; i < background.length; i++) {
            Row row = background[i];
            if (row.getTileType() == ObjectType.GOAL && player.getRow() == i) {
                player.setWonGame(true);
            }
            for (Obstacle o : row.getObstacles()) {

                ObjectType rowType = row.getTileType();
                if (rowType == ObjectType.ROAD) {
                    o.move();
                    if (player.getRow() == i && o.checkPlayerCollision(player)) {
                        return true;
                    }
                } else if (rowType == ObjectType.GRASS) {
                    if (o.checkPlayerCollision(player)) {
                        if (player.getRow() == 1) {
                            player.setWonGame(true);
                        }
                    }
                } else if (rowType == ObjectType.WATER || rowType == ObjectType.WATER2) {
                    if (player.getRow() == i && o.checkPlayerFullOverlap(player)) {
                        o.setHoldingPlayer(true);
                        player.setOnLog(true);
                        o.moveWithPlayer(player);
                    } else {
                        o.setHoldingPlayer(false);
                        o.move();
                    }
                }
            }
        }
        return false;
    }

    public boolean checkDeathStatusWater() {
        if (!player.isOnLog() && (background[player.getRow()].getTileType() == ObjectType.WATER
                || background[player.getRow()].getTileType() == ObjectType.WATER2)) {
            return true;
        }
        // Giving 5 pixels of buffer
        if (player.getLeft() < -5 || player.getRight() > screenWidth + 5) {
            return true;
        }

        return false;
    }



    public Player getPlayer() {
        return player;
    }

    public boolean inBounds(Obstacle obj) {
        if (obj.isMovable()) {
            if (obj.isGoingLeft()) {
                return obj.getRight() >= -20;
            } else {
                return obj.getLeft() <= screenHeight + 20;
            }
        }
        return true;
    }

    public void waterAnim() {
        for (Row row : background) {
            if (row.getTileType() == ObjectType.WATER) {
                row.setTileType(ObjectType.WATER2);
                for (Tile tile : row.getAllTiles()) {
                    tile.setType(ObjectType.WATER2);
                }

            } else if (row.getTileType() == ObjectType.WATER2) {
                row.setTileType(ObjectType.WATER);
                for (Tile tile : row.getAllTiles()) {
                    tile.setType(ObjectType.WATER);
                }
            }
        }
    }

    public void setBackground(Row[] rows) {
        background = rows;
    }
    public Row[] getBackground() {
        return background;
    }

    public void die() {
        player.die();
        resetPlayerPosition();
    }

    public void resetPlayerPosition() {
        player.setLeft(screenWidth / 2 - (tileWidth / 2));
        player.setTop(screenHeight - 2 * tileHeight);
        player.setRight(screenWidth / 2 + (tileWidth / 2));
        player.setBottom(screenHeight - tileHeight);
        player.setRow(numRows - 2);
        player.setMaxTop();
    }
}
