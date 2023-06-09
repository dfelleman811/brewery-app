package com.revature.breweryapp.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Entity
@Data
public class Beer {
	


	public Beer(Integer id, String name, String description, BeerStyle style, float abv, Brewery brewery) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.style = style;
		this.abv = abv;
		this.brewery = brewery;
		
	}
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String name;
	private String description;
	
	@Enumerated(EnumType.STRING)
	private BeerStyle style;
	
	private float abv;
	
	@OneToOne
	@JoinColumn(name = "brewery_id", referencedColumnName = "id")
	private Brewery brewery;

	
}
