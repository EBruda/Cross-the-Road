package com.example.CrossTheRoad;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class NumberOfLivesTest {

    @Test
    public void easyLevelNumberOfLivesTest() {
        Player player = new Player();
        player.setDifficulty(0);
        assertEquals(5, player.getLives());
    }

    @Test
    public void intermediateLevelNumberOfLivesTest() {
        Player player = new Player();
        player.setDifficulty(1);
        assertEquals(4, player.getLives());
    }

    @Test
    public void averageLevelNumberOfLivesTest() {
        Player player = new Player();
        player.setDifficulty(2);
        assertEquals(3, player.getLives());
    }

    @Test
    public void hardLevelNumberOfLivesTest() {
        Player player = new Player();
        player.setDifficulty(3);
        assertEquals(2, player.getLives());
    }

    @Test
    public void expertLevelNumberOfLivesTest() {
        Player player = new Player();
        player.setDifficulty(4);
        assertEquals(1, player.getLives());
    }







}
