package com.megatravel.mikroservissmestajnejedinice.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Smestaj {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 512, nullable = false)
    private String opis;
    @Column(nullable = false, unique = false)
    private int kapacitet;
    @Column(nullable = false, unique = false)
    private double cena;
    @Column(nullable = false, unique = false)
    private boolean dozvoljenoOtkazivanje;
    @Column(nullable = false, unique = false)
    private int brojDanaZaOtkazivanje;
    @Column(nullable = false, unique = false)
    private double ocena;
    @Column(nullable = false, unique = false)
    private Long agent;
    @OneToOne(mappedBy = "smestaj")
    private Adresa adresa;
    @ManyToOne
    private TipSmestaja tip;
    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(name = "smestaj_usluga", 
    		   joinColumns = @JoinColumn(name = "smestaj_id"), 
    		   inverseJoinColumns = @JoinColumn(name = "usluga_id"))
    private List<Usluga> usluge;
    @OneToMany(mappedBy = "smestaj")
    private List<Rezervacija> rezervacije;
    
    public Smestaj() { }

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

	public Adresa getAdresa() {
		return adresa;
	}

	public void setAdresa(Adresa adresa) {
		this.adresa = adresa;
	}

	public TipSmestaja getTip() {
		return tip;
	}

	public void setTip(TipSmestaja tip) {
		this.tip = tip;
	}

	public Long getAgent() {
		return agent;
	}

	public void setAgent(Long agent) {
		this.agent = agent;
	}

	public List<Usluga> getUsluge() {
		return usluge;
	}

	public void setUsluge(List<Usluga> usluge) {
		this.usluge = usluge;
	}

	public double getCena() {
		return cena;
	}

	public void setCena(double cena) {
		this.cena = cena;
	}

	public List<Rezervacija> getRezervacije() {
		return rezervacije;
	}

	public void setRezervacije(List<Rezervacija> rezervacije) {
		this.rezervacije = rezervacije;
	}
    
}
