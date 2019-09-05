package com.megatravel.vebaplikacijaagent.kontroleri;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.megatravel.vebaplikacijaagent.dto.PretvaranjeDTO;
import com.megatravel.vebaplikacijaagent.dto.SmestajDTO;
import com.megatravel.vebaplikacijaagent.dto.TipSmestajaDTO;
import com.megatravel.vebaplikacijaagent.dto.UslugaDTO;
import com.megatravel.vebaplikacijaagent.model.Smestaj;
import com.megatravel.vebaplikacijaagent.model.TipSmestaja;
import com.megatravel.vebaplikacijaagent.model.Usluga;
import com.megatravel.vebaplikacijaagent.servisi.SmestajServis;
import com.megatravel.vebaplikacijaagent.servisi.TipSmestajaServis;
import com.megatravel.vebaplikacijaagent.servisi.UslugaServis;

@RestController
@RequestMapping(value = "/api/smestaji", produces = MediaType.APPLICATION_JSON_VALUE)
public class SmestajKontroler {

	@Autowired
	private TipSmestajaServis tipSmestajaServis;
	
	@Autowired
	private UslugaServis uslugaServis;
	
	@Autowired
	private SmestajServis smestajServis;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<SmestajDTO>> nadjiSmestajeKojimaUpravljaAgent(@RequestParam("agent") Long agent) {
		List<Smestaj> smestaji = smestajServis.agentoviSmestaji(agent);
		List<SmestajDTO> pretvoreni = PretvaranjeDTO.pretvoriSmestaje(smestaji);
		ResponseEntity<List<SmestajDTO>> odgovor = new ResponseEntity<List<SmestajDTO>>(pretvoreni, HttpStatus.OK);
		return odgovor;
	}
	
	@RequestMapping(method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SmestajDTO> kreirajSmestaj(@RequestBody SmestajDTO smestaj,
			@RequestParam("agent") Long agent) {
		Smestaj kreirani = smestajServis.kreiraj(smestaj, agent);
		SmestajDTO kreiraniDTO = new SmestajDTO(kreirani);
		ResponseEntity<SmestajDTO> odgovor = new ResponseEntity<SmestajDTO>(kreiraniDTO , HttpStatus.CREATED);
		return odgovor;
	}
	
	@RequestMapping(value = "/tipovi", method = RequestMethod.GET)
	public ResponseEntity<List<TipSmestajaDTO>> nadjiSveTipove() {
		List<TipSmestaja> tipovi = tipSmestajaServis.nadjiSve();
		List<TipSmestajaDTO> pretvoreni = PretvaranjeDTO.pretvoriTipoveSmestaja(tipovi );
		ResponseEntity<List<TipSmestajaDTO>> odgovor = new ResponseEntity<List<TipSmestajaDTO>>(pretvoreni , HttpStatus.OK);
		return odgovor;
	}
	
	@RequestMapping(value = "/usluge", method = RequestMethod.GET)
	public ResponseEntity<List<UslugaDTO>> nadjiSveUsluge() {
		List<Usluga> usluge = uslugaServis.nadjiSve();
		List<UslugaDTO> pretvoreni = PretvaranjeDTO.pretvoriUsluge(usluge);
		ResponseEntity<List<UslugaDTO>> odgovor = new ResponseEntity<List<UslugaDTO>>(pretvoreni, HttpStatus.OK);
		return odgovor;
	}
	
}
