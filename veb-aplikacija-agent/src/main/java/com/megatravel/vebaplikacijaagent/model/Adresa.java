package com.megatravel.vebaplikacijaagent.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Adresa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	@Column(length = 64, nullable = false, unique = false)
	private String zemlja;
	@Column(length = 64, nullable = false, unique = false)
	private String grad;
	@Column(length = 64, nullable = false, unique = false)
	private String ulica;
	@Column(nullable = false, unique = false)
	private int broj;
	@OneToOne
	private Smestaj smestaj;
	
	public Adresa() { }

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

	public Smestaj getSmestaj() {
		return smestaj;
	}

	public void setSmestaj(Smestaj smestaj) {
		this.smestaj = smestaj;
	}
	
}