package com.revature.breweryapp.web;

import com.revature.breweryapp.model.Beer;
import com.revature.breweryapp.model.BeerStyle;
import com.revature.breweryapp.model.Brewery;
import com.revature.breweryapp.service.BeerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringJUnitWebConfig(classes = {BeerControllerIntegrationMockMVCTests.TestConfig.class})
public class BeerControllerIntegrationMockMVCTests {
    @Configuration
    @ComponentScan("com.revature.breweryapp")
    static class TestConfig {}

    @Autowired
    private BeerController beerController;

    @MockBean
    private BeerService beerService;

    // using MockMVC allows us to fake the actual server - and isolate the controller's behavior
    // still integration testing because it's fully using the beans for service and repos
    MockMvc mockMvc;

    List<Beer> beers = List.of(
            new Beer(1, "beer", "test beer", BeerStyle.LAGER, 5.5f, new Brewery()),
            new Beer(2, "beers", "test dbeer", BeerStyle.PALEALE, 6.5f, new Brewery()),
            new Beer(3, "beerx", "testyt beer", BeerStyle.ALE, 8.5f, new Brewery())
    );

    private final Integer TEST_BEER_ID = 1;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(beerController).build();

    }

    @Test
    public void shouldGetAllBeer() throws Exception {

        given(this.beerService.getAllBeer()).willReturn(beers);

        mockMvc.perform(get("/beer")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andDo(print());
    }

    @Test
    public void shouldGetBeerById() throws Exception {

        given(this.beerService.findById(TEST_BEER_ID)).willReturn(new Beer(1, "test beer", "testy brews", BeerStyle.IPA, 4.4f, new Brewery()));

        mockMvc.perform(get("/beer/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andDo(print());
    }


    @Test
    public void givenValidRequest_whenPostNewBeer_shouldCreateNewBeer() {

    }

}
