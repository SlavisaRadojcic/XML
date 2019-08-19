package com.megatravel.mikroservissmestajnejedinice.servisi;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import com.megatravel.mikroservissmestajnejedinice.dto.UslugaDTO;
import com.megatravel.mikroservissmestajnejedinice.model.Usluga;
import com.megatravel.mikroservissmestajnejedinice.repozitorijumi.UslugaRepozitorijum;

@Component
public class UslugaServis {

	@Autowired
	private UslugaRepozitorijum uslugaRepozitorijum;

	public List<Usluga> nadjiSve() {
		return uslugaRepozitorijum.findAll();
	}

	public Usluga dodajUslugu(UslugaDTO uslugaDTO) {
		Usluga usluga = new Usluga();
		usluga.setNaziv(uslugaDTO.getNaziv());
		usluga.setOpis(uslugaDTO.getOpis());
		uslugaRepozitorijum.save(usluga);
		return usluga;
	}

	public Usluga azurirajUslugu(UslugaDTO uslugaDTO, Long id) {
		Optional<Usluga> usluga = uslugaRepozitorijum.findById(id);
		if(usluga.isPresent() && usluga.get().getSmestaji().isEmpty()) {
			usluga.get().setNaziv(uslugaDTO.getNaziv());
			usluga.get().setOpis(uslugaDTO.getOpis());
			uslugaRepozitorijum.save(usluga.get());
			return usluga.get();
		}
		throw new ResponseStatusException(HttpStatus.NOT_FOUND);
	}

	public void obrisiUslugu(Long id) {
		Optional<Usluga> usluga = uslugaRepozitorijum.findById(id);
		if(usluga.isPresent() && usluga.get().getSmestaji().isEmpty()) {
			uslugaRepozitorijum.delete(usluga.get());
		}
		throw new ResponseStatusException(HttpStatus.NOT_FOUND);
	}
	
}
