package com.megatravel.mikroserviskorisnici.servisi;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import com.megatravel.mikroserviskorisnici.model.Korisnik;
import com.megatravel.mikroserviskorisnici.model.StatusKorisnika;
import com.megatravel.mikroserviskorisnici.model.TipKorisnika;
import com.megatravel.mikroserviskorisnici.repozitorijumi.KorisnikRepozitorijum;

@Component
public class LoginServis {

	@Autowired
	private KorisnikRepozitorijum korisnikRepozitorijum;
	
	public Korisnik uloguj(String mejl, String sifra) {
		List<Korisnik> korisnici = korisnikRepozitorijum.findAll();
		for(Korisnik korisnik : korisnici) {
			if(korisnik.getMejl().equals(mejl)) {
				if(korisnik.getLozinka().equals(sifra) 
						&& korisnik.getStatus().equals(StatusKorisnika.AKTIVAN)
							&& ( korisnik.getTip().equals(TipKorisnika.KORISNIK) || korisnik.getTip().equals(TipKorisnika.ADMINISTRATOR))) {
					return korisnik;
				} else {
					throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
				}
			}
		}
		throw new ResponseStatusException(HttpStatus.NOT_FOUND);
	}
	
	public Korisnik ulogujAgenta(String mejl, String sifra) {
		List<Korisnik> korisnici = korisnikRepozitorijum.findAll();
		for(Korisnik korisnik : korisnici) {
			if(korisnik.getMejl().equals(mejl)) {
				if(korisnik.getLozinka().equals(sifra) 
						&& korisnik.getStatus().equals(StatusKorisnika.AKTIVAN)
							&& korisnik.getTip().equals(TipKorisnika.AGENT)) {
					return korisnik;
				} else {
					throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
				}
			}
		}
		throw new ResponseStatusException(HttpStatus.NOT_FOUND);
	}
	
}
