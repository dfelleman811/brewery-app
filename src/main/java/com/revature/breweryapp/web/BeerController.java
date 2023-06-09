package com.revature.breweryapp.web;

import java.util.List;

import com.revature.breweryapp.model.BeerStyle;
import com.revature.breweryapp.validators.BeerStyleValidator;
import org.hibernate.annotations.Check;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.revature.breweryapp.model.Beer;
import com.revature.breweryapp.service.BeerService;

@RestController
@RequestMapping("/beer")
@CrossOrigin
@Validated
public class BeerController {

	private BeerService beerService;

	public BeerController(BeerService beerService) {
		this.beerService = beerService;
	}

	@GetMapping(produces = "application/json")
	@BeerStyleValidator
	public List<Beer> getAllBeer(
			@RequestParam(required = false) String style,
			@RequestParam(required = false) String brewery) {

		if (style != null) {
			for (BeerStyle beerStyle : BeerStyle.values()) {
				if (beerStyle.name().equalsIgnoreCase(style)) {
					return this.beerService.findAllByStyle(beerStyle);
				}
			}
		}
		return this.beerService.getAllBeer();
	}
	
	@GetMapping(path = "/{id}", produces = "application/json")
	public Beer getBeerById(@PathVariable Integer id) {

		return this.beerService.findById(id);
	}

	
	@PostMapping(consumes = "application/json", produces = "application/json")
	public Beer addBeer(@RequestBody Beer beer) {
		return this.beerService.addBeer(beer);
	}

}
