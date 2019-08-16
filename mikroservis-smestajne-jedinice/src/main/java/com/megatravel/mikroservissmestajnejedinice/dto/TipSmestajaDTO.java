package com.megatravel.mikroservissmestajnejedinice.dto;

import com.megatravel.mikroservissmestajnejedinice.model.TipSmestaja;

public class TipSmestajaDTO {

    private Long id;
	private String naziv;
	
	public TipSmestajaDTO(TipSmestaja tip) {
		this.id = tip.getId();
		this.naziv = tip.getNaziv();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

}
