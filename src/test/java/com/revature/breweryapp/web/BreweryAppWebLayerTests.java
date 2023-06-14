package com.revature.breweryapp.web;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringJUnitWebConfig(BreweryAppWebLayerTests.TestConfig.class)
public class BreweryAppWebLayerTests {
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
    public void test() throws Exception {
        MvcResult result =  this.mockMvc.perform(get("/beer"))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();

        System.out.println(result.getResponse().getContentAsString());
    }

    @Test
    public void singleId() throws Exception {
        MvcResult result = this.mockMvc.perform(get("/beer/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        System.out.println(result.getResponse().getContentAsString());
    }
}
