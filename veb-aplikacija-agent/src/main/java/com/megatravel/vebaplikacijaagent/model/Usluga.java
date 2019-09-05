package com.megatravel.vebaplikacijaagent.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Usluga {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 64, nullable = false, unique = true)
    private String naziv;
    @Column(length = 512, nullable = false)
    private String opis;
    @ManyToMany(mappedBy = "usluge")
    private List<Smestaj> smestaji;
    
    public Usluga() { }

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

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public List<Smestaj> getSmestaji() {
		return smestaji;
	}

	public void setSmestaji(List<Smestaj> smestaji) {
		this.smestaji = smestaji;
	}
    
}