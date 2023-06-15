package com.revature.breweryapp.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.breweryapp.model.Beer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "classpath:test-config.properties")
@AutoConfigureMockMvc
public class BeerControllerFullIntegrationMockDataTests {

    @Autowired
    private MockMvc mockMvc;

    @Value("${data.test}")
    private String testString;

    @Test
    public void test() {
        System.out.println(testString);
    }

    @Test
    public void testDataWorks() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/beer");
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andDo(print()).andReturn();
        String responseString = mvcResult.getResponse().getContentAsString();
        List<Beer> beers = new ObjectMapper().readValue(responseString, List.class);
        assertEquals(3, beers.size());
    }
}
