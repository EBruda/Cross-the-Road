package com.example.CrossTheRoad;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;


public class Tile {
    private ObjectType type;

    public Tile(ObjectType type) {
        if (type.equals(ObjectType.GRASS) || type.equals(ObjectType.GOAL)
                || type.equals(ObjectType.ROAD) || type.equals(ObjectType.WATER)
                    || type.equals(ObjectType.WATER2)) {
            this.type = type;
        } else {
            throw new IllegalArgumentException("You must pass a Tile bitmap");
        }
    }

    public void draw(Canvas canvas, int tileHeight, int tileWidth, int xPos, int yPos) {
        Bitmap bitmap = GameView.getBitmap(type);
        canvas.drawBitmap(bitmap, null, new Rect(xPos * tileWidth,
                yPos * tileHeight, (xPos + 1) * tileWidth,
                (yPos + 1) * tileHeight), null);
    }

    public void setType(ObjectType type) {
        this.type = type;
    }
}
