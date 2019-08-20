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
import org.springframework.web.bind.annotation.RestController;

import com.megatravel.mikroservissmestajnejedinice.dto.KomentarDTO;
import com.megatravel.mikroservissmestajnejedinice.model.Komentar;
import com.megatravel.mikroservissmestajnejedinice.pomocne.PretvaranjeDTO;
import com.megatravel.mikroservissmestajnejedinice.servisi.KomentarServis;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class KomentarKontroler {

	@Autowired
	private KomentarServis komentarServis;
	
	@RequestMapping(method = RequestMethod.GET,
			value = "/api/rezervacije/{rezervacija-id}/komentar")
	public ResponseEntity<KomentarDTO> nadjiKomentarNaRezervaciju(@PathVariable("rezervacija-id") Long id) {
		Komentar komentar = komentarServis.nadjiKomentarRezervacije(id);
		KomentarDTO komentarDTO = new KomentarDTO(komentar);
		ResponseEntity<KomentarDTO> odgovor = new ResponseEntity<KomentarDTO>(komentarDTO, HttpStatus.OK);
		return odgovor;
	}
	
	@RequestMapping(method = RequestMethod.POST,
			value = "/api/rezervacije/{rezervacija-id}/komentar",
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<KomentarDTO> ostaviKomentarNaRezervaciju(@PathVariable("rezervacija-id") Long id,
			@RequestBody KomentarDTO komentarDTO) {		
		Komentar komentar = komentarServis.ostaviKomentarNaRezervaciju(id, komentarDTO);
		KomentarDTO kreiraniKomentarDTO = new KomentarDTO(komentar);
		ResponseEntity<KomentarDTO> odgovor = new ResponseEntity<KomentarDTO>(kreiraniKomentarDTO, HttpStatus.OK);
		return odgovor;
	}
	
	@RequestMapping(method = RequestMethod.GET,
			value = "/api/komentari/neodobreni")
	public ResponseEntity<List<KomentarDTO>> nadjiNeodobreneKomentare() {
		List<Komentar> neodobreni = komentarServis.nadjiNeodobreneKomentare();
		List<KomentarDTO> pretvoreni = PretvaranjeDTO.pretvoriKomentare(neodobreni);
		ResponseEntity<List<KomentarDTO>> odgovor = new ResponseEntity<List<KomentarDTO>>(pretvoreni, HttpStatus.OK);
		return odgovor;
	}
	
	@RequestMapping(method = RequestMethod.PUT,
			value = "/api/komentari/{id}")
	public ResponseEntity<KomentarDTO> odobriKomentar(@PathVariable("id") Long id) {
		Komentar komentar = komentarServis.odobriKomentar(id);
		KomentarDTO odobreniKomentarDTO = new KomentarDTO(komentar);
		ResponseEntity<KomentarDTO> odgovor = new ResponseEntity<KomentarDTO>(odobreniKomentarDTO, HttpStatus.OK);
		return odgovor;
	}
	
}
