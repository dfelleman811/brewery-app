package com.revature.breweryapp.web;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringJUnitWebConfig(BeerControllerIntegrationMockMVCTests_temp.TestConfig.class)
public class BeerControllerIntegrationMockMVCTests_temp {
    @Configuration
    @ComponentScan("com.revature.breweryapp")
    static class TestConfig {}

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void greetController_shouldReturnHello() throws Exception {
        this.mockMvc.perform(get("/")).andExpect(status().isOk()).andExpect(jsonPath("$").isString()).andDo(print());
    }

    @Test
    public void beerController_whenGetAll_shouldReturnAllBeer() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(get("/beer"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andDo(print())
                .andReturn();

        String response = mvcResult.getResponse().getContentAsString();

        System.out.println("Response" + response);

    }
}
