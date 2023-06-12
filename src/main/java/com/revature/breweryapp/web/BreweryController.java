package com.revature.breweryapp.web;

import com.revature.breweryapp.model.Brewery;
import com.revature.breweryapp.service.BreweryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/brewery")
public class BreweryController {

    private BreweryService breweryService;

    public BreweryController(BreweryService breweryService) {
        this.breweryService = breweryService;
    }

    @GetMapping()
    public List<Brewery> getAllBreweries() {
        return this.breweryService.getAllBreweries();
    }


}
