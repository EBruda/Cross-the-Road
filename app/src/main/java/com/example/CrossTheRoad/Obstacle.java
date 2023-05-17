package com.example.CrossTheRoad;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

public class Obstacle {
    private ObjectType type;
    private int speed;
    private int left;
    private int right;
    private boolean goingLeft;
    private boolean walkable;
    private boolean movable;
    private int size;
    private boolean holdingPlayer;

    public Obstacle(ObjectType type, int left, int right, boolean goingLeft) {
        this.type = type;
        this.speed = getSpeed(type);
        this.left = left;
        this.right = right;
        this.size = getSize(type);
        this.goingLeft = goingLeft;
        this.walkable = (isWalkable(type));
        this.movable = (isMovable(type));
        this.holdingPlayer = false;
    }

    public void draw(Canvas canvas, int top, int bottom, boolean goingLeft) {
        if (canvas == null) {
            throw new IllegalArgumentException("3The Canvas object is null dummy");
        }

        int scale = 40;
        int offset = 0;
        if (type == ObjectType.WAGON) {
            scale = 80;
            offset = 5;
        }
        if (type == ObjectType.CARRIAGE) {
            scale = 60;
            offset = 5;
        }
        Bitmap bitmap = GameView.getBitmap(type);

        if (goingLeft) {
            // MIRROR SPRITE -- I don't understand the code but it works so :)
            canvas.save();
            canvas.scale(-1.0f, 1.0f);
            canvas.drawBitmap(bitmap, null, new Rect(-right - 10, top - scale + offset,
                    -left + 10, bottom + scale + offset), null);
            canvas.restore();
        } else {
            canvas.drawBitmap(bitmap, null, new Rect(left - 10, top - scale + offset,
                    right + 10, bottom + scale + offset), null);
        }
    }

    /**
     * The move method tells if an object can move and how it should move
     */
    public void move() {
        if (goingLeft && movable) {
            left -= speed;
            right -= speed;
        } else if (movable) {
            left += speed;
            right += speed;
        }
    }

    public void moveWithPlayer(Player player) {
        if (goingLeft && movable) {
            left -= speed;
            player.setLeft(player.getLeft() - speed);
            right -= speed;
            player.setRight(player.getRight() - speed);
        } else if (movable) {
            left += speed;
            player.setLeft(player.getLeft() + speed);
            right += speed;
            player.setRight(player.getRight() + speed);
        }
    }

    public boolean checkPlayerCollision(Player player) {
        if (player.getLeft() >= left && player.getLeft() <= right) {
            // If left edge of player is between left and right of a vehicle
            return true;
        }
        if (player.getRight() >= left && player.getRight() < right) {
            // If right edge of player is between left and right of a vehicle
            return true;
        }
        return false;
    }

    public boolean checkPlayerFullOverlap(Player player) {
        return player.getLeft() >= left && player.getRight() <= right;
    }

    /**
     * Gets the speed of an obstacle in the game
     *
     * @return speed of an obstacle
     */
    public int getSpeed() {
        return speed;
    }

    /**
     * Sets the speed of an obstacle to a new value
     *
     * @param speed the speed of a moving object in the game
     */
    public void setSpeed(int speed) {
        this.speed = speed;
    }

    /**
     * Gets the left coordinate of the obstacle
     *
     * @return left coordinate of the obstacle
     */
    public int getLeft() {
        return left;
    }

    /**
     * Gets the right coordinate of the obstacle
     *
     * @return right coordinate of the obstacle
     */
    public int getRight() {
        return right;
    }

    /**
     * Gets the diretions of the obstacle
     *
     * @return whether or not the obstacle is going left of not
     */
    public boolean isGoingLeft() {
        return goingLeft;
    }

    /**
     * Tells if an object can be walked on by the player
     *
     * @return whether or not an object is walkable
     */
    public boolean isWalkable() {
        return walkable;
    }

    public static boolean isWalkable(ObjectType type) {
        return true;
        // CHANGE THIS LATER TO MAKE LOG TILES WALKABLE
    }

    /**
     * Checks if the obstacle can move or should be still
     *
     * @return whether or not the object can move or not
     */
    public boolean isMovable() {
        return movable;
    }

    public static boolean isMovable(ObjectType type) {
        return (type == ObjectType.HORSE
                || type == ObjectType.CARRIAGE
                || type == ObjectType.WAGON
                || type == ObjectType.LONGLOG
                || type == ObjectType.MEDIUMLOG
                || type == ObjectType.SHORTLOG);
        // CHANGE THIS LATER TO MAKE LOG TILES MOVABLE
    }

    public void setHoldingPlayer(boolean holdingPlayer) {
        this.holdingPlayer = holdingPlayer;
    }

    public static int getSize(ObjectType type) {
        if (type == ObjectType.HORSE) {
            return 1;
        } else if (type == ObjectType.SHORTLOG || type == ObjectType.CARRIAGE) {
            return 2;
        } else if (type == ObjectType.MEDIUMLOG || type == ObjectType.WAGON) {
            return 3;
        } else {
            return 4;
        }
    }

    public static int getSpeed(ObjectType type) {
        if (type == ObjectType.HORSE || type == ObjectType.SHORTLOG) {
            return 15;
        }
        if (type == ObjectType.CARRIAGE || type == ObjectType.MEDIUMLOG) {
            return 25;
        }
        if (type == ObjectType.WAGON || type == ObjectType.LONGLOG) {
            return 35;
        }
        return 0;
    }
}
