package com.example.CrossTheRoad;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

public class InfoBar {
    private final Player player;
    private int startingLives;

    public InfoBar(Player player) {
        this.player = player;
        this.startingLives = player.getLives();
    }

    // Redraw this every time its fields change
    public void draw(Canvas canvas, int screenWidth) {
        if (canvas == null) {
            throw new IllegalArgumentException("The Canvas object is null dummy");
        }
        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setTextSize(60);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(9);

        if (player.getName().length() > 18) {
            paint.setTextSize(50);
        }

        canvas.drawText(player.getName().toUpperCase(), 450, 60, paint);

        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeWidth(3);

        canvas.drawText(player.getName().toUpperCase(), 450, 60, paint);

        for (int i = 0; i < player.getLives(); i++) {
            canvas.drawBitmap(GameView.getBitmap(ObjectType.LIFE), null,
                    new Rect(80 * i, 0, 80 * (i + 1), 80), null);
        }
        for (int i = player.getLives(); i < startingLives; i++) {
            canvas.drawBitmap(GameView.getBitmap(ObjectType.LOSTLIFE), null,
                    new Rect(80 * i, 0, 80 * (i + 1), 80), null);
        }

        paint.setStrokeWidth(5);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.BLACK);
        canvas.drawRect(screenWidth - 160, 0, screenWidth, 75, paint);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        canvas.drawText(getScoreString(player), screenWidth - 140, 60, paint);
    }

    // Helper for draw()
    public static String getScoreString(Player player) {
        int score = player.getScore() % 10000;
        String result = "";
        int numDigits = 0;
        int temp = score;
        while (temp > 0) {
            numDigits++;
            temp = temp / 10;
        }
        switch (numDigits) {
        case 0: result = "0000";
            break;
        case 1: result = "000" + score;
            break;
        case 2: result = "00" + score;
            break;
        case 3: result = "0" + score;
            break;
        default: result += score;
        }
        return result;
    }
}
