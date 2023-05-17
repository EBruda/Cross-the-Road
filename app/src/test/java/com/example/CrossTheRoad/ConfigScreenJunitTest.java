package com.example.CrossTheRoad;


import static org.junit.Assert.assertEquals;

import org.junit.Test;

// @RunWith is required only if you use a mix of JUnit3 and JUnit4.
public class ConfigScreenJunitTest {
    @Test
    public void nullNameTest() {
        assertEquals(MainActivity.checkNameTestHelper(null), false);

    }

    @Test
    public void emptyNameTest() {
        assertEquals(MainActivity.checkNameTestHelper(""), false);

    }

    @Test
    public void longNameTest() {
        assertEquals(MainActivity.checkNameTestHelper("Too long of a character name for this game"), false);

    }

    @Test
    public void nameTest() {
        assertEquals(MainActivity.checkNameTestHelper("Player"), true);

    }

}