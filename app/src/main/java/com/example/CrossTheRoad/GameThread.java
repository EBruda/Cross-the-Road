package com.example.CrossTheRoad;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

public class GameThread extends Thread {

    private final SurfaceHolder surfaceHolder;
    private GameView gameView;
    private int totalUpdates;
    private boolean isRunning;

    public GameThread(GameView gameView, SurfaceHolder surfaceHolder) {
        this.gameView = gameView;
        this.surfaceHolder = surfaceHolder;
    }

    public void run() {
        super.run();

        double currentTime;
        isRunning = true;
        int waterUpdateCount = 0;

        Canvas canvas = null;
        currentTime = System.currentTimeMillis();
        while (isRunning) {
            // ONLY RUN UPDATE CODE IF 0.1 SECONDS HAVE PASSED
            if (System.currentTimeMillis() - currentTime > 200) { //100
                waterUpdateCount++;
                if (waterUpdateCount > 4) {
                    gameView.getGame().waterAnim();
                    waterUpdateCount = 0;
                }

                try {
                    currentTime = System.currentTimeMillis();
                    /*
                    This is where new vehicles are generated and all of the obstacles
                    move one "step" forward, in accordance with their speed.
                    If speed is 30, an object moves 30 pixels per update
                     */
                    gameView.update();
                    if (GameView.continueGame) {
                        canvas = surfaceHolder.lockCanvas();
                        synchronized (surfaceHolder) {
                            gameView.draw(canvas);
                            totalUpdates++;
                        }
                    } else {
                        return;
                    }
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                } finally {
                    if (canvas != null) {
                        try {
                            surfaceHolder.unlockCanvasAndPost(canvas);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    public int getTotalUpdates() {
        return totalUpdates;
    }
}
