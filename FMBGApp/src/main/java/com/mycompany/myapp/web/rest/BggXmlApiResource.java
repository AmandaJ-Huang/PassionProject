package com.mycompany.myapp.web.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * BggXmlApiResource controller
 */
@RestController
@RequestMapping("/api/bgg-xml-api")
public class BggXmlApiResource {

    private final Logger log = LoggerFactory.getLogger(BggXmlApiResource.class);

    /**
     * GET getThingsById
     */
    @GetMapping("/get-things-by-id")
    public String getThingsById() {
        return "getThingsById";
    }
}
