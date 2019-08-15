package com.megatravel.mikroserviskorisnici.servisi;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import com.megatravel.mikroserviskorisnici.model.Korisnik;
import com.megatravel.mikroserviskorisnici.model.StatusKorisnika;
import com.megatravel.mikroserviskorisnici.model.TipKorisnika;
import com.megatravel.mikroserviskorisnici.repozitorijumi.KorisnikRepozitorijum;

@Component
public class KorisnikServis {

	@Autowired
	private KorisnikRepozitorijum korisnikRepozitorijum;
	
	public List<Korisnik> nadjiSve() {
		List<Korisnik> korisnici = korisnikRepozitorijum.findAll();
		List<Korisnik> trazeni = new ArrayList<>();
		for(Korisnik korisnik : korisnici) {
			if(korisnik.getTip().equals(TipKorisnika.KORISNIK)) {
				trazeni.add(korisnik);
			}
		}
		return trazeni;
	}
	
	public Korisnik nadjiJednog(Long id) {
		Optional<Korisnik> korisnik = korisnikRepozitorijum.findById(id);
		if(korisnik.isPresent()) {
			if(korisnik.get().getTip().equals(TipKorisnika.KORISNIK)) {
				return korisnik.get();
			} else {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
			}
		}
		throw new ResponseStatusException(HttpStatus.NOT_FOUND);
	}
	
	public Korisnik promeni(Long id, StatusKorisnika status) {
		Korisnik korisnik = nadjiJednog(id);
		korisnik.setStatus(status);
		korisnikRepozitorijum.save(korisnik);
		return korisnik;
	}
	
}
