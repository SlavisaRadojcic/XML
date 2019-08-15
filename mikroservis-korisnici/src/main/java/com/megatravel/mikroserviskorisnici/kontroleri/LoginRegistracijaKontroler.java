package com.megatravel.mikroserviskorisnici.kontroleri;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.megatravel.mikroserviskorisnici.dto.KorisnikDTO;
import com.megatravel.mikroserviskorisnici.dto.LoginDTO;
import com.megatravel.mikroserviskorisnici.model.Korisnik;
import com.megatravel.mikroserviskorisnici.servisi.LoginServis;
import com.megatravel.mikroserviskorisnici.servisi.RegistracijaServis;

@RestController
public class LoginRegistracijaKontroler {

	@Autowired
	private LoginServis loginServis;
	
	@Autowired
	private RegistracijaServis registracijaServis;
	
	@RequestMapping(value = "/uloguj", 
			method = RequestMethod.POST, 
			consumes = MediaType.APPLICATION_JSON_VALUE, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<KorisnikDTO> uloguj(@RequestBody LoginDTO loginDTO) {
		Korisnik korisnik = loginServis.uloguj(loginDTO.getMejl(), loginDTO.getSifra());
		KorisnikDTO korisnikDTO = new KorisnikDTO(korisnik);
		ResponseEntity<KorisnikDTO> odgovor = new ResponseEntity<KorisnikDTO>(korisnikDTO, HttpStatus.OK);
		return odgovor;
	}
	
	@RequestMapping(value = "/registruj",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<KorisnikDTO> registruj(@RequestBody KorisnikDTO korisnikDTO) {
		Korisnik korisnik = registracijaServis.registruj(korisnikDTO);
		KorisnikDTO kreiraniKorisnikDTO = new KorisnikDTO(korisnik);
		ResponseEntity<KorisnikDTO> odgovor = new ResponseEntity<KorisnikDTO>(kreiraniKorisnikDTO, HttpStatus.OK);
		return odgovor;
	}
}
