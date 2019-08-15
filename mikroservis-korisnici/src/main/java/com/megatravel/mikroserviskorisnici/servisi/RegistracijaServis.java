package com.megatravel.mikroserviskorisnici.servisi;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import com.megatravel.mikroserviskorisnici.dto.KorisnikDTO;
import com.megatravel.mikroserviskorisnici.model.Korisnik;
import com.megatravel.mikroserviskorisnici.model.StatusKorisnika;
import com.megatravel.mikroserviskorisnici.model.TipKorisnika;
import com.megatravel.mikroserviskorisnici.repozitorijumi.KorisnikRepozitorijum;

@Component
public class RegistracijaServis {

	@Autowired
	private KorisnikRepozitorijum korisnikRepozitorijum;
	
	public Korisnik registruj(KorisnikDTO korisnikDTO) {
		Korisnik korisnik = new Korisnik();
		if(mejlJeZauzet(korisnikDTO.getMejl())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		} else {
			korisnik.setIme(korisnikDTO.getIme());
			korisnik.setPrezime(korisnikDTO.getPrezime());
			korisnik.setMejl(korisnikDTO.getMejl());
			korisnik.setLozinka(korisnikDTO.getSifra());
			korisnik.setStatus(StatusKorisnika.AKTIVAN);
			korisnik.setTip(TipKorisnika.KORISNIK);
			return korisnik;
		}
	}
	
	private boolean mejlJeZauzet(String mejl) {
		List<Korisnik> korisnici = korisnikRepozitorijum.findAll();
		for(Korisnik korisnik : korisnici) {
			if(korisnik.getMejl().equals(mejl)) {
				return false;
			}
		}
		return true;
	}
	
}
