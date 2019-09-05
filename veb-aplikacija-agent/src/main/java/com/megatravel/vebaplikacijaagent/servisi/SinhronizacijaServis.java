package com.megatravel.vebaplikacijaagent.servisi;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.megatravel.vebaplikacijaagent.model.Adresa;
import com.megatravel.vebaplikacijaagent.model.Komentar;
import com.megatravel.vebaplikacijaagent.model.Korisnik;
import com.megatravel.vebaplikacijaagent.model.Ocena;
import com.megatravel.vebaplikacijaagent.model.Rezervacija;
import com.megatravel.vebaplikacijaagent.model.Smestaj;
import com.megatravel.vebaplikacijaagent.model.StatusKorisnika;
import com.megatravel.vebaplikacijaagent.model.TipKorisnika;
import com.megatravel.vebaplikacijaagent.model.TipSmestaja;
import com.megatravel.vebaplikacijaagent.model.Usluga;
import com.megatravel.vebaplikacijaagent.repozitorijumi.AdreseRepozitorijum;
import com.megatravel.vebaplikacijaagent.repozitorijumi.KomentarRepozitorijum;
import com.megatravel.vebaplikacijaagent.repozitorijumi.KorisnikRepozitorijum;
import com.megatravel.vebaplikacijaagent.repozitorijumi.OcenaRepozitorijum;
import com.megatravel.vebaplikacijaagent.repozitorijumi.RezervacijaRepozitorijum;
import com.megatravel.vebaplikacijaagent.repozitorijumi.SmestajRepozitorijum;
import com.megatravel.vebaplikacijaagent.repozitorijumi.TipSmestajaRepozitorijum;
import com.megatravel.vebaplikacijaagent.repozitorijumi.UslugaRepozitorijum;
import com.megatravel.vebaplikacijaagent.wsdl.AdresaDTO;
import com.megatravel.vebaplikacijaagent.wsdl.KomentarDTO;
import com.megatravel.vebaplikacijaagent.wsdl.KorisnikDTO;
import com.megatravel.vebaplikacijaagent.wsdl.OcenaDTO;
import com.megatravel.vebaplikacijaagent.wsdl.RezervacijaDTO;
import com.megatravel.vebaplikacijaagent.wsdl.SmestajDTO;
import com.megatravel.vebaplikacijaagent.wsdl.TipSmestajaDTO;
import com.megatravel.vebaplikacijaagent.wsdl.UslugaDTO;
import com.megatravel.vebaplikacijaagent.wsdl.komunikacija.KorisniciMikroservis;
import com.megatravel.vebaplikacijaagent.wsdl.komunikacija.OceneMikroservis;
import com.megatravel.vebaplikacijaagent.wsdl.komunikacija.SmestajneJediniceMikroservis;

@Service
public class SinhronizacijaServis {

	@Autowired
	private KorisniciMikroservis korisniciMikroservis;
	
	@Autowired
	private SmestajneJediniceMikroservis smestajneJediniceMikroservis;
	
	@Autowired
	private OceneMikroservis oceneMikroservis;
	
	public void sinhronizujSve() {
		korisniciMikroservis.dodajSveKorisnike();
		smestajneJediniceMikroservis.dodajSveAdrese();
		smestajneJediniceMikroservis.dodajSveTipove();
		smestajneJediniceMikroservis.dodajSveUsluge();
		smestajneJediniceMikroservis.dodajSveSmestaje();
		smestajneJediniceMikroservis.dodajSveRezervacije();
		smestajneJediniceMikroservis.dodajSveKomentare();
		oceneMikroservis.dodajSveOcene();
	}
	
	@Autowired
	private KorisnikRepozitorijum korisnikRepozitorjium;

	public void dodajKorisnika(KorisnikDTO korisnikDTO) {
		Long id = korisnikDTO.getId();
		if(korisnikRepozitorjium.findById(id).isPresent()) return;
		Korisnik korisnik = new Korisnik();
		korisnik.setId(id);
		korisnik.setIme(korisnikDTO.getIme());
		korisnik.setLozinka(korisnikDTO.getSifra());
		korisnik.setMejl(korisnikDTO.getMejl());
		korisnik.setPoslovniMaticniBroj(korisnikDTO.getPoslovniMaticniBroj());
		korisnik.setPrezime(korisnikDTO.getPrezime());
		switch(korisnikDTO.getStatus()) {
			case AKTIVAN : korisnik.setStatus(StatusKorisnika.AKTIVAN); break;
			case BLOKIRAN : korisnik.setStatus(StatusKorisnika.BLOKIRAN); break;
		}
		switch(korisnikDTO.getTip()) {
			case AGENT : korisnik.setTip(TipKorisnika.AGENT); break;
			case ADMINISTRATOR : korisnik.setTip(TipKorisnika.ADMINISTRATOR); break;
			case KORISNIK : korisnik.setTip(TipKorisnika.KORISNIK); break;
		}
		this.korisnikRepozitorjium.save(korisnik);
	}

	@Autowired
	private AdreseRepozitorijum adreseRepozitorijum;
	
	public void dodajAdresu(AdresaDTO adresaDTO) {
		Long id = adresaDTO.getId();
		if(adreseRepozitorijum.findById(id).isPresent()) return;
		Adresa adresa = new Adresa();
		adresa.setId(id);
		adresa.setBroj(adresaDTO.getBroj());
		adresa.setGrad(adresaDTO.getGrad());
		adresa.setUlica(adresaDTO.getUlica());
		adresa.setZemlja(adresaDTO.getZemlja());
		adreseRepozitorijum.save(adresa);
	}

	@Autowired
	private TipSmestajaRepozitorijum tipSmestajaRepozitorijum;
	
	public void dodajTip(TipSmestajaDTO tipDTO) {
		Long id = tipDTO.getId();
		if(tipSmestajaRepozitorijum.findById(id).isPresent()) return;
		TipSmestaja tip = new TipSmestaja();
		tip.setId(id);
		tip.setNaziv(tipDTO.getNaziv());
		tipSmestajaRepozitorijum.save(tip);
	}

	@Autowired
	private UslugaRepozitorijum uslugaRepozitorijum;
	
	public void dodajUslugu(UslugaDTO uslugaDTO) {
		Long id = uslugaDTO.getId();
		if(uslugaRepozitorijum.findById(id).isPresent()) return;
		Usluga usluga = new Usluga();
		usluga.setId(uslugaDTO.getId());
		usluga.setNaziv(uslugaDTO.getNaziv());
		usluga.setOpis(uslugaDTO.getOpis());
		uslugaRepozitorijum.save(usluga);
	}

	@Autowired
	private SmestajRepozitorijum smestajRepozitorijum;
	
	public void dodajSmestaj(SmestajDTO smestajDTO) {
		Long id = smestajDTO.getId();
		if(smestajRepozitorijum.findById(id).isPresent()) return;
		Smestaj smestaj = new Smestaj();
		smestaj.setId(id);
		smestaj.setBrojDanaZaOtkazivanje(smestajDTO.getBrojDanaZaOtkazivanje());
		smestaj.setCena(smestajDTO.getCena());
		smestaj.setDozvoljenoOtkazivanje(smestajDTO.isDozvoljenoOtkazivanje());
		smestaj.setKapacitet(smestajDTO.getKapacitet());
		smestaj.setOcena(smestajDTO.getOcena());
		smestaj.setOpis(smestajDTO.getOpis());
		Adresa adresa = adreseRepozitorijum.findById(smestajDTO.getAdresaDTO().getId()).get();
		TipSmestaja tip = tipSmestajaRepozitorijum.findById(smestajDTO.getTipDTO().getId()).get();
		smestaj.setTip(tip);
		smestaj.setUsluge(new ArrayList<Usluga>());
		for(UslugaDTO uslugaDTO : smestajDTO.getUslugeDTO()) {
			smestaj.getUsluge().add(uslugaRepozitorijum.findById(uslugaDTO.getId()).get());
		}
		smestaj.setAgent(korisnikRepozitorjium.findById(smestajDTO.getAgent()).get());
		smestajRepozitorijum.save(smestaj);
		adresa.setSmestaj(smestaj);
		adreseRepozitorijum.save(adresa);
	}

	@Autowired
	private RezervacijaRepozitorijum rezervacijaRepozitorijum;
	
	public void dodajRezervaciju(RezervacijaDTO rezervacijaDTO) {
		Long id = rezervacijaDTO.getId();
		if(rezervacijaRepozitorijum.findById(id).isPresent()) return;
		Rezervacija rezervacija = new Rezervacija();
		rezervacija.setId(rezervacijaDTO.getId());
		rezervacija.setCena(rezervacijaDTO.getCena());
		rezervacija.setRealizovana(rezervacijaDTO.isRealizovana());
		if(rezervacijaDTO.getKorisnik() != 0) rezervacija.setKorisnik(korisnikRepozitorjium.findById(rezervacijaDTO.getKorisnik()).get());
		rezervacija.setSmestaj(smestajRepozitorijum.findById(rezervacijaDTO.getSmestaj()).get());
		rezervacija.setPocetak(rezervacijaDTO.getPocetak().toGregorianCalendar().getTime());
		rezervacija.setKraj(rezervacijaDTO.getKraj().toGregorianCalendar().getTime());
		rezervacijaRepozitorijum.save(rezervacija);
	}

	@Autowired
	private KomentarRepozitorijum komentarRepozitorijum;
	
	public void dodajKomentar(KomentarDTO komentarDTO) {
		Long id = komentarDTO.getId();
		if(komentarRepozitorijum.findById(id).isPresent()) return;
		Komentar komentar = new Komentar();
		komentar.setId(komentarDTO.getId());
		komentar.setOdobren(komentarDTO.isOdobren());
		komentar.setTekst(komentarDTO.getTekst());
		komentar.setRezervacija(rezervacijaRepozitorijum.findById(komentarDTO.getRezervacija()).get());
		komentarRepozitorijum.save(komentar);
	}

	@Autowired
	private OcenaRepozitorijum ocenaRepozitorijum;
	
	public void dodajOcenu(OcenaDTO ocenaDTO) {
		Long id = ocenaDTO.getId();
		if(ocenaRepozitorijum.findById(id).isPresent()) return;
		Ocena ocena = new Ocena();
		ocena.setId(ocenaDTO.getId());
		ocena.setVrednost(ocenaDTO.getVrednost());
		ocena.setRezervacija(rezervacijaRepozitorijum.findById(ocenaDTO.getId()).get());
		ocenaRepozitorijum.save(ocena);
	}
	
	

}
