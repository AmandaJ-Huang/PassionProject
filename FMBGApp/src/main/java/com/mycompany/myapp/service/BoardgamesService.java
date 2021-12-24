package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.Boardgames;
import com.mycompany.myapp.repository.BoardgamesRepository;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Boardgames}.
 */
@Service
@Transactional
public class BoardgamesService {

    private final Logger log = LoggerFactory.getLogger(BoardgamesService.class);

    private final BoardgamesRepository boardgamesRepository;

    public BoardgamesService(BoardgamesRepository boardgamesRepository) {
        this.boardgamesRepository = boardgamesRepository;
    }

    /**
     * Save a boardgames.
     *
     * @param boardgames the entity to save.
     * @return the persisted entity.
     */
    public Boardgames save(Boardgames boardgames) {
        log.debug("Request to save Boardgames : {}", boardgames);
        return boardgamesRepository.save(boardgames);
    }

    /**
     * Partially update a boardgames.
     *
     * @param boardgames the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<Boardgames> partialUpdate(Boardgames boardgames) {
        log.debug("Request to partially update Boardgames : {}", boardgames);

        return boardgamesRepository
            .findById(boardgames.getId())
            .map(existingBoardgames -> {
                if (boardgames.getThumbnail_url() != null) {
                    existingBoardgames.setThumbnail_url(boardgames.getThumbnail_url());
                }
                if (boardgames.getPrimary_name() != null) {
                    existingBoardgames.setPrimary_name(boardgames.getPrimary_name());
                }
                if (boardgames.getMinplayers() != null) {
                    existingBoardgames.setMinplayers(boardgames.getMinplayers());
                }
                if (boardgames.getMaxplayers() != null) {
                    existingBoardgames.setMaxplayers(boardgames.getMaxplayers());
                }
                if (boardgames.getSuggested_numplayers() != null) {
                    existingBoardgames.setSuggested_numplayers(boardgames.getSuggested_numplayers());
                }
                if (boardgames.getPlayingtime() != null) {
                    existingBoardgames.setPlayingtime(boardgames.getPlayingtime());
                }
                if (boardgames.getSuggested_playerage() != null) {
                    existingBoardgames.setSuggested_playerage(boardgames.getSuggested_playerage());
                }
                if (boardgames.getRating() != null) {
                    existingBoardgames.setRating(boardgames.getRating());
                }
                if (boardgames.getRank() != null) {
                    existingBoardgames.setRank(boardgames.getRank());
                }
                if (boardgames.getAverageweight() != null) {
                    existingBoardgames.setAverageweight(boardgames.getAverageweight());
                }
                if (boardgames.getCategory() != null) {
                    existingBoardgames.setCategory(boardgames.getCategory());
                }

                return existingBoardgames;
            })
            .map(boardgamesRepository::save);
    }

    /**
     * Get all the boardgames.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<Boardgames> findAll(Pageable pageable) {
        log.debug("Request to get all Boardgames");
        return boardgamesRepository.findAll(pageable);
    }

    /**
     * Get one boardgames by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<Boardgames> findOne(Long id) {
        log.debug("Request to get Boardgames : {}", id);
        return boardgamesRepository.findById(id);
    }

    /**
     * Delete the boardgames by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Boardgames : {}", id);
        boardgamesRepository.deleteById(id);
    }
}
