package com.mycompany.myapp.web.rest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.mycompany.myapp.IntegrationTest;
import com.mycompany.myapp.web.rest.BggXmlApiResource;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

/**
 * Test class for the BggXmlApiResource REST controller.
 *
 * @see BggXmlApiResource
 */
@IntegrationTest
class BggXmlApiResourceIT {

    private MockMvc restMockMvc;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        BggXmlApiResource bggXmlApiResource = new BggXmlApiResource();
        restMockMvc = MockMvcBuilders.standaloneSetup(bggXmlApiResource).build();
    }

    /**
     * Test getThingsById
     */
    @Test
    void testGetThingsById() throws Exception {
        restMockMvc.perform(get("/api/bgg-xml-api/get-things-by-id")).andExpect(status().isOk());
    }
}
