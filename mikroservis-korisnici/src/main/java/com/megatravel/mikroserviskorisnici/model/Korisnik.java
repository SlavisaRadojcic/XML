package com.megatravel.mikroserviskorisnici.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Korisnik {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	@Column(length = 64, nullable = false, unique = true)
	private String mejl;
	@Column(length = 64, nullable = false, unique = false)
	private String lozinka;
	@Column(length = 64, nullable = false, unique = false)
	private String ime;
	@Column(length = 64, nullable = false, unique = false)
	private String prezime;
	@Column(length = 64, nullable = true, unique = true)
	private String poslovniMaticniBroj;
	@Enumerated(EnumType.STRING)
	@Column(nullable = false, unique = false)
	private StatusKorisnika status;
	@Enumerated(EnumType.STRING)
	@Column(nullable = false, unique = false)
	private TipKorisnika tip;
	
	public Korisnik() { }

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

	public String getLozinka() {
		return lozinka;
	}

	public void setLozinka(String lozinka) {
		this.lozinka = lozinka;
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
