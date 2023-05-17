/**
 * This class defines the Player object.
 */

package com.example.CrossTheRoad;

import android.graphics.Canvas;
import android.graphics.Rect;

public class Player {

    private int difficulty;
    private int score;

    private int maxScore;
    private ObjectType type;
    private int lives;
    private String name;
    private int left;
    private int top;
    private int maxTop;
    private int right;
    private int bottom;
    private boolean gameOver = false;
    private int row;
    private Row[] allRows;
    private boolean onLog;
    private boolean wonGame;

    public Player() {
        name = "default";
        difficulty = 0;
        score = 0;
        type = ObjectType.PRINCESS;
        lives = 5;
        left = 50;
        top = 50;
        right = 100;
        bottom = 100;
        maxTop = top;
        row = 16;
        onLog = false;
        wonGame = false;

    }

    public Player(String name, int difficulty, ObjectType type) {
        this();
        this.name = name;
        this.difficulty = difficulty;
        this.type = type;
        this.lives = 5 - difficulty;
    }

    public void draw(Canvas canvas) {
        if (canvas == null) {
            throw new IllegalArgumentException("3The Canvas object is null dummy");
        }
        canvas.drawBitmap(GameView.getBitmap(type), null,
                new Rect(left - 20, top - 30, right + 20, bottom + 10), null);
    }

    public void updateScore() {
        int scoreAdd = (int) (100.0 * ((double) lives / (5 - difficulty)));
        if (allRows != null && allRows[row] != null && allRows[row].getObstacleType() != null) {
            if (allRows[row].getObstacleType() == (ObjectType.CARRIAGE)) {
                scoreAdd = (int) (200.0 * ((double) lives / (5 - difficulty)));
            } else if (allRows[row].getObstacleType() == (ObjectType.WAGON)) {
                scoreAdd = (int) (175.0 * ((double) lives / (5 - difficulty)));
            } else if (allRows[row].getObstacleType() == (ObjectType.HORSE)) {
                scoreAdd = (int) (150.0 * ((double) lives / (5 - difficulty)));
            }
        }
        score += scoreAdd;
    }

    public boolean onWaterTile() {
        return allRows[row].getTileType() == ObjectType.WATER
                || allRows[row].getTileType() == ObjectType.WATER2;
    }

    public void die() {
        lives--;
        //highest score for all lives
        if (score > maxScore) {
            maxScore = score;
        }
        if (lives == 0) {
            // end game
            gameOver = true;
        } else {
            score = 0;
        }
    }

    public void win() {
        if (score > maxScore) {
            maxScore = score;
        }
        gameOver = true;
    }

    public void moveUp(Game game) {
        moveUp(game.tileHeight);
    }

    public void moveUp(int change) {

        // NEW CODE
        // move player
        if (top - change < 0) {
            gameOver = true;
            return;
        }
        top -= change;
        bottom -= change;
        if (top < maxTop) {
            maxTop = top;
            updateScore();
        }
        row--;
    }

    public void moveDown(Game game) {
        moveDown(game.tileHeight, game.screenHeight);
    }

    public void moveDown(int change, int boundary) {

        // NEW CODE
        // move player
        if (bottom + change > boundary) {
            return;
        }
        top += change;
        bottom += change;
        row++;
    }

    public void moveLeft(Game game) {
        moveLeft(game.tileWidth);
    }

    public void moveLeft(int change) {
        // We don't need any additions here for water tiles for now since there's no logs
        if (left - change < 0) {
            return;
        }
        left -= change;
        right -= change;
    }

    public void moveRight(Game game) {
        moveRight(game.tileWidth, game.screenWidth);
    }

    public void moveRight(int change, int boundary) {
        // We don't need any additions here for water tiles for now since there's no logs
        if (right + change > boundary) {
            return;
        }
        right += change;
        left += change;
    }


    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
        setLives();
    }

    public int getScore() {
        return score;
    }

    public int getMaxScore() {
        return maxScore;
    }

    public void setMaxScore(int max) {
        this.maxScore = max;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getLives() {
        return lives;
    }

    public void setLives() {
        this.lives = 5 - difficulty;
    }

    public String getName() {
        return name;
    }

    public int getLeft() {
        return left;
    }

    public void setLeft(int left) {
        this.left = left;
    }

    public int getMaxTop() {
        return maxTop;
    }

    public void setMaxTop() {
        maxTop = top;
    }

    public int getTop() {
        return top;
    }

    public void setTop(int top) {
        this.top = top;
    }

    public int getRight() {
        return right;
    }

    public void setRight(int right) {
        this.right = right;
    }

    public int getBottom() {
        return bottom;
    }

    public void setBottom(int bottom) {
        this.bottom = bottom;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void setAllRows(Row[] allRows) {
        this.allRows = allRows;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public boolean isOnLog() {
        return onLog;
    }

    public void setOnLog(boolean onLog) {
        this.onLog = onLog;
    }
    public boolean getWonGame() {
        return this.wonGame;
    }
    public void setWonGame(boolean wonGame) {
        this.wonGame = wonGame;
        Ending.setDisplayWinScreen(wonGame);
    }

}