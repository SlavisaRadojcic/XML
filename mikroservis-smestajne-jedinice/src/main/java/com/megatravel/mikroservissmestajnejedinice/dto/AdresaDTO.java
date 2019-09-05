package com.megatravel.mikroservissmestajnejedinice.dto;

import com.megatravel.mikroservissmestajnejedinice.model.Adresa;

public class AdresaDTO {

    private Long id;
	private String zemlja;
	private String grad;
	private String ulica;
	private int broj;
	
	public AdresaDTO() {
		
	}
	
	public AdresaDTO(Adresa adresa) {
		this.id = adresa.getId();
		this.zemlja = adresa.getZemlja();
		this.grad = adresa.getGrad();
		this.ulica = adresa.getUlica();
		this.broj = adresa.getBroj();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getZemlja() {
		return zemlja;
	}

	public void setZemlja(String zemlja) {
		this.zemlja = zemlja;
	}

	public String getGrad() {
		return grad;
	}

	public void setGrad(String grad) {
		this.grad = grad;
	}

	public String getUlica() {
		return ulica;
	}

	public void setUlica(String ulica) {
		this.ulica = ulica;
	}

	public int getBroj() {
		return broj;
	}

	public void setBroj(int broj) {
		this.broj = broj;
	}

}
