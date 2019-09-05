package com.megatravel.vebaplikacijaagent.wsdl.komunikacija;

import java.util.GregorianCalendar;
import java.util.List;

import javax.xml.datatype.DatatypeFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Service;
import org.springframework.ws.client.core.WebServiceTemplate;

import com.megatravel.vebaplikacijaagent.model.Adresa;
import com.megatravel.vebaplikacijaagent.model.Rezervacija;
import com.megatravel.vebaplikacijaagent.model.Smestaj;
import com.megatravel.vebaplikacijaagent.model.TipSmestaja;
import com.megatravel.vebaplikacijaagent.model.Usluga;
import com.megatravel.vebaplikacijaagent.servisi.SinhronizacijaServis;
import com.megatravel.vebaplikacijaagent.wsdl.AdresaDTO;
import com.megatravel.vebaplikacijaagent.wsdl.DodavanjeRezervacijeRequest;
import com.megatravel.vebaplikacijaagent.wsdl.DodavanjeRezervacijeResponse;
import com.megatravel.vebaplikacijaagent.wsdl.DodavanjeSmestajneJediniceRequest;
import com.megatravel.vebaplikacijaagent.wsdl.DodavanjeSmestajneJediniceResponse;
import com.megatravel.vebaplikacijaagent.wsdl.KomentarDTO;
import com.megatravel.vebaplikacijaagent.wsdl.PotvrdaRezervacijeRequest;
import com.megatravel.vebaplikacijaagent.wsdl.PotvrdaRezervacijeResponse;
import com.megatravel.vebaplikacijaagent.wsdl.RezervacijaDTO;
import com.megatravel.vebaplikacijaagent.wsdl.SmestajDTO;
import com.megatravel.vebaplikacijaagent.wsdl.SveAdreseRequest;
import com.megatravel.vebaplikacijaagent.wsdl.SveAdreseResponse;
import com.megatravel.vebaplikacijaagent.wsdl.SveRezervacijeRequest;
import com.megatravel.vebaplikacijaagent.wsdl.SveRezervacijeResponse;
import com.megatravel.vebaplikacijaagent.wsdl.SveUslugeRequest;
import com.megatravel.vebaplikacijaagent.wsdl.SveUslugeResponse;
import com.megatravel.vebaplikacijaagent.wsdl.SviKomentariRequest;
import com.megatravel.vebaplikacijaagent.wsdl.SviKomentariResponse;
import com.megatravel.vebaplikacijaagent.wsdl.SviSmestajiRequest;
import com.megatravel.vebaplikacijaagent.wsdl.SviSmestajiResponse;
import com.megatravel.vebaplikacijaagent.wsdl.SviTipoviRequest;
import com.megatravel.vebaplikacijaagent.wsdl.SviTipoviResponse;
import com.megatravel.vebaplikacijaagent.wsdl.TipSmestajaDTO;
import com.megatravel.vebaplikacijaagent.wsdl.UslugaDTO;

@Service
public class SmestajneJediniceMikroservis {

	private static final String URI = "http://localhost:9999/mikroservis-smestajne-jedinice/servisi";
	
	@Autowired
	private Jaxb2Marshaller marshaller;
	
	private WebServiceTemplate template;
	
	@Autowired
	private SinhronizacijaServis sinhronizacijaServis;
	
	public void dodajSveAdrese() {
		this.template = new WebServiceTemplate(this.marshaller);
		SveAdreseResponse odgovor = (SveAdreseResponse) this.template.marshalSendAndReceive(URI, new SveAdreseRequest());
		for(AdresaDTO adresaDTO : odgovor.getAdrese()) {
			sinhronizacijaServis.dodajAdresu(adresaDTO);
		}
	}
	
	public void dodajSveTipove() {
		this.template = new WebServiceTemplate(this.marshaller);
		SviTipoviResponse odgovor = (SviTipoviResponse) this.template.marshalSendAndReceive(URI, new SviTipoviRequest());
		for(TipSmestajaDTO tipDTO : odgovor.getTipovi()) {
			sinhronizacijaServis.dodajTip(tipDTO);
		}
	}
	
	public void dodajSveUsluge() {
		this.template = new WebServiceTemplate(this.marshaller);
		SveUslugeResponse odgovor = (SveUslugeResponse) this.template.marshalSendAndReceive(URI, new SveUslugeRequest());
		for(UslugaDTO uslugaDTO : odgovor.getUsluge()) {
			sinhronizacijaServis.dodajUslugu(uslugaDTO);
		}
	}
	
	public void dodajSveSmestaje() {
		this.template = new WebServiceTemplate(this.marshaller);
		SviSmestajiResponse odgovor = (SviSmestajiResponse) this.template.marshalSendAndReceive(URI, new SviSmestajiRequest());
		for(SmestajDTO smestajDTO : odgovor.getSmestaji()) {
			sinhronizacijaServis.dodajSmestaj(smestajDTO);
		}
	}

	public void dodajSveRezervacije() {
		this.template = new WebServiceTemplate(this.marshaller);
		SveRezervacijeResponse odgovor = (SveRezervacijeResponse) this.template.marshalSendAndReceive(URI, new SveRezervacijeRequest());
		for(RezervacijaDTO rezervacijaDTO : odgovor.getRezervacije()) {
			sinhronizacijaServis.dodajRezervaciju(rezervacijaDTO);
		}
	}
	
	public void dodajSveKomentare() {
		this.template = new WebServiceTemplate(this.marshaller);
		SviKomentariResponse odgovor = (SviKomentariResponse) this.template.marshalSendAndReceive(URI, new SviKomentariRequest());
		for(KomentarDTO komentarDTO : odgovor.getKomentari()) {
			sinhronizacijaServis.dodajKomentar(komentarDTO);
		}
	}
	
	public boolean posaljiSmestajnuJedinicu(Smestaj smestaj) {
		DodavanjeSmestajneJediniceRequest zahtev = new DodavanjeSmestajneJediniceRequest();
		SmestajDTO smestajDTO = new SmestajDTO();
		smestajDTO.setId(smestaj.getId());
		smestajDTO.setAgent(smestaj.getAgent().getId());
		smestajDTO.setBrojDanaZaOtkazivanje(smestaj.getBrojDanaZaOtkazivanje());
		smestajDTO.setCena(smestaj.getCena());
		smestajDTO.setDozvoljenoOtkazivanje(smestaj.isDozvoljenoOtkazivanje());
		smestajDTO.setKapacitet(smestaj.getKapacitet());
		smestajDTO.setOcena(smestaj.getOcena());
		smestajDTO.setOpis(smestaj.getOpis());
		// Postavljanje adrese
		Adresa adresa = smestaj.getAdresa();
		AdresaDTO adresaDTO = new AdresaDTO();
		adresaDTO.setId(adresa.getId());
		adresaDTO.setBroj(adresa.getBroj());
		adresaDTO.setGrad(adresa.getGrad());
		adresaDTO.setUlica(adresa.getUlica());
		adresaDTO.setZemlja(adresa.getZemlja());
		smestajDTO.setAdresaDTO(adresaDTO);
		// Postavljanje tipa smestaja
		TipSmestaja tip = smestaj.getTip();
		TipSmestajaDTO tipDTO = new TipSmestajaDTO();
		tipDTO.setId(tip.getId());
		tipDTO.setNaziv(tip.getNaziv());
		smestajDTO.setTipDTO(tipDTO);
		// Postavljanje usluga
		List<Usluga> usluge = smestaj.getUsluge();
		for(Usluga usluga : usluge) {
			UslugaDTO uslugaDTO = new UslugaDTO();
			uslugaDTO.setId(usluga.getId());
			uslugaDTO.setNaziv(usluga.getNaziv());
			uslugaDTO.setOpis(usluga.getOpis());
			smestajDTO.getUslugeDTO().add(uslugaDTO);
		}
		zahtev.setSmestaj(smestajDTO);
		DodavanjeSmestajneJediniceResponse odgovor = (DodavanjeSmestajneJediniceResponse) this.template.marshalSendAndReceive(URI, zahtev);
		return odgovor.isUspesno();
	}
	
	public boolean posaljiRezervaciju(Rezervacija rezervacija) {
		DodavanjeRezervacijeRequest zahtev = new DodavanjeRezervacijeRequest();
		RezervacijaDTO rezervacijaDTO = new RezervacijaDTO();
		rezervacijaDTO.setId(rezervacija.getId());
		rezervacijaDTO.setCena(rezervacija.getCena());
		if(rezervacija.getKorisnik() != null) rezervacijaDTO.setKorisnik(rezervacija.getKorisnik().getId());
		rezervacijaDTO.setRealizovana(rezervacija.isRealizovana());
		rezervacijaDTO.setSmestaj(rezervacija.getSmestaj().getId());
		try {
			GregorianCalendar kalendar = new GregorianCalendar();
			kalendar.setTime(rezervacija.getPocetak());
			rezervacijaDTO.setPocetak(DatatypeFactory.newInstance().newXMLGregorianCalendar(kalendar));
			kalendar.setTime(rezervacija.getKraj());
			rezervacijaDTO.setKraj(DatatypeFactory.newInstance().newXMLGregorianCalendar(kalendar));
		} catch(Exception e) { }
		zahtev.setRezervacija(rezervacijaDTO);
		DodavanjeRezervacijeResponse odgovor = (DodavanjeRezervacijeResponse) this.template.marshalSendAndReceive(URI, zahtev);
		return odgovor.isUspesno();
	}
	
	public boolean potvrdiRezervaciju(Long id) {
		this.template = new WebServiceTemplate(this.marshaller);
		PotvrdaRezervacijeRequest zahtev = new PotvrdaRezervacijeRequest();
		zahtev.setRezervacija(id);
		PotvrdaRezervacijeResponse odgovor = (PotvrdaRezervacijeResponse) this.template.marshalSendAndReceive(URI, zahtev);
		return odgovor.isUspesno();
	}
	
}
