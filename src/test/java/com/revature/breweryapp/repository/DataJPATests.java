package com.revature.breweryapp.repository;

import com.revature.breweryapp.model.Beer;
import com.revature.breweryapp.model.BeerStyle;
import com.revature.breweryapp.model.Brewery;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class DataJPATests {

    @Autowired
    BeerRepository beerRepository;

    @Test
    public void test() {
        List<Beer> allBeers = beerRepository.findAll();

        assertEquals(13, allBeers.size());

    }

    @Test
    public void addBeerTest() {
        Beer newBeer = new Beer();
        newBeer.setId(1);
        newBeer.setName("Test Beer");
        newBeer.setStyle(BeerStyle.ALE);
        newBeer.setDescription("This is a test!");
        newBeer.setAbv(5.5f);
        newBeer.setBrewery(new Brewery());

        beerRepository.save(newBeer);

        Beer b = beerRepository.findById(1).orElseGet(Beer::new);

        assertEquals(1, b.getId());
    }
}
