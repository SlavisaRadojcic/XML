package com.megatravel.mikroservisocene.servisi;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import com.megatravel.mikroservisocene.komunikacija.MikroservisKorisniciRest;
import com.megatravel.mikroservisocene.komunikacija.MikroservisSmestajneJediniceRest;
import com.megatravel.mikroservisocene.model.Ocena;
import com.megatravel.mikroservisocene.repozitorijumi.OceneRepozitorijum;

@Component
public class OceneServis {

	@Autowired
	private OceneRepozitorijum oceneRepozitorijum;

	@Autowired
	private MikroservisSmestajneJediniceRest restSmestaj;
	
	@Autowired
	private MikroservisKorisniciRest restKorisnici;
	
	public Ocena ostaviOcenu(int vrednost, Long rezervacija, Long korisnik) {
		List<Ocena> ocene = this.oceneRepozitorijum.findAll();
		// Proverava da li je vec ocenjeno
		for(Ocena ocena : ocene) {
			if(ocena.getRezervacija().equals(rezervacija)) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
			}
		}
		// Proverava da li je dobra vrednost ocene
		if(vrednost < 1 || vrednost > 5) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
		// Proverava da li je trazeni korisnik onaj sa rezervacije
		ResponseEntity<Long> odgovorKorisnik = this.restSmestaj.nadjiKorisnikaRezervacije(rezervacija);
		if(odgovorKorisnik.getStatusCode().is2xxSuccessful()) {
			if(!odgovorKorisnik.getBody().equals(korisnik)) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
			}
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		// Proverava da li se u sistemu nalazi korisnik
		ResponseEntity<?> odgovorNadjiKorisnika = this.restKorisnici.nadjiJednog(korisnik);
		if(!odgovorNadjiKorisnika.getStatusCode().is2xxSuccessful()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		// Proverava da li je rezervacija realizovana
		ResponseEntity<Boolean> odgovorRealizovana = this.restSmestaj.rezervacijeJeRealizovana(rezervacija);
		if(odgovorRealizovana.getStatusCode().is2xxSuccessful() && !odgovorRealizovana.getBody()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
		Ocena ocena = new Ocena();
		ocena.setVrednost(vrednost);
		ocena.setRezervacija(rezervacija);
		this.oceneRepozitorijum.save(ocena);
		// Pronalazi druge rezervacije istog smestaja
		ResponseEntity<List<Long>> odgovorRezervacije = this.restSmestaj.nadjiDrugeRezervacije(rezervacija);
		if(!odgovorRezervacije.getStatusCode().is2xxSuccessful()) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		// Racuna novu ocenu kao prosek svih postojecih ocena tog smestaja
		double novaOcena = this.novaOcena(odgovorRezervacije.getBody());
		this.restSmestaj.postaviOcenu(novaOcena, rezervacija);
		return ocena;
	}
	
	private double novaOcena(List<Long> rezervacije) {
		double novaOcena = 0;
		List<Ocena> ocene = this.oceneRepozitorijum.findAll();
		for(Ocena ocena : ocene) {
			for(Long rezervacija : rezervacije) {
				if(ocena.getRezervacija().equals(rezervacija)) {
					novaOcena += ocena.getVrednost();
				}
			}
		}
		return novaOcena / rezervacije.size();
	}
	
}
