package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.Boardgames;
import com.mycompany.myapp.repository.BoardgamesRepository;
import com.mycompany.myapp.service.BoardgamesService;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.mycompany.myapp.domain.Boardgames}.
 */
@RestController
@RequestMapping("/api")
public class BoardgamesResource {

    private final Logger log = LoggerFactory.getLogger(BoardgamesResource.class);

    private static final String ENTITY_NAME = "boardgames";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final BoardgamesService boardgamesService;

    private final BoardgamesRepository boardgamesRepository;

    public BoardgamesResource(BoardgamesService boardgamesService, BoardgamesRepository boardgamesRepository) {
        this.boardgamesService = boardgamesService;
        this.boardgamesRepository = boardgamesRepository;
    }

    /**
     * {@code POST  /boardgames} : Create a new boardgames.
     *
     * @param boardgames the boardgames to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new boardgames, or with status {@code 400 (Bad Request)} if the boardgames has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/boardgames")
    public ResponseEntity<Boardgames> createBoardgames(@RequestBody Boardgames boardgames) throws URISyntaxException {
        log.debug("REST request to save Boardgames : {}", boardgames);
        if (boardgames.getId() != null) {
            throw new BadRequestAlertException("A new boardgames cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Boardgames result = boardgamesService.save(boardgames);
        return ResponseEntity
            .created(new URI("/api/boardgames/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /boardgames/:id} : Updates an existing boardgames.
     *
     * @param id the id of the boardgames to save.
     * @param boardgames the boardgames to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated boardgames,
     * or with status {@code 400 (Bad Request)} if the boardgames is not valid,
     * or with status {@code 500 (Internal Server Error)} if the boardgames couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/boardgames/{id}")
    public ResponseEntity<Boardgames> updateBoardgames(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody Boardgames boardgames
    ) throws URISyntaxException {
        log.debug("REST request to update Boardgames : {}, {}", id, boardgames);
        if (boardgames.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, boardgames.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!boardgamesRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Boardgames result = boardgamesService.save(boardgames);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, boardgames.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /boardgames/:id} : Partial updates given fields of an existing boardgames, field will ignore if it is null
     *
     * @param id the id of the boardgames to save.
     * @param boardgames the boardgames to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated boardgames,
     * or with status {@code 400 (Bad Request)} if the boardgames is not valid,
     * or with status {@code 404 (Not Found)} if the boardgames is not found,
     * or with status {@code 500 (Internal Server Error)} if the boardgames couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/boardgames/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Boardgames> partialUpdateBoardgames(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody Boardgames boardgames
    ) throws URISyntaxException {
        log.debug("REST request to partial update Boardgames partially : {}, {}", id, boardgames);
        if (boardgames.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, boardgames.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!boardgamesRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Boardgames> result = boardgamesService.partialUpdate(boardgames);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, boardgames.getId().toString())
        );
    }

    /**
     * {@code GET  /boardgames} : get all the boardgames.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of boardgames in body.
     */
    @GetMapping("/boardgames")
    public ResponseEntity<List<Boardgames>> getAllBoardgames(Pageable pageable) {
        log.debug("REST request to get a page of Boardgames");
        Page<Boardgames> page = boardgamesService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /boardgames/:id} : get the "id" boardgames.
     *
     * @param id the id of the boardgames to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the boardgames, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/boardgames/{id}")
    public ResponseEntity<Boardgames> getBoardgames(@PathVariable Long id) {
        log.debug("REST request to get Boardgames : {}", id);
        Optional<Boardgames> boardgames = boardgamesService.findOne(id);
        return ResponseUtil.wrapOrNotFound(boardgames);
    }

    /**
     * {@code DELETE  /boardgames/:id} : delete the "id" boardgames.
     *
     * @param id the id of the boardgames to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/boardgames/{id}")
    public ResponseEntity<Void> deleteBoardgames(@PathVariable Long id) {
        log.debug("REST request to delete Boardgames : {}", id);
        boardgamesService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
