package com.megatravel.mikroserviskorisnici.dto;

import com.megatravel.mikroserviskorisnici.model.Korisnik;
import com.megatravel.mikroserviskorisnici.model.StatusKorisnika;
import com.megatravel.mikroserviskorisnici.model.TipKorisnika;

public class KorisnikDTO {

    private Long id;
	private String mejl;
	private String sifra;
	private String ime;
	private String prezime;
	private String poslovniMaticniBroj;
	private StatusKorisnika status;
	private TipKorisnika tip;
	
	public KorisnikDTO(Korisnik korisnik) {
		this.id = korisnik.getId();
		this.mejl = korisnik.getMejl();
		this.sifra = korisnik.getLozinka();
		this.ime = korisnik.getIme();
		this.prezime = korisnik.getPrezime();
		this.poslovniMaticniBroj = korisnik.getPoslovniMaticniBroj();
		this.status = korisnik.getStatus();
		this.tip = korisnik.getTip();
	}
	
	public KorisnikDTO() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMejl() {
		return mejl;
	}

	public void setMejl(String mejl) {
		this.mejl = mejl;
	}

	public String getSifra() {
		return sifra;
	}

	public void getSifra(String sifra) {
		this.sifra = sifra;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public String getPoslovniMaticniBroj() {
		return poslovniMaticniBroj;
	}

	public void setPoslovniMaticniBroj(String poslovniMaticniBroj) {
		this.poslovniMaticniBroj = poslovniMaticniBroj;
	}

	public StatusKorisnika getStatus() {
		return status;
	}

	public void setStatus(StatusKorisnika status) {
		this.status = status;
	}

	public TipKorisnika getTip() {
		return tip;
	}

	public void setTip(TipKorisnika tip) {
		this.tip = tip;
	}
	
}
