package com.example.CrossTheRoad;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.widget.TextView;

public class EndScreen extends  AppCompatActivity {
    private Bundle extras;
    private TextView scoreValText;
    private static int score = 0;
    private static boolean gameExit = false;
    private MediaPlayer music;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.end_screen);
        extras = getIntent().getExtras();
        scoreValText = (TextView) findViewById(R.id.scoreVal);
        if (extras != null) {
            scoreValText.setText(String.valueOf(extras.getInt("score")));
        }
        score = Integer.parseInt(String.valueOf(scoreValText.getText()));

        Button replayButton = findViewById(R.id.replayBtn);
        replayButton.setOnClickListener(view -> replayGame(view));

        Button exitButton = findViewById(R.id.exitBtn);
        exitButton.setOnClickListener(view -> exitGame(view));
        music = MediaPlayer.create(this, R.raw.gamelosesong);
        music.start();
    }
    public void replayGame(View view) {
        //move on to the end screen:
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("replay", true);
        startActivity(intent);
        music.stop();
    }

    public void exitGame(View view) {
        gameExit = true;
        finishAffinity();
        System.exit(0);
    }

    public static int getScoreVal() {
        return score;
    }
    public static boolean getGameExit() {
        return gameExit;
    }
}