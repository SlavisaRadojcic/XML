package com.megatravel.vebaplikacijaagent.dto;

import com.megatravel.vebaplikacijaagent.model.TipSmestaja;

public class TipSmestajaDTO {

    private Long id;
	private String naziv;
	
	public TipSmestajaDTO() {
		
	}
	
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
