package com.megatravel.mikroservissmestajnejedinice.servisi;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import com.megatravel.mikroservissmestajnejedinice.dto.KriterijumiPretrageDTO;
import com.megatravel.mikroservissmestajnejedinice.model.Adresa;
import com.megatravel.mikroservissmestajnejedinice.model.Rezervacija;
import com.megatravel.mikroservissmestajnejedinice.model.Smestaj;
import com.megatravel.mikroservissmestajnejedinice.model.TipSmestaja;
import com.megatravel.mikroservissmestajnejedinice.model.Usluga;
import com.megatravel.mikroservissmestajnejedinice.repozitorijumi.AdresaRepozitorijum;
import com.megatravel.mikroservissmestajnejedinice.repozitorijumi.RezervacijaRepozitorijum;
import com.megatravel.mikroservissmestajnejedinice.repozitorijumi.SmestajRepozitorijum;
import com.megatravel.mikroservissmestajnejedinice.repozitorijumi.TipSmestajaRepozitorijum;
import com.megatravel.mikroservissmestajnejedinice.repozitorijumi.UslugaRepozitorijum;
import com.megatravel.mikroservissmestajnejedinice.xml.AdresaDTO;
import com.megatravel.mikroservissmestajnejedinice.xml.SmestajDTO;
import com.megatravel.mikroservissmestajnejedinice.xml.UslugaDTO;

@Component
public class SmestajServis {

	@Autowired
	private SmestajRepozitorijum smestajRepozitorijum;
	
	@Autowired
	private RezervacijaRepozitorijum rezervacijaRepozitorijum;
	
	@Autowired
	private TipSmestajaRepozitorijum tipSmestajaRepozitorijum;
	
	@Autowired
	private AdresaRepozitorijum adresaRepozitorijum;
	
	@Autowired
	private UslugaRepozitorijum uslugaRepozitorijum;
	
	public List<Smestaj> nadjiSve(KriterijumiPretrageDTO kriterijumiPretrageDTO) {
		List<Smestaj> smestaji = smestajRepozitorijum.findAll();
		if(trebaPretraziti(kriterijumiPretrageDTO)) {
			List<Smestaj> filtrirano = new ArrayList<Smestaj>();
			for(Smestaj smestaj : smestaji) {
				if(!smestaj.getAdresa().getZemlja().contentEquals(kriterijumiPretrageDTO.getZemlja())) continue;
				if(!smestaj.getAdresa().getGrad().contentEquals(kriterijumiPretrageDTO.getGrad())) continue;
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
			if(pocetak.after(rezervacija.getPocetak()) && pocetak.before(rezervacija.getKraj()))
				return false;
			if(kraj.after(rezervacija.getPocetak()) && kraj.before(rezervacija.getKraj()))
				return false;
			if(pocetak.before(rezervacija.getPocetak()) && kraj.after(rezervacija.getKraj()))
				return false;
			if(pocetak.equals(rezervacija.getPocetak()) || pocetak.equals(rezervacija.getKraj()))
				return false;
			if(kraj.equals(rezervacija.getPocetak()) || kraj.equals(rezervacija.getKraj()))
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

	public List<Smestaj> nadjiSve() {
		return smestajRepozitorijum.findAll();
	}
	
	@Transactional
	public Smestaj kreiraj(SmestajDTO smestajDTO, Long agentID) {
		TipSmestaja tip = nadjiTipSmestaja(smestajDTO.getTipDTO().getId());
		List<Usluga> usluge = nadjiUsluge(smestajDTO.getUslugeDTO());
		Smestaj smestaj = new Smestaj();
		smestaj.setBrojDanaZaOtkazivanje(smestajDTO.getBrojDanaZaOtkazivanje());
		smestaj.setCena(smestajDTO.getCena());
		smestaj.setDozvoljenoOtkazivanje(smestajDTO.isDozvoljenoOtkazivanje());
		smestaj.setKapacitet(smestajDTO.getKapacitet());
		smestaj.setOpis(smestajDTO.getOpis());
		smestaj.setOcena(0);
		smestaj.setTip(tip);
		smestaj.setAgent(agentID);
		smestaj.setUsluge(usluge);
		smestajRepozitorijum.save(smestaj);
		Adresa adresa = kreirajAdresu(smestajDTO.getAdresaDTO(), smestaj);
		smestaj.setAdresa(adresa);
		return smestaj;
	}
	
	private TipSmestaja nadjiTipSmestaja(Long tipId) {
		Optional<TipSmestaja> tipOpt = tipSmestajaRepozitorijum.findById(tipId);
		if(!tipOpt.isPresent()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		return tipOpt.get();
	}
	
	private Adresa kreirajAdresu(AdresaDTO adresaDTO, Smestaj smestaj) {
		Adresa adresa = new Adresa();
		adresa.setZemlja(adresaDTO.getZemlja());
		adresa.setGrad(adresaDTO.getGrad());
		adresa.setUlica(adresaDTO.getUlica());
		adresa.setBroj(adresaDTO.getBroj());
		adresa.setSmestaj(smestaj);
		adresaRepozitorijum.save(adresa);
		return adresa;
	}
	
	private List<Usluga> nadjiUsluge(List<UslugaDTO> uslugeDTO) {
		List<Usluga> rezultat = new ArrayList<Usluga>();
		for(UslugaDTO uslugaDTO : uslugeDTO) {
			Optional<Usluga> uslugaOpt = uslugaRepozitorijum.findById(uslugaDTO.getId());
			if(!uslugaOpt.isPresent()) {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND);
			}
			rezultat.add(uslugaOpt.get());
		}
		return rezultat;
	}
	
}
