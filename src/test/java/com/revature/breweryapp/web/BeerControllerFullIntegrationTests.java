package com.revature.breweryapp.web;

import com.revature.breweryapp.model.Beer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT) // MOCK web environment is default - which mocks the servlet environment - not the entire spring app context loaded
@AutoConfigureMockMvc
public class BeerControllerFullIntegrationTests {

    @Autowired
    private MockMvc mockMvc;
//autoconfigure mockmvc annotation basically does the below for us - it autoconfigures the mock mvc entity
//    @Autowired
//    private WebApplicationContext webApplicationContext;
//
//    @BeforeEach
//    public void setUp() {
//        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
//    }
    @Test
    public void givenNoParams_whenGetAll_shouldReturnAllBeer() throws Exception {
      this.mockMvc.perform(get("/beer"))
              .andDo(print())
              .andExpect(status().isOk())
              .andExpect(jsonPath("$").isArray())
              .andExpect(jsonPath("$").isNotEmpty());
    }
}
