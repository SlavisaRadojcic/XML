package com.megatravel.mikroservissmestajnejedinice.dto;

import java.util.Date;

import com.megatravel.mikroservissmestajnejedinice.model.Rezervacija;

public class RezervacijaDTO {

    private Long id;
    private double cena;
    private boolean realizovana;
	private Long korisnik;
    private Long ocena;
    private Date pocetak;
    private Date kraj;
	
	public RezervacijaDTO(Rezervacija rezervacija) {
		this.id = rezervacija.getId();
		this.cena = rezervacija.getCena();
		this.realizovana = rezervacija.isRealizovana();
		this.korisnik = rezervacija.getKorisnik();
		this.ocena = rezervacija.getOcena();
		this.pocetak = rezervacija.getPocetak();
		this.kraj = rezervacija.getKraj();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public double getCena() {
		return cena;
	}

	public void setCena(double cena) {
		this.cena = cena;
	}

	public boolean isRealizovana() {
		return realizovana;
	}

	public void setRealizovana(boolean realizovana) {
		this.realizovana = realizovana;
	}

	public Long getKorisnik() {
		return korisnik;
	}

	public void setKorisnik(Long korisnik) {
		this.korisnik = korisnik;
	}

	public Long getOcena() {
		return ocena;
	}

	public void setOcena(Long ocena) {
		this.ocena = ocena;
	}

	public Date getPocetak() {
		return pocetak;
	}

	public void setPocetak(Date pocetak) {
		this.pocetak = pocetak;
	}

	public Date getKraj() {
		return kraj;
	}

	public void setKraj(Date kraj) {
		this.kraj = kraj;
	}

}
