package com.revature.breweryapp.repository;

import java.util.List;


import com.revature.breweryapp.model.Brewery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.breweryapp.model.Beer;
import com.revature.breweryapp.model.BeerStyle;

@Repository
public interface BeerRepository extends JpaRepository<Beer, Integer> {
	
	List<Beer> findAllByStyle(BeerStyle style);

	List<Beer> findAllByBrewery(Brewery brewery);

	List<Beer> findAllByBreweryAndStyle(Brewery brewery, BeerStyle style);

	List<Beer> findAllByOrderByAbvDesc();

	List<Beer> findAllByOrderByAbvAsc();
}
