package com.example.CrossTheRoad;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class WinScreen extends  AppCompatActivity {
    private Bundle extras;
    private TextView scoreValText;
    private static int score = 0;
    private static boolean gameExit = false;
    private static boolean playMusic = false;
    private MediaPlayer music;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.win_screen);
        extras = getIntent().getExtras();
        scoreValText = (TextView) findViewById(R.id.scoreVal);
        if (extras != null) {
            scoreValText.setText(String.valueOf(extras.getInt("score")));
        }
        score = Integer.parseInt(String.valueOf(scoreValText.getText()));
        Ending e = new Ending(false, score, false);
        Ending.setDisplayWinScreen(true);

        Button replayButton = findViewById(R.id.replayBtn);
        replayButton.setOnClickListener(view -> replayGame(view));

        Button exitButton = findViewById(R.id.exitBtn);
        exitButton.setOnClickListener(view -> exitGame(view));
        music = MediaPlayer.create(this, R.raw.gamewinsong);
        music.start();

    }
    public void replayGame(View view) {
        //move on to the end screen:
        music.stop();
        playMusic = false;
        Button replayButton = findViewById(R.id.replayBtn);
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("replay", true);
        startActivity(intent);
    }

    public void exitGame(View view) {
        music.stop();
        gameExit = true;
        finishAffinity();
        System.exit(0);
    }
}