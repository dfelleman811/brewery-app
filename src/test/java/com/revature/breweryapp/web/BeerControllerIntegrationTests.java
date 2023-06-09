package com.revature.breweryapp.web;

import com.revature.breweryapp.model.Beer;
import com.revature.breweryapp.repository.BeerRepository;
import com.revature.breweryapp.service.BeerService;
import com.revature.breweryapp.service.BeerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringJUnitWebConfig(classes = {BeerControllerIntegrationTests.TestConfig.class})
public class BeerControllerIntegrationTests {
    @Configuration
    @ComponentScan("com.revature.breweryapp")
    static class TestConfig {}

    @Autowired
    private BeerController beerController;


    MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(beerController).build();
    }

    @Test
    public void shouldGetAllBeer() throws Exception {
        mockMvc.perform(get("/beer"))
                .andExpect(status().isOk());
    }


}
