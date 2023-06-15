package com.revature.breweryapp.web;

import com.revature.breweryapp.model.Beer;
import com.revature.breweryapp.model.BeerStyle;
import com.revature.breweryapp.model.Brewery;
import com.revature.breweryapp.repository.BeerRepository;
import com.revature.breweryapp.repository.BreweryRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringJUnitWebConfig(BreweryAppWebLayerTests.TestConfig.class)
public class BreweryAppWebLayerTests {
    @Configuration
    @ComponentScan("com.revature.breweryapp")
    static class TestConfig {

//        @Bean
//        public CommandLineRunner commandLineRunner() {
//            return (args -> {
//                System.out.println("Hello");
//            });
//        }
    }

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Autowired
    private BeerController beerController;
@Autowired
private BreweryRepository breweryRepository;
    @BeforeEach
    public void setUp() {
        Brewery brewery = new Brewery();
        breweryRepository.save(brewery);
        Beer b = new Beer(1, "test", "test", BeerStyle.STOUT, 5.5f, brewery);

        beerController.addBeer(b);
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }


    @Test
    public void test() throws Exception {
        MvcResult result =  this.mockMvc.perform(get("/beer"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$[0].id").value(1))
                .andReturn();

        System.out.println(result.getResponse().getContentAsString());
    }

    @Test
    public void singleId() throws Exception {
        MvcResult result = this.mockMvc.perform(get("/beer/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andReturn();

        System.out.println(result.getResponse().getContentAsString());
    }
}
