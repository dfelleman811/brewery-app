package com.revature.breweryapp.service;

import com.revature.breweryapp.model.Beer;
import com.revature.breweryapp.model.BeerStyle;
import com.revature.breweryapp.repository.BeerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
public class BeerServiceUnitTests {

    @Mock
    BeerRepository beerRepository;
    @InjectMocks
    BeerServiceImpl beerService;

    List<Beer> mockBeers = new ArrayList<>();

    @BeforeEach
    public void setUp() {
        mockBeers.add(new Beer());
        given(beerRepository.findAllByStyle(any(BeerStyle.class))).willReturn(mockBeers);


    }

    @Test
    public void givenValidStyle_shouldReturnBeerList() {
        List<Beer> returnedBeerList = beerService.findAllByStyle(BeerStyle.ALE);

        then(beerRepository).should().findAllByStyle(BeerStyle.ALE);
        assertEquals(returnedBeerList, mockBeers);
    }

}
