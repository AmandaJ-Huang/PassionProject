package com.passion.fmbg.controllers;

import com.passion.fmbg.entities.Games;
import com.passion.fmbg.services.BoardgameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class BoardgameController {
    private BoardgameService service;

    @Autowired
    public BoardgameController(BoardgameService service) {
        this.service = service;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<Games> create(@RequestBody Games bg) {
        return new ResponseEntity<>(service.create(bg), HttpStatus.CREATED);
    }

    @GetMapping(value = "/api/boardgames", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Games getAllBoardgames() {
        String url = "https://api.boardgameatlas.com/api/search?ids=TAAifFP590&client_id=a5PDFkKaa5";
        RestTemplate restTemplate = new RestTemplate();

        return restTemplate.getForObject(url, Games.class);
    }


}
