package com.revature.breweryapp.service;

import com.revature.breweryapp.model.Brewery;
import com.revature.breweryapp.repository.BreweryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BreweryServiceImpl implements BreweryService {


    private BreweryRepository breweryRepository;

    public BreweryServiceImpl(BreweryRepository breweryRepository) {
        this.breweryRepository = breweryRepository;
    }
    @Override
    public List<Brewery> getAllBreweries() {
        return breweryRepository.findAll();
    }
}
