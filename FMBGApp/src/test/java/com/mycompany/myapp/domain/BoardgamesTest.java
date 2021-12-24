package com.mycompany.myapp.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.mycompany.myapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class BoardgamesTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Boardgames.class);
        Boardgames boardgames1 = new Boardgames();
        boardgames1.setId(1L);
        Boardgames boardgames2 = new Boardgames();
        boardgames2.setId(boardgames1.getId());
        assertThat(boardgames1).isEqualTo(boardgames2);
        boardgames2.setId(2L);
        assertThat(boardgames1).isNotEqualTo(boardgames2);
        boardgames1.setId(null);
        assertThat(boardgames1).isNotEqualTo(boardgames2);
    }
}
