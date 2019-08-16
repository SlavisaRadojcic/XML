package com.megatravel.mikroservissmestajnejedinice.dto;

import com.megatravel.mikroservissmestajnejedinice.model.Smestaj;

public class SmestajDTO {

    private Long id;
    private String opis;
    private int kapacitet;
    private double cena;
    private boolean dozvoljenoOtkazivanje;
    private int brojDanaZaOtkazivanje;
    private double ocena;
    private Long agent;
    private AdresaDTO adresaDTO;
    private TipSmestajaDTO tipDTO;
	
	public SmestajDTO(Smestaj smestaj) {
		this.id = smestaj.getId();
		this.opis = smestaj.getOpis();
		this.kapacitet = smestaj.getKapacitet();
		this.cena = smestaj.getCena();
		this.dozvoljenoOtkazivanje = smestaj.isDozvoljenoOtkazivanje();
		this.brojDanaZaOtkazivanje = smestaj.getBrojDanaZaOtkazivanje();
		this.ocena = smestaj.getOcena();
		this.agent = smestaj.getAgent();
		this.adresaDTO = new AdresaDTO(smestaj.getAdresa());
		this.tipDTO = new TipSmestajaDTO(smestaj.getTip());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public int getKapacitet() {
		return kapacitet;
	}

	public void setKapacitet(int kapacitet) {
		this.kapacitet = kapacitet;
	}

	public double getCena() {
		return cena;
	}

	public void setCena(double cena) {
		this.cena = cena;
	}

	public boolean isDozvoljenoOtkazivanje() {
		return dozvoljenoOtkazivanje;
	}

	public void setDozvoljenoOtkazivanje(boolean dozvoljenoOtkazivanje) {
		this.dozvoljenoOtkazivanje = dozvoljenoOtkazivanje;
	}

	public int getBrojDanaZaOtkazivanje() {
		return brojDanaZaOtkazivanje;
	}

	public void setBrojDanaZaOtkazivanje(int brojDanaZaOtkazivanje) {
		this.brojDanaZaOtkazivanje = brojDanaZaOtkazivanje;
	}

	public double getOcena() {
		return ocena;
	}

	public void setOcena(double ocena) {
		this.ocena = ocena;
	}

	public Long getAgent() {
		return agent;
	}

	public void setAgent(Long agent) {
		this.agent = agent;
	}

	public AdresaDTO getAdresaDTO() {
		return adresaDTO;
	}

	public void setAdresaDTO(AdresaDTO adresaDTO) {
		this.adresaDTO = adresaDTO;
	}

	public TipSmestajaDTO getTipDTO() {
		return tipDTO;
	}

	public void setTipDTO(TipSmestajaDTO tipDTO) {
		this.tipDTO = tipDTO;
	}
	
}
