package com.example.CrossTheRoad;

public class Ending {
    private boolean replay;
    private int score;
    private boolean reset;

    private static boolean displayWinScreen = false;
    public Ending(boolean replay, int score, boolean reset) {
        this.replay = replay;
        this.score = score;
        this.reset = reset;
    }

    public int getScore() {
        return score;
    }

    public static boolean isDisplayWinScreen() {
        return displayWinScreen;
    }

    public static void setDisplayWinScreen(boolean displayWinScreen) {
        Ending.displayWinScreen = displayWinScreen;
    }
}
