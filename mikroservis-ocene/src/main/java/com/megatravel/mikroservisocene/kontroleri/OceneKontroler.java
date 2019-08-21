package com.megatravel.mikroservisocene.kontroleri;

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

import com.megatravel.mikroservisocene.dto.OcenaDTO;
import com.megatravel.mikroservisocene.model.Ocena;
import com.megatravel.mikroservisocene.servisi.OceneServis;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class OceneKontroler {

	@Autowired
	private OceneServis oceneServis;
	
	@RequestMapping(value = "/api/rezervacije/{id}/ocene", 
			produces = MediaType.APPLICATION_JSON_VALUE,
			method = RequestMethod.POST)
	public ResponseEntity<OcenaDTO> ostaviOcenuNaRezervaciju(@RequestParam("korisnik") Long korisnik,
			@RequestParam("ocena") int vrednost,
			@PathVariable("id") Long rezervacija) {
		Ocena ocena = oceneServis.ostaviOcenu(vrednost, rezervacija, korisnik);
		OcenaDTO ocenaDTO = new OcenaDTO(ocena);
		ResponseEntity<OcenaDTO> odgovor = new ResponseEntity<OcenaDTO>(ocenaDTO, HttpStatus.CREATED);
		return odgovor;
	}
	
}
