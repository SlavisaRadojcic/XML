package com.megatravel.mikroservissmestajnejedinice.servisi;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import com.megatravel.mikroservissmestajnejedinice.dto.RezervacijaDTO;
import com.megatravel.mikroservissmestajnejedinice.model.Rezervacija;
import com.megatravel.mikroservissmestajnejedinice.model.Smestaj;
import com.megatravel.mikroservissmestajnejedinice.repozitorijumi.RezervacijaRepozitorijum;
import com.megatravel.mikroservissmestajnejedinice.repozitorijumi.SmestajRepozitorijum;

@Component
public class RezervacijaServis {

	@Autowired
	private RezervacijaRepozitorijum rezervacijaRepozitorijum;
	
	@Autowired
	private SmestajRepozitorijum smestajRepozitorijum;
	
	@Autowired
	private SmestajServis smestajServis;
	
	public List<Rezervacija> nadjiIstoriju(Long korisnik) {
		List<Rezervacija> rezervacije = nadjiRezervacije(korisnik);
		List<Rezervacija> istorija = new ArrayList<Rezervacija>();
		Date vreme = new Date();
		for(Rezervacija rezervacija : rezervacije) {
			if(rezervacija.getKraj().before(vreme)) {
				istorija.add(rezervacija);
			}
		}
		return istorija;
	}

	public List<Rezervacija> nadjiAktivne(Long korisnik) {
		List<Rezervacija> rezervacije = nadjiRezervacije(korisnik);
		List<Rezervacija> aktivne = new ArrayList<Rezervacija>();
		Date vreme = new Date();
		for(Rezervacija rezervacija : rezervacije) {
			if(rezervacija.getKraj().after(vreme)) {
				aktivne.add(rezervacija);
			}
		}
		return aktivne;
	}

	public List<Rezervacija> nadjiRezervacije(Long korisnik) {
		List<Rezervacija> sve = rezervacijaRepozitorijum.findAll();
		List<Rezervacija> rezervacije = new ArrayList<>();
		for(Rezervacija rezervacija : sve) {
			if(rezervacija.getKorisnik() == korisnik) {
				rezervacije.add(rezervacija);
			}
		}
		return rezervacije;
	}

	public Rezervacija napraviRezervaciju(RezervacijaDTO rezervacijaDTO, Long smestaj, Long korisnik) {
		Optional<Smestaj> nadjeni = smestajRepozitorijum.findById(smestaj);
		if(nadjeni.isPresent()) {
			if(smestajServis.smestajSlobodan(nadjeni.get(), rezervacijaDTO.getPocetak(), rezervacijaDTO.getKraj())) {
				Rezervacija rezervacija = new Rezervacija();
				rezervacija.setSmestaj(nadjeni.get());
				rezervacija.setKorisnik(korisnik);
				rezervacija.setPocetak(rezervacijaDTO.getPocetak());
				rezervacija.setKraj(rezervacijaDTO.getKraj());
				rezervacija.setRealizovana(false);
				rezervacija.setKomentar(null);
				rezervacija.setOcena(null);
				long brojDana = (rezervacijaDTO.getKraj().getTime() - rezervacija.getPocetak().getTime()) / 86400000;
				rezervacija.setCena(nadjeni.get().getCena() * brojDana);
			}
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
		throw new ResponseStatusException(HttpStatus.NOT_FOUND);
	}
	
	public void otkaziRezervaciju(Long id) {
		Optional<Rezervacija> rezervacija = rezervacijaRepozitorijum.findById(id);
		if(rezervacija.isPresent()) {
			Smestaj smestaj = rezervacija.get().getSmestaj();
			if(smestaj.isDozvoljenoOtkazivanje()) {
				Date vreme = new Date();
				long brojDana = (rezervacija.get().getPocetak().getTime() - vreme.getTime()) / 86400000;
				if(brojDana >= smestaj.getBrojDanaZaOtkazivanje()) {
					rezervacijaRepozitorijum.deleteById(id);
				} else {
					throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
				}
			} else {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
			}
		}
		throw new ResponseStatusException(HttpStatus.NOT_FOUND);
	}

	public Long nadjiKorisnikaRezervacija(Long id) {
		Optional<Rezervacija> rezervacija = rezervacijaRepozitorijum.findById(id);
		if(rezervacija.isPresent()) {
			Rezervacija rez = rezervacija.get();
			if(rez.getKorisnik() != null) {
				return rez.getKorisnik();
			}
		}
		throw new ResponseStatusException(HttpStatus.NOT_FOUND);
	}

	public List<Long> nadjiDrugeRezervacije(Long id) {
		Optional<Rezervacija> rezervacija = rezervacijaRepozitorijum.findById(id);
		if(rezervacija.isPresent()) {
			Smestaj smestaj = rezervacija.get().getSmestaj();
			List<Long> rezervacije = new ArrayList<Long>();
			for(Rezervacija trenutna : smestaj.getRezervacije()) {
				rezervacije.add(trenutna.getId());
			}
			return rezervacije;
		}
		throw new ResponseStatusException(HttpStatus.NOT_FOUND);
	}
	
}
