package com.revature.breweryapp.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.revature.breweryapp.model.Beer;
import com.revature.breweryapp.model.BeerStyle;
import com.revature.breweryapp.repository.BeerRepository;

@Service
public class BeerServiceImpl implements BeerService {
	
	private BeerRepository beerRepository;
	
	public BeerServiceImpl(BeerRepository beerRepository) {
		this.beerRepository = beerRepository;
	}

	@Override
	public List<Beer> getAllBeer() {
		return this.beerRepository.findAll();
	}

	@Override
	public List<Beer> findAllByStyle(BeerStyle style) {
		return this.beerRepository.findAllByStyle(style);
	}

	@Override
	public Beer addBeer(Beer beer) {
		return this.beerRepository.save(beer);
	}

	@Override
	public Beer findById(Integer id) {
		return this.beerRepository.findById(id).orElse(null);
	}
	
	

}
