package com.megatravel.mikroserviskorisnici.kontroleri;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.megatravel.mikroserviskorisnici.dto.KorisnikDTO;
import com.megatravel.mikroserviskorisnici.model.Korisnik;
import com.megatravel.mikroserviskorisnici.model.StatusKorisnika;
import com.megatravel.mikroserviskorisnici.pomocne.PretvaranjeDTO;
import com.megatravel.mikroserviskorisnici.servisi.KorisnikServis;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(value = "/api/korisnici",
				produces = MediaType.APPLICATION_JSON_VALUE)
public class KorisnikKontroler {

	@Autowired
	private KorisnikServis korisnikServis;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<KorisnikDTO>> nadjiSve() {
		List<Korisnik> korisnici = korisnikServis.nadjiSve();
		List<KorisnikDTO> pretvoreni = PretvaranjeDTO.pretvori(korisnici);
		ResponseEntity<List<KorisnikDTO>> odgovor = new ResponseEntity<List<KorisnikDTO>>(pretvoreni, HttpStatus.OK);
		return odgovor;
	}
	
	@RequestMapping(method = RequestMethod.GET,
			value = "/{id}")
	public ResponseEntity<KorisnikDTO> nadjiJednog(@PathVariable("id") Long id) {
		Korisnik korisnik = korisnikServis.nadjiJednog(id);
		KorisnikDTO korisnikDTO = new KorisnikDTO(korisnik);
		ResponseEntity<KorisnikDTO> odgovor = new ResponseEntity<KorisnikDTO>(korisnikDTO, HttpStatus.OK);
		return odgovor;
	}
	
	@RequestMapping(method = RequestMethod.POST,
			value = "/{id}")
	public ResponseEntity<KorisnikDTO> promeni(@RequestParam("status") StatusKorisnika status,
			@PathVariable("id") Long id) {
		Korisnik korisnik = korisnikServis.promeni(id, status);
		KorisnikDTO korisnikDTO = new KorisnikDTO(korisnik);
		ResponseEntity<KorisnikDTO> odgovor = new ResponseEntity<KorisnikDTO>(korisnikDTO, HttpStatus.OK);
		return odgovor;
	}
	
}
