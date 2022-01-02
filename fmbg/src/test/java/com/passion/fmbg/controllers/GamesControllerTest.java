package com.passion.fmbg.controllers;

import org.junit.Test;

public class GamesControllerTest {
    // ?categories={categories}
    // &mechanics={mechanics}
    // &min_players={min_players}
    // &max_players={max_players}
    // &min_playtime={min_playtime}
    // &min_age={min_age}
    // &order_by={order_by}
    // &ascending={ascending}

    @Test
    public void testUrlConstruct() {
        // Given
        GamesController gamesController = new GamesController();
        gamesController.findGames("KUBCKBkGxV", "",
                2, 4, 60, 90, 10,
                "rank", "true");
        System.out.println(gamesController.urlConstruct());
    }
}
