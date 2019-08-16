package com.megatravel.mikroservissmestajnejedinice.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Rezervacija {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = false)
    private double cena;
    @Column(nullable = false, unique = false)
    private boolean realizovana;
    @OneToOne(mappedBy = "rezervacija")
    private Komentar komentar;
    @Column(nullable = false, unique = false)
	private Long korisnik;
    @Column(nullable = false, unique = true)
    private Long ocena;
    @ManyToOne
    private Smestaj smestaj;
    @Column(nullable = false, unique = false)
    private Date pocetak;
    @Column(nullable = false, unique = false)
    private Date kraj;
    
    public Rezervacija() { }

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

	public Komentar getKomentar() {
		return komentar;
	}

	public void setKomentar(Komentar komentar) {
		this.komentar = komentar;
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

	public Smestaj getSmestaj() {
		return smestaj;
	}

	public void setSmestaj(Smestaj smestaj) {
		this.smestaj = smestaj;
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
