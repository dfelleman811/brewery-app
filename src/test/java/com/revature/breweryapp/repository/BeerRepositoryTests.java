package com.revature.breweryapp.repository;

import com.revature.breweryapp.model.Beer;
import com.revature.breweryapp.model.BeerStyle;
import com.revature.breweryapp.model.Brewery;
import org.hamcrest.core.Every;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

// we only need to load the repository/persistence components - not concerned with service or controller
// by NOT loading full application context (like with @SpringBootTest) we save time and make our tests more isolated and efficient
// by default @DataJpaTest scans for @Entity and @Repository stereotype annotations and ignores @component, @service, @controller, etc.

@DataJpaTest // this annotation autoconfigures an in memory database for testing | by default this annotation makes tests transactional and rollback at the end of each test
public class BeerRepositoryTests {

    @Autowired
    BeerRepository beerRepository;

    @Test
    public void givenData_whenFindAll_shouldReturnAllBeer() {
        List<Beer> allBeers = beerRepository.findAll();
        System.out.println(allBeers);
        assertEquals(13, allBeers.size()); // data.sql is being added to H2 on startup - see src/main/resources/db...

    }

    @Test
    public void givenValidBeer_whenSaveBeer_shouldAddBeer() {
        Beer newBeer = new Beer();
        newBeer.setName("Test Beer");
        newBeer.setStyle(BeerStyle.ALE);
        newBeer.setDescription("This is a test!");
        newBeer.setAbv(5.5f);
        newBeer.setBrewery(new Brewery());

        Beer savedBeer = beerRepository.save(newBeer);

       assertNotNull(savedBeer);
       assertTrue(savedBeer.getId() > 0);
    }

    @Test
    public void givenValidId_whenGetById_shouldReturnBeer() {

        Beer beer = beerRepository.findById(1).get();

        assertNotNull(beer);
        assertEquals(1, beer.getId());
    }
    @Test
    public void givenBeerData_whenUpdateBeer_shouldReturnUpdatedBeer() {
        Beer beer = new Beer();
        beer.setName("Test Beer");
        beer.setDescription("a beer for traste testing");
        beer.setStyle(BeerStyle.ALE);
        beerRepository.save(beer);

        Beer savedBeer = beerRepository.findById(beer.getId()).get();
        savedBeer.setDescription("TBD");
        savedBeer.setStyle(BeerStyle.STOUT);
        Beer updatedBeer = beerRepository.save(savedBeer);

        assertEquals("TBD", updatedBeer.getDescription());
        assertEquals(BeerStyle.STOUT, updatedBeer.getStyle());


    }
@Test
    public void givenBeerData_whenDeleteBeer_shouldRemoveFromDb() {
        Beer b = new Beer();
        b.setName("delete me!");

        Beer savedBeer = beerRepository.save(b);

        int id = savedBeer.getId();

        beerRepository.delete(savedBeer);

        Optional<Beer> afterDelete = beerRepository.findById(id);

        assertTrue(afterDelete.isEmpty());
    }

    @ParameterizedTest
    @EnumSource(BeerStyle.class)
    public void givenValidStyle_whenGetBeerByStyle_shouldReturnBeer(BeerStyle style) {
        List<Beer> beerList = beerRepository.findAllByStyle(style);
        System.out.println(beerList);

        assertThat(beerList, Every.everyItem(hasProperty("style", is(style))));
    }

    @Test
    public void givenBeerData_whenGetSorted_shouldReturnSortedAsc() {
        List<Beer> actualBeers = beerRepository.findAllByOrderByAbvAsc();
        System.out.println(actualBeers);

        // no matcher really exists to check so we're making a sorted list to compare against.
        // probly not the best to be using the repo to test the repo but...
        List<Beer> beers = beerRepository.findAll();
        beers.sort(Comparator.comparing(Beer::getAbv));

        assertEquals(beers, actualBeers);

    }

    @Test
    public void givenBeerData_whenGetSorted_shouldReturnSortedDesc() {
        List<Beer> actualBeers = beerRepository.findAllByOrderByAbvDesc();
        System.out.println(actualBeers);

        // no matcher really exists to check so we're making a sorted list to compare against.
        // probly not the best to be using the repo to test the repo but...
        List<Beer> beers = beerRepository.findAll();
        beers.sort(Comparator.comparing(Beer::getAbv, Comparator.reverseOrder()));

        assertEquals(beers, actualBeers);

    }


//    public void givenValidBrewery_whenGetByBrewery_shouldReturnAllBeer() {
//        List<Beer> beers = beerRepository.findAllByBrewery();
//    }
}
