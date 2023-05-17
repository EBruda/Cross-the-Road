package com.example.CrossTheRoad;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.media.MediaPlayer;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

public class GameView extends SurfaceView implements SurfaceHolder.Callback {
    private static BitmapBuilder bitmapBuilder = null;
    private SurfaceHolder surfaceHolder;
    private Game game;
    private GameThread gameThread;
    private MediaPlayer music;

    protected static boolean continueGame = true;
    
    public GameView(Context context) {
        super(context);
    }

    public GameView(Context context, Player newPlayer) {
        super(context);

        game = new Game(getScreenHeight(), getScreenWidth(), newPlayer);

        bitmapBuilder = BitmapBuilder.getInstance(context);

        surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);

        gameThread = new GameThread(this, surfaceHolder);

        setFocusable(true);
        this.setOnTouchListener(new OnSwipeListener(context, game));
        music = MediaPlayer.create(context, R.raw.gamesong);
        music.start();
    }

    @Override
    public void draw(Canvas c) {
        super.draw(c);

        game.draw(c);
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder) {
        gameThread.start();
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }
    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder) {

    }

    private static int getScreenWidth() {
        return Resources.getSystem().getDisplayMetrics().widthPixels;
    }

    private static int getScreenHeight() {
        return Resources.getSystem().getDisplayMetrics().heightPixels;
    }

    public Game getGame() {
        return game;
    }

    public void decrementScoreTimer() {
        Player player = game.getPlayer();
        if (player.getScore() > 0
                && gameThread.getTotalUpdates() % 10 == 0 && !player.isGameOver()) {
            player.setScore(player.getScore() - 5);
        }
    }

    public static Bitmap getBitmap(ObjectType type) {
        return bitmapBuilder.getBitmapSet().get(type);
    }

    public void update() {
        if (game.getPlayer().getWonGame()) {
            toWinScreen();
        } else if (game.getPlayer().getLives() <= 0) {
            toEndScreen();
        }  else {
            decrementScoreTimer();
            game.update();
        }
    }

    public void toWinScreen() {
        Intent intent = new Intent(this.getContext(), WinScreen.class);
        intent.putExtra("score", game.getPlayer().getMaxScore());
        getContext().startActivity(intent);
        continueGame = false;
        music.stop();
    }

    public void toEndScreen() {
        Intent intent = new Intent(this.getContext(), EndScreen.class);
        intent.putExtra("score", game.getPlayer().getMaxScore());
        getContext().startActivity(intent);
        continueGame = false;
        music.stop();
    }

    public static void setContinueGame(boolean continueGame) {
        GameView.continueGame = continueGame;
    }
}
