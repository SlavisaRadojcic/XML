package com.megatravel.mikroservissmestajnejedinice.kontroleri;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.megatravel.mikroservissmestajnejedinice.dto.RezervacijaDTO;
import com.megatravel.mikroservissmestajnejedinice.model.Rezervacija;
import com.megatravel.mikroservissmestajnejedinice.pomocne.PretvaranjeDTO;
import com.megatravel.mikroservissmestajnejedinice.servisi.RezervacijaServis;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(value = "/api/rezervacije", produces = MediaType.APPLICATION_JSON_VALUE)
public class RezervacijaKontroler {

	@Autowired
	private RezervacijaServis rezervacijaServis;
	
	@RequestMapping(value = "/istorija",
			method = RequestMethod.GET)
	public ResponseEntity<List<RezervacijaDTO>> nadjiIstoriju(@RequestParam("korisnik") Long korisnik) {
		List<Rezervacija> rezervacije = rezervacijaServis.nadjiIstoriju(korisnik);
		List<RezervacijaDTO> pretvoreni = PretvaranjeDTO.pretvoriRezervacije(rezervacije);
		ResponseEntity<List<RezervacijaDTO>> odgovor = new ResponseEntity<List<RezervacijaDTO>>(pretvoreni, HttpStatus.OK);
		return odgovor;
	}
	
	@RequestMapping(value = "/aktivne",
			method = RequestMethod.GET)
	public ResponseEntity<List<RezervacijaDTO>> nadjiAktivne(@RequestParam("korisnik") Long korisnik) {
		List<Rezervacija> rezervacije = rezervacijaServis.nadjiAktivne(korisnik);
		List<RezervacijaDTO> pretvoreni = PretvaranjeDTO.pretvoriRezervacije(rezervacije);
		ResponseEntity<List<RezervacijaDTO>> odgovor = new ResponseEntity<List<RezervacijaDTO>>(pretvoreni, HttpStatus.OK);
		return odgovor;
	}
	
	@RequestMapping(method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RezervacijaDTO> rezervisi(@RequestBody RezervacijaDTO rezervacijaDTO,
			@RequestParam("smestaj") Long smestaj,
			@RequestParam("korisnik") Long korisnik) {
		Rezervacija rezervacija = rezervacijaServis.napraviRezervaciju(rezervacijaDTO, smestaj, korisnik);
		RezervacijaDTO kreiranaRezervacijaDTO = new RezervacijaDTO(rezervacija);
		ResponseEntity<RezervacijaDTO> odgovor = new ResponseEntity<RezervacijaDTO>(kreiranaRezervacijaDTO, HttpStatus.CREATED);
		return odgovor;
	}
	
	@RequestMapping(method = RequestMethod.DELETE,
			value = "/{id}")
	public ResponseEntity<Void> otkazi(@PathVariable("id") Long id) {
		rezervacijaServis.otkaziRezervaciju(id);
		ResponseEntity<Void> odgovor = new ResponseEntity<Void>(HttpStatus.OK);
		return odgovor;
	}
	
	@RequestMapping(method = RequestMethod.GET,
			value = "/{id}/korisnik")
	public ResponseEntity<Long> nadjiKorisnikaRezervacije(@PathVariable("id") Long id) {
		Long korisnik = this.rezervacijaServis.nadjiKorisnikaRezervacija(id);
		ResponseEntity<Long> odgovor = new ResponseEntity<Long>(korisnik, HttpStatus.OK);
		return odgovor ;
	}
	
	@RequestMapping(method = RequestMethod.GET,
			value = "/{id}/rezervacije-smestaja")
	public ResponseEntity<List<Long>> nadjiDrugeRezervacije(@PathVariable("id") Long id) {
		List<Long> korisnik = this.rezervacijaServis.nadjiDrugeRezervacije(id);
		ResponseEntity<List<Long>> odgovor = new ResponseEntity<List<Long>>(korisnik, HttpStatus.OK);
		return odgovor ;
	}
	
}
