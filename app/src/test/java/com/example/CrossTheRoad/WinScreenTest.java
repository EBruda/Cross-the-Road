package com.example.CrossTheRoad;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import android.view.View;

import org.junit.Test;

public class WinScreenTest {
    @Test
    public void highScoreTest() {
        Player player = new Player();
        assertEquals(EndScreen.getScoreVal(), player.getMaxScore());
    }

    @Test
    public void replayPlayerTestWin() {
        Ending ending = new Ending(false, 10000, false);
        Player player = new Player();
        player.setWonGame(true);
        assertTrue(Ending.isDisplayWinScreen());
    }

    @Test
    public void exitGameTest() {
        assertThrows(RuntimeException.class, () -> {
            EndScreen end = new EndScreen();
            View view = new View(end);
            end.exitGame(view);
            end.getGameExit();
        });
    }



}
