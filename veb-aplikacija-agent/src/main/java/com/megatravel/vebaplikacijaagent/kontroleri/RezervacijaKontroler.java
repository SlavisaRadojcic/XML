package com.megatravel.vebaplikacijaagent.kontroleri;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.megatravel.vebaplikacijaagent.dto.PretvaranjeDTO;
import com.megatravel.vebaplikacijaagent.dto.RezervacijaDTO;
import com.megatravel.vebaplikacijaagent.model.Rezervacija;
import com.megatravel.vebaplikacijaagent.servisi.RezervacijaServis;

@RestController
@RequestMapping(value = "/api/smestaji/{id}/rezervacije", produces = MediaType.APPLICATION_JSON_VALUE)
public class RezervacijaKontroler {

	@Autowired
	private RezervacijaServis rezervacijaServis;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<RezervacijaDTO>> nadjiRezervacijeSmestaja(@PathVariable("id") Long smestaj) {
		List<Rezervacija> rezervacije = rezervacijaServis.rezervacijeSmestaja(smestaj);
		List<RezervacijaDTO> pretvoreni = PretvaranjeDTO.pretvoriRezervacije(rezervacije);
		ResponseEntity<List<RezervacijaDTO>> odgovor = new ResponseEntity<List<RezervacijaDTO>>(pretvoreni, HttpStatus.OK);
		return odgovor;
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RezervacijaDTO> napraviRezervaciju(@RequestBody RezervacijaDTO rezervacija, @PathVariable("id") Long smestaj) {
		Rezervacija kreirana = rezervacijaServis.kreiraj(rezervacija, smestaj);
		RezervacijaDTO kreiranaDTO = new RezervacijaDTO(kreirana );
		ResponseEntity<RezervacijaDTO> odgovor = new ResponseEntity<RezervacijaDTO>(kreiranaDTO , HttpStatus.CREATED);
		return odgovor ;
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/{rezervacija}")
	public ResponseEntity<RezervacijaDTO> potvrdiRezervaciju(@PathVariable("rezervacija") Long rezervacija) {
		Rezervacija rez = rezervacijaServis.realizujRezervaciju(rezervacija);
		RezervacijaDTO kreiranaRezervacijaDTO = new RezervacijaDTO(rez);
		ResponseEntity<RezervacijaDTO> odgovor = new ResponseEntity<RezervacijaDTO>(kreiranaRezervacijaDTO, HttpStatus.CREATED);
		return odgovor;
	}
	
}
