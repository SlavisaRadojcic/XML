package com.megatravel.mikroservissmestajnejedinice.servisi;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import com.megatravel.mikroservissmestajnejedinice.dto.KriterijumiPretrageDTO;
import com.megatravel.mikroservissmestajnejedinice.model.Rezervacija;
import com.megatravel.mikroservissmestajnejedinice.model.Smestaj;
import com.megatravel.mikroservissmestajnejedinice.repozitorijumi.RezervacijaRepozitorijum;
import com.megatravel.mikroservissmestajnejedinice.repozitorijumi.SmestajRepozitorijum;

@Component
public class SmestajServis {

	@Autowired
	private SmestajRepozitorijum smestajRepozitorijum;
	
	@Autowired
	private RezervacijaRepozitorijum rezervacijaRepozitorijum;
	
	public List<Smestaj> nadjiSve(KriterijumiPretrageDTO kriterijumiPretrageDTO) {
		List<Smestaj> smestaji = smestajRepozitorijum.findAll();
		if(trebaPretraziti(kriterijumiPretrageDTO)) {
			List<Smestaj> filtrirano = new ArrayList<Smestaj>();
			for(Smestaj smestaj : smestaji) {
				if(smestaj.getAdresa().getZemlja() != kriterijumiPretrageDTO.getZemlja()) continue;
				if(smestaj.getAdresa().getGrad() != kriterijumiPretrageDTO.getGrad()) continue;
				if(smestaj.getKapacitet() < kriterijumiPretrageDTO.getBrojLjudi()) continue;
				if(!smestajSlobodan(smestaj, kriterijumiPretrageDTO.getPocetak(), kriterijumiPretrageDTO.getKraj())) continue;
				filtrirano.add(smestaj);
			}
			return filtrirano;
		} else {
			return smestaji;
		}
	}

	public boolean trebaPretraziti(KriterijumiPretrageDTO kriterijumiPretrageDTO) {
		if(kriterijumiPretrageDTO.getZemlja() == null) return false;
		if(kriterijumiPretrageDTO.getGrad() == null) return false;
		if(kriterijumiPretrageDTO.getBrojLjudi() <= 0) return false;
		if(kriterijumiPretrageDTO.getPocetak() == null) return false;
		if(kriterijumiPretrageDTO.getKraj() == null) return false;
		return true;
	}
	
	public boolean smestajSlobodan(Smestaj smestaj, Date pocetak, Date kraj) {
		for(Rezervacija rezervacija : smestaj.getRezervacije()) {
			if(rezervacija.getPocetak().after(pocetak) && rezervacija.getPocetak().before(kraj))
				return false;
			if(rezervacija.getKraj().after(pocetak) && rezervacija.getKraj().before(kraj))
				return false;
		}
		return true;
	}

	public Smestaj postaviOcenu(double ocena, Long rezervacijaId) {
		Optional<Rezervacija> rezervacija = this.rezervacijaRepozitorijum.findById(rezervacijaId);
		if(!rezervacija.isPresent()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		} else {
			Smestaj smestaj = rezervacija.get().getSmestaj();
			smestaj.setOcena(ocena);
			this.smestajRepozitorijum.save(smestaj);
			return smestaj;
		}
	}
	
}
