package com.megatravel.mikroservissmestajnejedinice.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class TipSmestaja {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
	private String naziv;
    
    @OneToMany(mappedBy = "tip")
    private List<Smestaj> smestaji;
    
    public TipSmestaja() { }

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

	public List<Smestaj> getSmestaji() {
		return smestaji;
	}

	public void setSmestaji(List<Smestaj> smestaji) {
		this.smestaji = smestaji;
	}
    
}
