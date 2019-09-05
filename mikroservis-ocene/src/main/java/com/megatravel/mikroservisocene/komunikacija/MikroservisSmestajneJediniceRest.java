package com.megatravel.mikroservisocene.komunikacija;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("mikroservis-smestajne-jedinice")
public interface MikroservisSmestajneJediniceRest {

	@RequestMapping(method = RequestMethod.GET,
			value = "/api/rezervacije/{id}/korisnik", 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Long> nadjiKorisnikaRezervacije(@PathVariable("id") Long id);
	
	@RequestMapping(method = RequestMethod.GET,
			value = "/api/rezervacije/{id}/rezervacije-smestaja", 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Long>> nadjiDrugeRezervacije(@PathVariable("id") Long id);
	
	@RequestMapping(method = RequestMethod.POST,
			value = "/api/smestaji/sa-rezervacijom/{id}", 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> postaviOcenu(@RequestParam("ocena") double ocena,
			@PathVariable("id") Long rezervacijaId);
	
	@RequestMapping(method = RequestMethod.GET,
			value = "api/rezervacije/{id}",
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Boolean> rezervacijeJeRealizovana(@PathVariable("id") Long id);
	
}
