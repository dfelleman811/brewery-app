package com.revature.breweryapp.web;

import com.revature.breweryapp.model.Beer;
import com.revature.breweryapp.model.BeerStyle;
import com.revature.breweryapp.model.Brewery;
import com.revature.breweryapp.service.BeerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
public class BeerControllerUnitTests {

    @Mock
    BeerService beerService;

    @InjectMocks
    BeerController beerController;

    List<Beer> beerList = new ArrayList<>();

    @BeforeEach
    public void setUp() {

        beerList.add(new Beer());
        beerList.add(new Beer());


    }

    @Test
    public void shouldReturnAllBeer() {
        given(beerService.getAllBeer()).willReturn(beerList);
        List<Beer> returnedList = beerController.getAllBeer(null, null);
        then(beerService).should().getAllBeer();
        assertEquals(beerList, returnedList);
        assertTrue(beerList.size() > 0);
    }

@Test
    public void givenValidId_shouldReturnBeer() {
        Beer mockBeer = new Beer(
                1,
                "test",
                "lots of test flavor",
                BeerStyle.valueOf("PILSNER"),
                5.5f,
                new Brewery()
        );

        given(beerService.findById(1)).willReturn(mockBeer) ;

        Beer returnedBeer = beerController.getBeerById(1);
        then(beerService).should().findById(1);
        assertEquals(1, returnedBeer.getId());
    }
}
