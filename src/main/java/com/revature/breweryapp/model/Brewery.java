package com.revature.breweryapp.model;

import java.sql.Timestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity @Data @NoArgsConstructor
public class Brewery {

	public Brewery(Integer id, String name, String city, String state) {
		this.id = id;
		this.name = name;
		this.city = city;
		this.state = state;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String name;
	private String city;
	private String state;

}
