package com.megatravel.mikroserviskorisnici.servisi;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import com.megatravel.mikroserviskorisnici.dto.KorisnikDTO;
import com.megatravel.mikroserviskorisnici.model.Korisnik;
import com.megatravel.mikroserviskorisnici.model.StatusKorisnika;
import com.megatravel.mikroserviskorisnici.model.TipKorisnika;
import com.megatravel.mikroserviskorisnici.repozitorijumi.KorisnikRepozitorijum;

@Component
public class AgentServis {

	@Autowired
	private KorisnikRepozitorijum agentRepozitorijum;

	public List<Korisnik> nadjiSve() {
		List<Korisnik> korisnici = agentRepozitorijum.findAll();
		List<Korisnik> agenti = new ArrayList<>();
		for(Korisnik korisnik : korisnici) {
			if(korisnik.getTip().equals(TipKorisnika.AGENT)) {
				agenti.add(korisnik);
			}
		}
		return agenti;
	}
	
	public Korisnik nadjiJednog(Long id) {
		Optional<Korisnik> korisnik = agentRepozitorijum.findById(id);
		if(korisnik.isPresent()) {
			if(korisnik.get().getTip().equals(TipKorisnika.AGENT)) {
				return korisnik.get();
			} else {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
			}
		}
		throw new ResponseStatusException(HttpStatus.NOT_FOUND);
	}
	
	public Korisnik dodaj(KorisnikDTO korisnikDTO) {
		Korisnik agent = new Korisnik();
		if(!mejlJeZauzet(korisnikDTO.getMejl())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		} else {
			agent.setIme(korisnikDTO.getIme());
			agent.setPrezime(korisnikDTO.getPrezime());
			agent.setMejl(korisnikDTO.getMejl());
			agent.setLozinka(korisnikDTO.getSifra());
			agent.setPoslovniMaticniBroj(korisnikDTO.getPoslovniMaticniBroj());
			agent.setStatus(StatusKorisnika.AKTIVAN);
			agent.setTip(TipKorisnika.AGENT);
			agentRepozitorijum.save(agent);
			return agent;
		}
	}
	
	private boolean mejlJeZauzet(String mejl) {
		List<Korisnik> korisnici = agentRepozitorijum.findAll();
		for(Korisnik korisnik : korisnici) {
			if(korisnik.getMejl().equals(mejl)) {
				return false;
			}
		}
		return true;
	}
	
}
