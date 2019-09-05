package com.megatravel.vebaplikacijaagent.servisi;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import com.megatravel.vebaplikacijaagent.dto.AdresaDTO;
import com.megatravel.vebaplikacijaagent.dto.SmestajDTO;
import com.megatravel.vebaplikacijaagent.dto.UslugaDTO;
import com.megatravel.vebaplikacijaagent.model.Adresa;
import com.megatravel.vebaplikacijaagent.model.Korisnik;
import com.megatravel.vebaplikacijaagent.model.Rezervacija;
import com.megatravel.vebaplikacijaagent.model.Smestaj;
import com.megatravel.vebaplikacijaagent.model.TipKorisnika;
import com.megatravel.vebaplikacijaagent.model.TipSmestaja;
import com.megatravel.vebaplikacijaagent.model.Usluga;
import com.megatravel.vebaplikacijaagent.repozitorijumi.AdresaRepozitorijum;
import com.megatravel.vebaplikacijaagent.repozitorijumi.KorisnikRepozitorijum;
import com.megatravel.vebaplikacijaagent.repozitorijumi.SmestajRepozitorijum;
import com.megatravel.vebaplikacijaagent.repozitorijumi.TipSmestajaRepozitorijum;
import com.megatravel.vebaplikacijaagent.repozitorijumi.UslugaRepozitorijum;
import com.megatravel.vebaplikacijaagent.wsdl.komunikacija.SmestajneJediniceMikroservis;

@Component
public class SmestajServis {

	@Autowired
	private SmestajRepozitorijum smestajRepozitorijum;
	
	@Autowired
	private KorisnikRepozitorijum korisnikRepozitorijum;
	
	@Autowired
	private TipSmestajaRepozitorijum tipSmestajaRepozitorijum;
	
	@Autowired
	private AdresaRepozitorijum adresaRepozitorijum;
	
	@Autowired
	private UslugaRepozitorijum uslugaRepozitorijum;
	
	@Autowired
	private SmestajneJediniceMikroservis smestajneJediniceMikroservis;
	
	public List<Smestaj> agentoviSmestaji(Long id) {
		List<Smestaj> smestaji = smestajRepozitorijum.findAll();
		List<Smestaj> rezultat = new ArrayList<Smestaj>();
		for(Smestaj smestaj : smestaji) {
			if(smestaj.getAgent().getId().equals(id)) {
				rezultat.add(smestaj);
			}
		}
		return rezultat;
	}

	public Smestaj kreiraj(SmestajDTO smestajDTO, Long agentID) {
		Korisnik agent = nadjiAgenta(agentID);
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
		smestaj.setAgent(agent);
		smestaj.setUsluge(usluge);
		smestajRepozitorijum.save(smestaj);
		Adresa adresa = kreirajAdresu(smestajDTO.getAdresaDTO(), smestaj);
		smestaj.setAdresa(adresa);
		// TODO : Proveri da li poveze adresu
		if(!smestajneJediniceMikroservis.posaljiSmestajnuJedinicu(smestaj)) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
		return smestaj;
	}
	
	private Korisnik nadjiAgenta(Long agentId) {
		Optional<Korisnik> agentOpt = korisnikRepozitorijum.findById(agentId);
		if(!agentOpt.isPresent()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		Korisnik agent = agentOpt.get();
		if(!agent.getTip().equals(TipKorisnika.AGENT)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		return agent;
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
	
}
