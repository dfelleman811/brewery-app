package com.revature.breweryapp.web;



import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.breweryapp.model.Beer;
import com.revature.breweryapp.model.BeerStyle;
import com.revature.breweryapp.service.BeerService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(BeerController.class)
public class BeerControllerIntegrationMockMVCTests_temp {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BeerService beerService;

    @Test
    public void getAllTest() throws Exception {
        mockMvc.perform(get("/beer")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andDo(print());
    }

    @Test
    public void postTest() throws Exception {
        Beer b = new Beer();
        b.setName("test beer");
        b.setDescription("just for testing");
        b.setAbv(5.5f);
        b.setStyle(BeerStyle.ALE);

        Beer beerToReturn = new Beer();
        beerToReturn.setId(1);
        beerToReturn.setName("test beer");
        beerToReturn.setDescription("just for testing");
        beerToReturn.setAbv(5.5f);
        beerToReturn.setStyle(BeerStyle.ALE);


        given(beerService.addBeer(any(Beer.class))).willReturn(beerToReturn);

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/beer")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(toJson(b));

       MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
       String resBodyAsString = mvcResult.getResponse().getContentAsString();
       Beer createdBeer = new ObjectMapper().readValue(resBodyAsString, Beer.class);

        System.out.println(createdBeer);
        assertNotNull(createdBeer);
        assertTrue(createdBeer.getId() > 0);



    }

    public String toJson(Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }

}
