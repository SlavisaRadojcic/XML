package com.megatravel.vebaplikacijaagent.kontroleri;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.megatravel.vebaplikacijaagent.dto.KorisnikDTO;
import com.megatravel.vebaplikacijaagent.dto.LoginDTO;
import com.megatravel.vebaplikacijaagent.model.Korisnik;
import com.megatravel.vebaplikacijaagent.servisi.KorisnikServis;

@RestController
public class KorisnikKontroler {

	@Autowired
	private KorisnikServis korisnikServis;
	
	@RequestMapping(value = "/api/korisnici/login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<KorisnikDTO> logovanje(@RequestBody LoginDTO login) {
		Korisnik agent = korisnikServis.logovanje(login.getMejl(), login.getSifra());
		KorisnikDTO agentDTO = new KorisnikDTO(agent);
		ResponseEntity<KorisnikDTO> odgovor = new ResponseEntity<KorisnikDTO>(agentDTO, HttpStatus.OK);
		return odgovor;
	}
	
}
