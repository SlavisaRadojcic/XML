package com.megatravel.mikroservissmestajnejedinice.servisi;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import com.megatravel.mikroservissmestajnejedinice.dto.TipSmestajaDTO;
import com.megatravel.mikroservissmestajnejedinice.model.TipSmestaja;
import com.megatravel.mikroservissmestajnejedinice.repozitorijumi.TipSmestajaRepozitorijum;

@Component
public class TipSmestajaServis {

	@Autowired
	private TipSmestajaRepozitorijum tipSmestajaRepozitorijum;

	public List<TipSmestaja> nadjiSve() {
		return tipSmestajaRepozitorijum.findAll();
	}

	public TipSmestaja dodajTipSmestaja(TipSmestajaDTO tipSmestajaDTO) {
		TipSmestaja tip = new TipSmestaja();
		tip.setNaziv(tipSmestajaDTO.getNaziv());
		tipSmestajaRepozitorijum.save(tip);
		return tip;
	}

	public TipSmestaja azurirajTipSmestaja(TipSmestajaDTO tipSmestajaDTO, Long id) {
		Optional<TipSmestaja> tip = tipSmestajaRepozitorijum.findById(id);
		if(tip.isPresent()) {
			tip.get().setNaziv(tipSmestajaDTO.getNaziv());
			tipSmestajaRepozitorijum.save(tip.get());
			return tip.get();
		}
		throw new ResponseStatusException(HttpStatus.NOT_FOUND);
	}
	
	public void obrisiTipSmestaja(Long id) {
		Optional<TipSmestaja> tip = tipSmestajaRepozitorijum.findById(id);
		if(tip.isPresent() && tip.get().getSmestaji().isEmpty()) {
			tipSmestajaRepozitorijum.delete(tip.get());
			return;
		}
		throw new ResponseStatusException(HttpStatus.NOT_FOUND);
	}
	
}
