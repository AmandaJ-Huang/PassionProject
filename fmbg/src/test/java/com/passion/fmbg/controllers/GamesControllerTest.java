package com.passion.fmbg.controllers;

import org.junit.Test;

public class GamesControllerTest {

    @Test
    public void testUrlConstruct() {
        // Given
        GamesController gamesController = new GamesController();
        gamesController.findGames("KUBCKBkGxV", "WPytek5P8l",
                2, 4, 60, 90, 10,
                "rank", "true");
        System.out.println(gamesController.urlConstruct());
    }
}
