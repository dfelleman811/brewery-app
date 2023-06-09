package com.revature.breweryapp.service;

import java.util.List;

import com.revature.breweryapp.model.Beer;
import com.revature.breweryapp.model.BeerStyle;

public interface BeerService {

	public List<Beer> getAllBeer();
	
	public List<Beer> findAllByStyle(BeerStyle style);

	public Beer addBeer(Beer beer);

	public Beer findById(Integer id);
}
