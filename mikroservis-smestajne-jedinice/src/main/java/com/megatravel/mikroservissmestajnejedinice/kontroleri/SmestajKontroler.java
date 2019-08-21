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

import com.megatravel.mikroservissmestajnejedinice.dto.KriterijumiPretrageDTO;
import com.megatravel.mikroservissmestajnejedinice.dto.SmestajDTO;
import com.megatravel.mikroservissmestajnejedinice.model.Smestaj;
import com.megatravel.mikroservissmestajnejedinice.pomocne.PretvaranjeDTO;
import com.megatravel.mikroservissmestajnejedinice.servisi.SmestajServis;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(value = "/api/smestaji",
				produces = MediaType.APPLICATION_JSON_VALUE)
public class SmestajKontroler {

	@Autowired
	private SmestajServis smestajServis;
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<List<SmestajDTO>> preuzmiSve(@RequestBody KriterijumiPretrageDTO kriterijumiPretrageDTO) {
		List<Smestaj> smestaji = smestajServis.nadjiSve(kriterijumiPretrageDTO);
		List<SmestajDTO> pretvoreni = PretvaranjeDTO.pretvoriSmestaje(smestaji);
		ResponseEntity<List<SmestajDTO>> odgovor = new ResponseEntity<List<SmestajDTO>>(pretvoreni, HttpStatus.OK);
		return odgovor;
	}
	
	@RequestMapping(method = RequestMethod.POST,
			value = "/sa-rezervacijom/{id}")
	public ResponseEntity<SmestajDTO> postaviOcenu(@RequestParam("ocena") double ocena,
			@PathVariable("id") Long rezervacijaId) {
		Smestaj smestaj = this.smestajServis.postaviOcenu(ocena, rezervacijaId);
		SmestajDTO smestajDTO = new SmestajDTO(smestaj);
		ResponseEntity<SmestajDTO> odgovor = new ResponseEntity<SmestajDTO>(smestajDTO, HttpStatus.ACCEPTED);
		return odgovor ;
	}
	
}
