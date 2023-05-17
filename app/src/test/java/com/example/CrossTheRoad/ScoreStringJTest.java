package com.example.CrossTheRoad;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class ScoreStringJTest {

    @Test
    public void zeroDigitScoreTest() {
        Player player = new Player();
        assertEquals("0000", InfoBar.getScoreString(player));
    }

    @Test
    public void oneDigitScoreTest() {
        Player player = new Player();
        player.setScore(1);
        assertEquals("0001", InfoBar.getScoreString(player));
        player.setScore(9);
        assertEquals("0009", InfoBar.getScoreString(player));
    }

    @Test
    public void twoDigitScoreTest() {
        Player player = new Player();
        player.setScore(10);
        assertEquals("0010", InfoBar.getScoreString(player));
        player.setScore(99);
        assertEquals("0099", InfoBar.getScoreString(player));
    }

    @Test
    public void threeDigitScoreTest() {
        Player player = new Player();
        player.setScore(100);
        assertEquals("0100", InfoBar.getScoreString(player));
        player.setScore(999);
        assertEquals("0999", InfoBar.getScoreString(player));
    }

    @Test
    public void fourDigitScoreTest() {
        Player player = new Player();
        player.setScore(1000);
        assertEquals("1000", InfoBar.getScoreString(player));
        player.setScore(9999);
        assertEquals("9999", InfoBar.getScoreString(player));
    }

    @Test
    public void scoreOverflow() {
        Player player = new Player();
        player.setScore(12345);
        assertEquals("2345", InfoBar.getScoreString(player));
        player.setScore(999999999);
        assertEquals("9999", InfoBar.getScoreString(player));
    }
}
