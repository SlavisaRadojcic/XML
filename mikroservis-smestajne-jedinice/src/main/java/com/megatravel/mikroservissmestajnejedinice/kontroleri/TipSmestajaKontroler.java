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

import com.megatravel.mikroservissmestajnejedinice.dto.TipSmestajaDTO;
import com.megatravel.mikroservissmestajnejedinice.model.TipSmestaja;
import com.megatravel.mikroservissmestajnejedinice.pomocne.PretvaranjeDTO;
import com.megatravel.mikroservissmestajnejedinice.servisi.TipSmestajaServis;

@RestController
@RequestMapping(value = "/api/tipovi",
				produces = MediaType.APPLICATION_JSON_VALUE)
public class TipSmestajaKontroler {

	@Autowired
	private TipSmestajaServis tipSmestajaServis;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<TipSmestajaDTO>> nadjiSve() {
		List<TipSmestaja> tipovi = tipSmestajaServis.nadjiSve();
		List<TipSmestajaDTO> pretvoreni = PretvaranjeDTO.pretvoriTipoveSmestaja(tipovi );
		ResponseEntity<List<TipSmestajaDTO>> odgovor = new ResponseEntity<List<TipSmestajaDTO>>(pretvoreni , HttpStatus.OK);
		return odgovor;
	}
	
	@RequestMapping(method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<TipSmestajaDTO> dodajTipSmestaja(@RequestBody TipSmestajaDTO tipSmestajaDTO) {
		TipSmestaja tip = tipSmestajaServis.dodajTipSmestaja(tipSmestajaDTO);
		TipSmestajaDTO kreiraniTipSmestajaDTO = new TipSmestajaDTO(tip );
		ResponseEntity<TipSmestajaDTO> odgovor = new ResponseEntity<TipSmestajaDTO>(kreiraniTipSmestajaDTO , HttpStatus.CREATED);
		return odgovor;
	}
	
	@RequestMapping(method = RequestMethod.PUT,
			value = "/{id}",
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<TipSmestajaDTO> azurirajTipSmestaja(@RequestBody TipSmestajaDTO tipSmestajaDTO
			, @PathVariable("id") Long id) {
		TipSmestaja tip = tipSmestajaServis.azurirajTipSmestaja(tipSmestajaDTO, id);
		TipSmestajaDTO kreiraniTipSmestajaDTO = new TipSmestajaDTO(tip );
		ResponseEntity<TipSmestajaDTO> odgovor = new ResponseEntity<TipSmestajaDTO>(kreiraniTipSmestajaDTO , HttpStatus.CREATED);
		return odgovor;
	}
	
	@RequestMapping(method = RequestMethod.DELETE,
			value = "/{id}")
	public ResponseEntity<Void> obrisiTipSmestaja(@PathVariable("id") Long id) {
		tipSmestajaServis.obrisiTipSmestaja(id);
		ResponseEntity<Void> odgovor = new ResponseEntity<Void>(HttpStatus.OK);
		return odgovor ;
	}
	
}
