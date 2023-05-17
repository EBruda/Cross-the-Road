package com.example.CrossTheRoad;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import org.junit.Test;
import static org.junit.Assert.assertThrows;
import android.view.View;

public class EndScreenJUnitTest {
    @Test
    public void highScoreTest() {
        Player player = new Player();
        assertEquals(EndScreen.getScoreVal(), player.getMaxScore());
    }

    //originally no replay (begins with start screen)
    @Test
    public void replayTest() {
        assertEquals(false, MainActivity.replay);
    }

    @Test
    public void replayPlayerTest() {
        Ending ending = new Ending(true, 10000, true);
        Player p = new Player();
        assertNotEquals(p.getScore(), ending.getScore());
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
