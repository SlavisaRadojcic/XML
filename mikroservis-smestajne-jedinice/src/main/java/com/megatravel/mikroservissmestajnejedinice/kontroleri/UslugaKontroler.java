package com.megatravel.mikroservissmestajnejedinice.kontroleri;

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

import com.megatravel.mikroservissmestajnejedinice.dto.UslugaDTO;
import com.megatravel.mikroservissmestajnejedinice.model.Usluga;
import com.megatravel.mikroservissmestajnejedinice.pomocne.PretvaranjeDTO;
import com.megatravel.mikroservissmestajnejedinice.servisi.UslugaServis;

@RestController
@RequestMapping(value = "/api/usluge",
				produces = MediaType.APPLICATION_JSON_VALUE)
public class UslugaKontroler {

	@Autowired
	private UslugaServis uslugaServis;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<UslugaDTO>> nadjiSve() {
		List<Usluga> usluge = uslugaServis.nadjiSve();
		List<UslugaDTO> pretvoreni = PretvaranjeDTO.pretvoriUsluge(usluge);
		ResponseEntity<List<UslugaDTO>> odgovor = new ResponseEntity<List<UslugaDTO>>(pretvoreni, HttpStatus.OK);
		return odgovor;
	}
	
	@RequestMapping(method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UslugaDTO> dodajUslugu(@RequestBody UslugaDTO uslugaDTO) {
		Usluga usluga = uslugaServis.dodajUslugu(uslugaDTO);
		UslugaDTO kreiranaUslugaDTO = new UslugaDTO(usluga);
		ResponseEntity<UslugaDTO> odgovor = new ResponseEntity<UslugaDTO>(kreiranaUslugaDTO , HttpStatus.CREATED);
		return odgovor;
	}
	
	@RequestMapping(method = RequestMethod.PUT,
			value = "/{id}",
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UslugaDTO> azurirajUslugu(@RequestBody UslugaDTO uslugaDTO
			, @PathVariable("id") Long id) {
		Usluga usluga = uslugaServis.azurirajUslugu(uslugaDTO, id);
		UslugaDTO kreiranaUslugaDTO = new UslugaDTO(usluga);
		ResponseEntity<UslugaDTO> odgovor = new ResponseEntity<UslugaDTO>(kreiranaUslugaDTO , HttpStatus.CREATED);
		return odgovor;
	}
	
	@RequestMapping(method = RequestMethod.DELETE,
			value = "/{id}")
	public ResponseEntity<Void> obrisiUslugu(@PathVariable("id") Long id) {
		uslugaServis.obrisiUslugu(id);
		ResponseEntity<Void> odgovor = new ResponseEntity<Void>(HttpStatus.OK);
		return odgovor ;
	}
	
}
