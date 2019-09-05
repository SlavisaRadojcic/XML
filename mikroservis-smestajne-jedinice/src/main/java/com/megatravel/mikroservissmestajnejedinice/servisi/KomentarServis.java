package com.megatravel.mikroservissmestajnejedinice.servisi;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import com.megatravel.mikroservissmestajnejedinice.dto.KomentarDTO;
import com.megatravel.mikroservissmestajnejedinice.model.Komentar;
import com.megatravel.mikroservissmestajnejedinice.model.Rezervacija;
import com.megatravel.mikroservissmestajnejedinice.repozitorijumi.KomentarRepozitorijum;
import com.megatravel.mikroservissmestajnejedinice.repozitorijumi.RezervacijaRepozitorijum;

@Component
public class KomentarServis {

	@Autowired
	private RezervacijaRepozitorijum rezervacijaRepozitorijum;
	
	@Autowired
	private KomentarRepozitorijum komentarRepozitorijum;
	
	public Komentar nadjiKomentarRezervacije(Long id) {
		Optional<Rezervacija> rezervacija = rezervacijaRepozitorijum.findById(id);
		if(rezervacija.isPresent() && rezervacija.get().getKomentar() != null) {
			return rezervacija.get().getKomentar();
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}

	public Komentar ostaviKomentarNaRezervaciju(Long id, KomentarDTO komentarDTO) {
		Optional<Rezervacija> rezervacija = rezervacijaRepozitorijum.findById(id);
		if(rezervacija.isPresent() && rezervacija.get().getKomentar() == null && rezervacija.get().isRealizovana()) {
			Komentar komentar = new Komentar();
			komentar.setOdobren(false);
			komentar.setRezervacija(rezervacija.get());
			komentar.setTekst(komentarDTO.getTekst());
			komentarRepozitorijum.save(komentar);
			return komentar;
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}
	
	public List<Komentar> nadjiNeodobreneKomentare() {
		List<Komentar> komentari = komentarRepozitorijum.findAll();
		List<Komentar> neodobreni = new ArrayList<>();
		for(Komentar komentar : komentari) {
			if(komentar.isOdobren()) continue;
			neodobreni.add(komentar);
		}
		return neodobreni;
	}

	public Komentar odobriKomentar(Long id) {
		Optional<Komentar> komentar = komentarRepozitorijum.findById(id);
		if(komentar.isPresent() && !komentar.get().isOdobren()) {
			komentar.get().setOdobren(true);
			komentarRepozitorijum.save(komentar.get());
			return komentar.get();
		}
		throw new ResponseStatusException(HttpStatus.NOT_FOUND);
	}
	
}
