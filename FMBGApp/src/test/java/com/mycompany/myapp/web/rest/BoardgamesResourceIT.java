package com.mycompany.myapp.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.mycompany.myapp.IntegrationTest;
import com.mycompany.myapp.domain.Boardgames;
import com.mycompany.myapp.repository.BoardgamesRepository;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link BoardgamesResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class BoardgamesResourceIT {

    private static final String DEFAULT_THUMBNAIL_URL = "AAAAAAAAAA";
    private static final String UPDATED_THUMBNAIL_URL = "BBBBBBBBBB";

    private static final String DEFAULT_PRIMARY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_PRIMARY_NAME = "BBBBBBBBBB";

    private static final Integer DEFAULT_MINPLAYERS = 1;
    private static final Integer UPDATED_MINPLAYERS = 2;

    private static final Integer DEFAULT_MAXPLAYERS = 1;
    private static final Integer UPDATED_MAXPLAYERS = 2;

    private static final Integer DEFAULT_SUGGESTED_NUMPLAYERS = 1;
    private static final Integer UPDATED_SUGGESTED_NUMPLAYERS = 2;

    private static final Integer DEFAULT_PLAYINGTIME = 1;
    private static final Integer UPDATED_PLAYINGTIME = 2;

    private static final Integer DEFAULT_SUGGESTED_PLAYERAGE = 1;
    private static final Integer UPDATED_SUGGESTED_PLAYERAGE = 2;

    private static final Float DEFAULT_RATING = 1F;
    private static final Float UPDATED_RATING = 2F;

    private static final Long DEFAULT_RANK = 1L;
    private static final Long UPDATED_RANK = 2L;

    private static final Float DEFAULT_AVERAGEWEIGHT = 1F;
    private static final Float UPDATED_AVERAGEWEIGHT = 2F;

    private static final String DEFAULT_CATEGORY = "AAAAAAAAAA";
    private static final String UPDATED_CATEGORY = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/boardgames";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private BoardgamesRepository boardgamesRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restBoardgamesMockMvc;

    private Boardgames boardgames;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Boardgames createEntity(EntityManager em) {
        Boardgames boardgames = new Boardgames()
            .thumbnail_url(DEFAULT_THUMBNAIL_URL)
            .primary_name(DEFAULT_PRIMARY_NAME)
            .minplayers(DEFAULT_MINPLAYERS)
            .maxplayers(DEFAULT_MAXPLAYERS)
            .suggested_numplayers(DEFAULT_SUGGESTED_NUMPLAYERS)
            .playingtime(DEFAULT_PLAYINGTIME)
            .suggested_playerage(DEFAULT_SUGGESTED_PLAYERAGE)
            .rating(DEFAULT_RATING)
            .rank(DEFAULT_RANK)
            .averageweight(DEFAULT_AVERAGEWEIGHT)
            .category(DEFAULT_CATEGORY);
        return boardgames;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Boardgames createUpdatedEntity(EntityManager em) {
        Boardgames boardgames = new Boardgames()
            .thumbnail_url(UPDATED_THUMBNAIL_URL)
            .primary_name(UPDATED_PRIMARY_NAME)
            .minplayers(UPDATED_MINPLAYERS)
            .maxplayers(UPDATED_MAXPLAYERS)
            .suggested_numplayers(UPDATED_SUGGESTED_NUMPLAYERS)
            .playingtime(UPDATED_PLAYINGTIME)
            .suggested_playerage(UPDATED_SUGGESTED_PLAYERAGE)
            .rating(UPDATED_RATING)
            .rank(UPDATED_RANK)
            .averageweight(UPDATED_AVERAGEWEIGHT)
            .category(UPDATED_CATEGORY);
        return boardgames;
    }

    @BeforeEach
    public void initTest() {
        boardgames = createEntity(em);
    }

    @Test
    @Transactional
    void createBoardgames() throws Exception {
        int databaseSizeBeforeCreate = boardgamesRepository.findAll().size();
        // Create the Boardgames
        restBoardgamesMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(boardgames)))
            .andExpect(status().isCreated());

        // Validate the Boardgames in the database
        List<Boardgames> boardgamesList = boardgamesRepository.findAll();
        assertThat(boardgamesList).hasSize(databaseSizeBeforeCreate + 1);
        Boardgames testBoardgames = boardgamesList.get(boardgamesList.size() - 1);
        assertThat(testBoardgames.getThumbnail_url()).isEqualTo(DEFAULT_THUMBNAIL_URL);
        assertThat(testBoardgames.getPrimary_name()).isEqualTo(DEFAULT_PRIMARY_NAME);
        assertThat(testBoardgames.getMinplayers()).isEqualTo(DEFAULT_MINPLAYERS);
        assertThat(testBoardgames.getMaxplayers()).isEqualTo(DEFAULT_MAXPLAYERS);
        assertThat(testBoardgames.getSuggested_numplayers()).isEqualTo(DEFAULT_SUGGESTED_NUMPLAYERS);
        assertThat(testBoardgames.getPlayingtime()).isEqualTo(DEFAULT_PLAYINGTIME);
        assertThat(testBoardgames.getSuggested_playerage()).isEqualTo(DEFAULT_SUGGESTED_PLAYERAGE);
        assertThat(testBoardgames.getRating()).isEqualTo(DEFAULT_RATING);
        assertThat(testBoardgames.getRank()).isEqualTo(DEFAULT_RANK);
        assertThat(testBoardgames.getAverageweight()).isEqualTo(DEFAULT_AVERAGEWEIGHT);
        assertThat(testBoardgames.getCategory()).isEqualTo(DEFAULT_CATEGORY);
    }

    @Test
    @Transactional
    void createBoardgamesWithExistingId() throws Exception {
        // Create the Boardgames with an existing ID
        boardgames.setId(1L);

        int databaseSizeBeforeCreate = boardgamesRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restBoardgamesMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(boardgames)))
            .andExpect(status().isBadRequest());

        // Validate the Boardgames in the database
        List<Boardgames> boardgamesList = boardgamesRepository.findAll();
        assertThat(boardgamesList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllBoardgames() throws Exception {
        // Initialize the database
        boardgamesRepository.saveAndFlush(boardgames);

        // Get all the boardgamesList
        restBoardgamesMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(boardgames.getId().intValue())))
            .andExpect(jsonPath("$.[*].thumbnail_url").value(hasItem(DEFAULT_THUMBNAIL_URL)))
            .andExpect(jsonPath("$.[*].primary_name").value(hasItem(DEFAULT_PRIMARY_NAME)))
            .andExpect(jsonPath("$.[*].minplayers").value(hasItem(DEFAULT_MINPLAYERS)))
            .andExpect(jsonPath("$.[*].maxplayers").value(hasItem(DEFAULT_MAXPLAYERS)))
            .andExpect(jsonPath("$.[*].suggested_numplayers").value(hasItem(DEFAULT_SUGGESTED_NUMPLAYERS)))
            .andExpect(jsonPath("$.[*].playingtime").value(hasItem(DEFAULT_PLAYINGTIME)))
            .andExpect(jsonPath("$.[*].suggested_playerage").value(hasItem(DEFAULT_SUGGESTED_PLAYERAGE)))
            .andExpect(jsonPath("$.[*].rating").value(hasItem(DEFAULT_RATING.doubleValue())))
            .andExpect(jsonPath("$.[*].rank").value(hasItem(DEFAULT_RANK.intValue())))
            .andExpect(jsonPath("$.[*].averageweight").value(hasItem(DEFAULT_AVERAGEWEIGHT.doubleValue())))
            .andExpect(jsonPath("$.[*].category").value(hasItem(DEFAULT_CATEGORY)));
    }

    @Test
    @Transactional
    void getBoardgames() throws Exception {
        // Initialize the database
        boardgamesRepository.saveAndFlush(boardgames);

        // Get the boardgames
        restBoardgamesMockMvc
            .perform(get(ENTITY_API_URL_ID, boardgames.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(boardgames.getId().intValue()))
            .andExpect(jsonPath("$.thumbnail_url").value(DEFAULT_THUMBNAIL_URL))
            .andExpect(jsonPath("$.primary_name").value(DEFAULT_PRIMARY_NAME))
            .andExpect(jsonPath("$.minplayers").value(DEFAULT_MINPLAYERS))
            .andExpect(jsonPath("$.maxplayers").value(DEFAULT_MAXPLAYERS))
            .andExpect(jsonPath("$.suggested_numplayers").value(DEFAULT_SUGGESTED_NUMPLAYERS))
            .andExpect(jsonPath("$.playingtime").value(DEFAULT_PLAYINGTIME))
            .andExpect(jsonPath("$.suggested_playerage").value(DEFAULT_SUGGESTED_PLAYERAGE))
            .andExpect(jsonPath("$.rating").value(DEFAULT_RATING.doubleValue()))
            .andExpect(jsonPath("$.rank").value(DEFAULT_RANK.intValue()))
            .andExpect(jsonPath("$.averageweight").value(DEFAULT_AVERAGEWEIGHT.doubleValue()))
            .andExpect(jsonPath("$.category").value(DEFAULT_CATEGORY));
    }

    @Test
    @Transactional
    void getNonExistingBoardgames() throws Exception {
        // Get the boardgames
        restBoardgamesMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewBoardgames() throws Exception {
        // Initialize the database
        boardgamesRepository.saveAndFlush(boardgames);

        int databaseSizeBeforeUpdate = boardgamesRepository.findAll().size();

        // Update the boardgames
        Boardgames updatedBoardgames = boardgamesRepository.findById(boardgames.getId()).get();
        // Disconnect from session so that the updates on updatedBoardgames are not directly saved in db
        em.detach(updatedBoardgames);
        updatedBoardgames
            .thumbnail_url(UPDATED_THUMBNAIL_URL)
            .primary_name(UPDATED_PRIMARY_NAME)
            .minplayers(UPDATED_MINPLAYERS)
            .maxplayers(UPDATED_MAXPLAYERS)
            .suggested_numplayers(UPDATED_SUGGESTED_NUMPLAYERS)
            .playingtime(UPDATED_PLAYINGTIME)
            .suggested_playerage(UPDATED_SUGGESTED_PLAYERAGE)
            .rating(UPDATED_RATING)
            .rank(UPDATED_RANK)
            .averageweight(UPDATED_AVERAGEWEIGHT)
            .category(UPDATED_CATEGORY);

        restBoardgamesMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedBoardgames.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedBoardgames))
            )
            .andExpect(status().isOk());

        // Validate the Boardgames in the database
        List<Boardgames> boardgamesList = boardgamesRepository.findAll();
        assertThat(boardgamesList).hasSize(databaseSizeBeforeUpdate);
        Boardgames testBoardgames = boardgamesList.get(boardgamesList.size() - 1);
        assertThat(testBoardgames.getThumbnail_url()).isEqualTo(UPDATED_THUMBNAIL_URL);
        assertThat(testBoardgames.getPrimary_name()).isEqualTo(UPDATED_PRIMARY_NAME);
        assertThat(testBoardgames.getMinplayers()).isEqualTo(UPDATED_MINPLAYERS);
        assertThat(testBoardgames.getMaxplayers()).isEqualTo(UPDATED_MAXPLAYERS);
        assertThat(testBoardgames.getSuggested_numplayers()).isEqualTo(UPDATED_SUGGESTED_NUMPLAYERS);
        assertThat(testBoardgames.getPlayingtime()).isEqualTo(UPDATED_PLAYINGTIME);
        assertThat(testBoardgames.getSuggested_playerage()).isEqualTo(UPDATED_SUGGESTED_PLAYERAGE);
        assertThat(testBoardgames.getRating()).isEqualTo(UPDATED_RATING);
        assertThat(testBoardgames.getRank()).isEqualTo(UPDATED_RANK);
        assertThat(testBoardgames.getAverageweight()).isEqualTo(UPDATED_AVERAGEWEIGHT);
        assertThat(testBoardgames.getCategory()).isEqualTo(UPDATED_CATEGORY);
    }

    @Test
    @Transactional
    void putNonExistingBoardgames() throws Exception {
        int databaseSizeBeforeUpdate = boardgamesRepository.findAll().size();
        boardgames.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBoardgamesMockMvc
            .perform(
                put(ENTITY_API_URL_ID, boardgames.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(boardgames))
            )
            .andExpect(status().isBadRequest());

        // Validate the Boardgames in the database
        List<Boardgames> boardgamesList = boardgamesRepository.findAll();
        assertThat(boardgamesList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchBoardgames() throws Exception {
        int databaseSizeBeforeUpdate = boardgamesRepository.findAll().size();
        boardgames.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restBoardgamesMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(boardgames))
            )
            .andExpect(status().isBadRequest());

        // Validate the Boardgames in the database
        List<Boardgames> boardgamesList = boardgamesRepository.findAll();
        assertThat(boardgamesList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamBoardgames() throws Exception {
        int databaseSizeBeforeUpdate = boardgamesRepository.findAll().size();
        boardgames.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restBoardgamesMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(boardgames)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Boardgames in the database
        List<Boardgames> boardgamesList = boardgamesRepository.findAll();
        assertThat(boardgamesList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateBoardgamesWithPatch() throws Exception {
        // Initialize the database
        boardgamesRepository.saveAndFlush(boardgames);

        int databaseSizeBeforeUpdate = boardgamesRepository.findAll().size();

        // Update the boardgames using partial update
        Boardgames partialUpdatedBoardgames = new Boardgames();
        partialUpdatedBoardgames.setId(boardgames.getId());

        partialUpdatedBoardgames
            .thumbnail_url(UPDATED_THUMBNAIL_URL)
            .primary_name(UPDATED_PRIMARY_NAME)
            .minplayers(UPDATED_MINPLAYERS)
            .playingtime(UPDATED_PLAYINGTIME)
            .suggested_playerage(UPDATED_SUGGESTED_PLAYERAGE)
            .rating(UPDATED_RATING)
            .averageweight(UPDATED_AVERAGEWEIGHT)
            .category(UPDATED_CATEGORY);

        restBoardgamesMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedBoardgames.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedBoardgames))
            )
            .andExpect(status().isOk());

        // Validate the Boardgames in the database
        List<Boardgames> boardgamesList = boardgamesRepository.findAll();
        assertThat(boardgamesList).hasSize(databaseSizeBeforeUpdate);
        Boardgames testBoardgames = boardgamesList.get(boardgamesList.size() - 1);
        assertThat(testBoardgames.getThumbnail_url()).isEqualTo(UPDATED_THUMBNAIL_URL);
        assertThat(testBoardgames.getPrimary_name()).isEqualTo(UPDATED_PRIMARY_NAME);
        assertThat(testBoardgames.getMinplayers()).isEqualTo(UPDATED_MINPLAYERS);
        assertThat(testBoardgames.getMaxplayers()).isEqualTo(DEFAULT_MAXPLAYERS);
        assertThat(testBoardgames.getSuggested_numplayers()).isEqualTo(DEFAULT_SUGGESTED_NUMPLAYERS);
        assertThat(testBoardgames.getPlayingtime()).isEqualTo(UPDATED_PLAYINGTIME);
        assertThat(testBoardgames.getSuggested_playerage()).isEqualTo(UPDATED_SUGGESTED_PLAYERAGE);
        assertThat(testBoardgames.getRating()).isEqualTo(UPDATED_RATING);
        assertThat(testBoardgames.getRank()).isEqualTo(DEFAULT_RANK);
        assertThat(testBoardgames.getAverageweight()).isEqualTo(UPDATED_AVERAGEWEIGHT);
        assertThat(testBoardgames.getCategory()).isEqualTo(UPDATED_CATEGORY);
    }

    @Test
    @Transactional
    void fullUpdateBoardgamesWithPatch() throws Exception {
        // Initialize the database
        boardgamesRepository.saveAndFlush(boardgames);

        int databaseSizeBeforeUpdate = boardgamesRepository.findAll().size();

        // Update the boardgames using partial update
        Boardgames partialUpdatedBoardgames = new Boardgames();
        partialUpdatedBoardgames.setId(boardgames.getId());

        partialUpdatedBoardgames
            .thumbnail_url(UPDATED_THUMBNAIL_URL)
            .primary_name(UPDATED_PRIMARY_NAME)
            .minplayers(UPDATED_MINPLAYERS)
            .maxplayers(UPDATED_MAXPLAYERS)
            .suggested_numplayers(UPDATED_SUGGESTED_NUMPLAYERS)
            .playingtime(UPDATED_PLAYINGTIME)
            .suggested_playerage(UPDATED_SUGGESTED_PLAYERAGE)
            .rating(UPDATED_RATING)
            .rank(UPDATED_RANK)
            .averageweight(UPDATED_AVERAGEWEIGHT)
            .category(UPDATED_CATEGORY);

        restBoardgamesMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedBoardgames.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedBoardgames))
            )
            .andExpect(status().isOk());

        // Validate the Boardgames in the database
        List<Boardgames> boardgamesList = boardgamesRepository.findAll();
        assertThat(boardgamesList).hasSize(databaseSizeBeforeUpdate);
        Boardgames testBoardgames = boardgamesList.get(boardgamesList.size() - 1);
        assertThat(testBoardgames.getThumbnail_url()).isEqualTo(UPDATED_THUMBNAIL_URL);
        assertThat(testBoardgames.getPrimary_name()).isEqualTo(UPDATED_PRIMARY_NAME);
        assertThat(testBoardgames.getMinplayers()).isEqualTo(UPDATED_MINPLAYERS);
        assertThat(testBoardgames.getMaxplayers()).isEqualTo(UPDATED_MAXPLAYERS);
        assertThat(testBoardgames.getSuggested_numplayers()).isEqualTo(UPDATED_SUGGESTED_NUMPLAYERS);
        assertThat(testBoardgames.getPlayingtime()).isEqualTo(UPDATED_PLAYINGTIME);
        assertThat(testBoardgames.getSuggested_playerage()).isEqualTo(UPDATED_SUGGESTED_PLAYERAGE);
        assertThat(testBoardgames.getRating()).isEqualTo(UPDATED_RATING);
        assertThat(testBoardgames.getRank()).isEqualTo(UPDATED_RANK);
        assertThat(testBoardgames.getAverageweight()).isEqualTo(UPDATED_AVERAGEWEIGHT);
        assertThat(testBoardgames.getCategory()).isEqualTo(UPDATED_CATEGORY);
    }

    @Test
    @Transactional
    void patchNonExistingBoardgames() throws Exception {
        int databaseSizeBeforeUpdate = boardgamesRepository.findAll().size();
        boardgames.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBoardgamesMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, boardgames.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(boardgames))
            )
            .andExpect(status().isBadRequest());

        // Validate the Boardgames in the database
        List<Boardgames> boardgamesList = boardgamesRepository.findAll();
        assertThat(boardgamesList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchBoardgames() throws Exception {
        int databaseSizeBeforeUpdate = boardgamesRepository.findAll().size();
        boardgames.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restBoardgamesMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(boardgames))
            )
            .andExpect(status().isBadRequest());

        // Validate the Boardgames in the database
        List<Boardgames> boardgamesList = boardgamesRepository.findAll();
        assertThat(boardgamesList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamBoardgames() throws Exception {
        int databaseSizeBeforeUpdate = boardgamesRepository.findAll().size();
        boardgames.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restBoardgamesMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(boardgames))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the Boardgames in the database
        List<Boardgames> boardgamesList = boardgamesRepository.findAll();
        assertThat(boardgamesList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteBoardgames() throws Exception {
        // Initialize the database
        boardgamesRepository.saveAndFlush(boardgames);

        int databaseSizeBeforeDelete = boardgamesRepository.findAll().size();

        // Delete the boardgames
        restBoardgamesMockMvc
            .perform(delete(ENTITY_API_URL_ID, boardgames.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Boardgames> boardgamesList = boardgamesRepository.findAll();
        assertThat(boardgamesList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
