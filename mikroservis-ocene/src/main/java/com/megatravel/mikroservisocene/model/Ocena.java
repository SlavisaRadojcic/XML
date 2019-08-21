package com.megatravel.mikroservisocene.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Ocena {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, updatable = false)
    private int vrednost;
    @Column(nullable = false, updatable = false, unique = true)
    private Long rezervacija;
	
    public Ocena() { }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getVrednost() {
		return vrednost;
	}

	public void setVrednost(int vrednost) {
		this.vrednost = vrednost;
	}

	public Long getRezervacija() {
		return rezervacija;
	}

	public void setRezervacija(Long rezervacija) {
		this.rezervacija = rezervacija;
	}
    
}
