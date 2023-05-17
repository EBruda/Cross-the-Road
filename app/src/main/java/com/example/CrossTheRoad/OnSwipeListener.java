package com.example.CrossTheRoad;

import android.content.Context;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

public class OnSwipeListener implements View.OnTouchListener {

    private final GestureDetector gestureDetector;
    private Game game;
    private Player player;

    public OnSwipeListener(Context context, Game game) {
        this.game = game;
        player = game.getPlayer();
        gestureDetector = new GestureDetector(context, new GestureListener());
    }

    public boolean onTouch(View v, MotionEvent event) {
        return gestureDetector.onTouchEvent(event);
    }

    private final class GestureListener extends GestureDetector.SimpleOnGestureListener {

        private static final int SWIPE_DISTANCE_THRESHOLD = 50;
        private static final int SWIPE_VELOCITY_THRESHOLD = 50;

        @Override
        public boolean onDown(MotionEvent e) {
            return true;
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            float distanceX = e2.getX() - e1.getX();
            float distanceY = e2.getY() - e1.getY();
            System.out.println("X: " + Math.abs(velocityX));
            System.out.println("Y: " + Math.abs(velocityY));

            boolean checkThresholdY = Math.abs(distanceY) > SWIPE_DISTANCE_THRESHOLD
                    && Math.abs(velocityY) > SWIPE_VELOCITY_THRESHOLD;
            boolean checkThresholdX = Math.abs(distanceX) > SWIPE_DISTANCE_THRESHOLD
                    && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD;

            if (checkThresholdX || checkThresholdY) { // Changed to OR instead of AND
                if (Math.abs(distanceY) > Math.abs(distanceX)) {
                    //vertical movement:
                    if (distanceY > 0) {
                        //swipe down
                        player.moveDown(game);
                    } else {
                        //swipe up
                        player.moveUp(game);
                    }
                } else {
                    //horizontal movement:
                    if (distanceX > 0) {
                        //swipe right
                        player.moveRight(game);
                    } else {
                        //swipe left
                        player.moveLeft(game);
                    }
                }
                return true;
            }
            return false;
        }
    }
}