package com.megatravel.vebaplikacijaagent.dto;

import com.megatravel.vebaplikacijaagent.model.Ocena;

public class OcenaDTO {

    private Long id;
    private int vrednost;
    private Long rezervacija;
	
    public OcenaDTO() { }

    public OcenaDTO(Ocena ocena) {
    	this.id = ocena.getId();
    	this.vrednost = ocena.getVrednost();
    	this.rezervacija = ocena.getRezervacija().getId();
    }
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getVrednost() {
		return vrednost;
	}

	public void setVrednost(int vrednost) {
		this.vrednost = vrednost;
	}

	public Long getRezervacija() {
		return rezervacija;
	}

	public void setRezervacija(Long rezervacija) {
		this.rezervacija = rezervacija;
	}
	
}
