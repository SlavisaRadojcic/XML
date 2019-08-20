package com.megatravel.mikroserviskorisnici.kontroleri;

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
import org.springframework.web.bind.annotation.RestController;

import com.megatravel.mikroserviskorisnici.dto.KorisnikDTO;
import com.megatravel.mikroserviskorisnici.model.Korisnik;
import com.megatravel.mikroserviskorisnici.pomocne.PretvaranjeDTO;
import com.megatravel.mikroserviskorisnici.servisi.AgentServis;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(value = "/api/agenti", 
				produces = MediaType.APPLICATION_JSON_VALUE)
public class AgentKontroler {

	@Autowired
	private AgentServis agentServis;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<KorisnikDTO>> nadjiSve() {
		List<Korisnik> agenti = agentServis.nadjiSve();
		List<KorisnikDTO> pretvoreni = PretvaranjeDTO.pretvori(agenti);
		ResponseEntity<List<KorisnikDTO>> odgovor = new ResponseEntity<List<KorisnikDTO>>(pretvoreni, HttpStatus.OK);
		return odgovor;
	}
	
	@RequestMapping(method = RequestMethod.GET,
			value = "/{id}")
	public ResponseEntity<KorisnikDTO> nadjiJednog(@PathVariable("id") Long id) {
		Korisnik agent = agentServis.nadjiJednog(id);
		KorisnikDTO agentDTO = new KorisnikDTO(agent);
		ResponseEntity<KorisnikDTO> odgovor = new ResponseEntity<KorisnikDTO>(agentDTO, HttpStatus.OK);
		return odgovor;
	}
	
	@RequestMapping(method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<KorisnikDTO> dodavanjeAgenta(@RequestBody KorisnikDTO korisnikDTO) {
		Korisnik agent = agentServis.dodaj(korisnikDTO);
		KorisnikDTO agentDTO = new KorisnikDTO(agent);
		ResponseEntity<KorisnikDTO> odgovor = new ResponseEntity<KorisnikDTO>(agentDTO, HttpStatus.OK);
		return odgovor;
	}
	
}
