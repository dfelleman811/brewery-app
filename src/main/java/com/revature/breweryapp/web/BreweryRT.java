package com.revature.breweryapp.web;


import com.revature.breweryapp.model.Brewery;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class BreweryRT {

    private final RestTemplate restTemplate;

    public BreweryRT(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ResponseEntity<List> getAllBreweries() {
        return restTemplate.getForEntity("/brewery", List.class);
    }

    public ResponseEntity<Brewery> getBreweryById(int id) {
        String url = "/brewery/" + id;
        return restTemplate.getForEntity(url, Brewery.class);
    }
}
