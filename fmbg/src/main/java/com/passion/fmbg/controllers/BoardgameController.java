package com.passion.fmbg.controllers;

import com.passion.fmbg.entities.Boardgames;
import com.passion.fmbg.services.BoardgameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BoardgameController {
    private BoardgameService service;

    @Autowired
    public BoardgameController(BoardgameService service) {
        this.service = service;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<Boardgames> create(@RequestBody Boardgames bg) {
        return new ResponseEntity<>(service.create(bg), HttpStatus.CREATED);
    }


}
