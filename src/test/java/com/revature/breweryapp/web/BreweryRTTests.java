package com.revature.breweryapp.web;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.breweryapp.model.Beer;
import com.revature.breweryapp.model.Brewery;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;


@RestClientTest(BreweryRT.class)
public class BreweryRTTests {

    private MockRestServiceServer mockServer;

    @Autowired
    private BreweryRT breweryRT;

    @Autowired
    RestTemplate restTemplate;


    @BeforeEach
    public void setUp() {
        mockServer = MockRestServiceServer.createServer(restTemplate);
    }


    @Test
    public void givenMockServer_whenGetRequest_shouldReturnAllBreweriesList() {
        mockServer.expect(requestTo("/brewery"))
                .andRespond(withSuccess(allBreweriesSuccessBody(), MediaType.APPLICATION_JSON));

        List<Brewery> breweries = breweryRT.getAllBreweries();

        assertEquals(3, breweries.size());
    }

    @Test
    public void givenMockServer_whenGetRequest_shouldReturnAllBreweriesJson() {
            mockServer.expect(requestTo("/brewery"))
                    .andRespond(withSuccess(allBreweriesSuccessBody(), MediaType.APPLICATION_JSON));

        ResponseEntity<List> allBreweries = breweryRT.getAllBreweriesJson();

        assertEquals(3, allBreweries.getBody().size());
    }

    @Test
    public void givenMockServer_whenGetRequest_shouldReturnBreweryByIdJson() {
        mockServer.expect(requestTo("/brewery/1"))
                .andRespond(withSuccess(breweryByIdSuccessBody(1), MediaType.APPLICATION_JSON));

        ResponseEntity<Brewery> breweryRE = breweryRT.getBreweryByIdJson(1);

        assertEquals(1, breweryRE.getBody().getId());
    }


    // helper methods to mock response bodies as json strings

    public String allBreweriesSuccessBody() {
        try {
            return new ObjectMapper().writeValueAsString(List.of(
                    new Brewery(1, "Test Hops", "St. Paul", "MN"),
                    new Brewery(2, "Bad Brews", "Johnston", "LA"),
                    new Brewery(3, "Hi-Chops", "Chicago", "IL")
            ));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public String breweryByIdSuccessBody(int id)  {
        try {
            return new ObjectMapper().writeValueAsString(new Brewery(id, "test brews", "FakePlace", "KD"));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}
