package com.revature.breweryapp.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

public enum BeerStyle {
	
	PILSNER, ALE, PALEALE, LAGER, IPA, STOUT, SOUR

}
