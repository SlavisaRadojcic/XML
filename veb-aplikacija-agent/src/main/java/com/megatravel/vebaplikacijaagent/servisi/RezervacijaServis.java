package com.megatravel.vebaplikacijaagent.servisi;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import com.megatravel.vebaplikacijaagent.dto.RezervacijaDTO;
import com.megatravel.vebaplikacijaagent.model.Rezervacija;
import com.megatravel.vebaplikacijaagent.model.Smestaj;
import com.megatravel.vebaplikacijaagent.repozitorijumi.RezervacijaRepozitorijum;
import com.megatravel.vebaplikacijaagent.repozitorijumi.SmestajRepozitorijum;
import com.megatravel.vebaplikacijaagent.wsdl.komunikacija.SmestajneJediniceMikroservis;

@Component
public class RezervacijaServis {

	@Autowired
	private RezervacijaRepozitorijum rezervacijaRepozitorijum;
	
	@Autowired
	private SmestajRepozitorijum smestajRepozitorijum;
	
	@Autowired
	private SmestajServis smestajServis;
	
	@Autowired
	private SmestajneJediniceMikroservis smestajneJediniceMikroservis;
	
	public List<Rezervacija> rezervacijeSmestaja(Long smestaj) {
		List<Rezervacija> rezervacije = rezervacijaRepozitorijum.findAll();
		List<Rezervacija> rezultat = new ArrayList<Rezervacija>();
		for(Rezervacija rezervacija : rezervacije) {
			if(rezervacija.getSmestaj().getId().equals(smestaj)) {
				rezultat.add(rezervacija);
			}
		}
		return rezultat;
	}
	
	public Rezervacija realizujRezervaciju(Long rezervacijaId) {
		Optional<Rezervacija> rezervacija = rezervacijaRepozitorijum.findById(rezervacijaId);
		if(!rezervacija.isPresent()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		} else {
			Rezervacija rez = rezervacija.get();
			Date poslednjiDan = rez.getKraj();
			Date danasnjiDan = new Date();
			if(danasnjiDan.before(poslednjiDan)) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
			}
			rez.setRealizovana(true);
			smestajneJediniceMikroservis.potvrdiRezervaciju(rezervacijaId);
			rezervacijaRepozitorijum.save(rez);
			return rez;
		}
	}

	public Rezervacija kreiraj(RezervacijaDTO rezervacijaDTO, Long smestaj) {
		Optional<Smestaj> nadjeni = smestajRepozitorijum.findById(smestaj);
		if(nadjeni.isPresent()) {
			if(smestajServis.smestajSlobodan(nadjeni.get(), rezervacijaDTO.getPocetak(), rezervacijaDTO.getKraj())) {
				Rezervacija rezervacija = new Rezervacija();
				rezervacija.setSmestaj(nadjeni.get());
				rezervacija.setPocetak(rezervacijaDTO.getPocetak());
				rezervacija.setKraj(rezervacijaDTO.getKraj());
				rezervacija.setRealizovana(false);
				rezervacija.setKomentar(null);
				rezervacija.setOcena(null);
				long brojDana = (rezervacijaDTO.getKraj().getTime() - rezervacija.getPocetak().getTime()) / 86400000 + 1;
				rezervacija.setCena(nadjeni.get().getCena() * brojDana);
				rezervacijaRepozitorijum.save(rezervacija);
				if(!smestajneJediniceMikroservis.posaljiRezervaciju(rezervacija)) {
					throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
				}
				return rezervacija;
			}
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
		throw new ResponseStatusException(HttpStatus.NOT_FOUND);
	}
	
}
