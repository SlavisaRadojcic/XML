package com.megatravel.mikroservissmestajnejedinice.xml.endpointi;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import javax.xml.datatype.DatatypeFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.megatravel.mikroservissmestajnejedinice.model.Adresa;
import com.megatravel.mikroservissmestajnejedinice.model.Komentar;
import com.megatravel.mikroservissmestajnejedinice.model.Rezervacija;
import com.megatravel.mikroservissmestajnejedinice.model.Smestaj;
import com.megatravel.mikroservissmestajnejedinice.model.TipSmestaja;
import com.megatravel.mikroservissmestajnejedinice.model.Usluga;
import com.megatravel.mikroservissmestajnejedinice.repozitorijumi.AdresaRepozitorijum;
import com.megatravel.mikroservissmestajnejedinice.repozitorijumi.KomentarRepozitorijum;
import com.megatravel.mikroservissmestajnejedinice.repozitorijumi.RezervacijaRepozitorijum;
import com.megatravel.mikroservissmestajnejedinice.servisi.RezervacijaServis;
import com.megatravel.mikroservissmestajnejedinice.servisi.SmestajServis;
import com.megatravel.mikroservissmestajnejedinice.servisi.TipSmestajaServis;
import com.megatravel.mikroservissmestajnejedinice.servisi.UslugaServis;
import com.megatravel.mikroservissmestajnejedinice.xml.AdresaDTO;
import com.megatravel.mikroservissmestajnejedinice.xml.DodavanjeRezervacijeRequest;
import com.megatravel.mikroservissmestajnejedinice.xml.DodavanjeRezervacijeResponse;
import com.megatravel.mikroservissmestajnejedinice.xml.DodavanjeSmestajneJediniceRequest;
import com.megatravel.mikroservissmestajnejedinice.xml.DodavanjeSmestajneJediniceResponse;
import com.megatravel.mikroservissmestajnejedinice.xml.KomentarDTO;
import com.megatravel.mikroservissmestajnejedinice.xml.PotvrdaRezervacijeRequest;
import com.megatravel.mikroservissmestajnejedinice.xml.PotvrdaRezervacijeResponse;
import com.megatravel.mikroservissmestajnejedinice.xml.RezervacijaDTO;
import com.megatravel.mikroservissmestajnejedinice.xml.SmestajDTO;
import com.megatravel.mikroservissmestajnejedinice.xml.SveAdreseRequest;
import com.megatravel.mikroservissmestajnejedinice.xml.SveAdreseResponse;
import com.megatravel.mikroservissmestajnejedinice.xml.SveRezervacijeRequest;
import com.megatravel.mikroservissmestajnejedinice.xml.SveRezervacijeResponse;
import com.megatravel.mikroservissmestajnejedinice.xml.SveUslugeRequest;
import com.megatravel.mikroservissmestajnejedinice.xml.SveUslugeResponse;
import com.megatravel.mikroservissmestajnejedinice.xml.SviKomentariRequest;
import com.megatravel.mikroservissmestajnejedinice.xml.SviKomentariResponse;
import com.megatravel.mikroservissmestajnejedinice.xml.SviSmestajiRequest;
import com.megatravel.mikroservissmestajnejedinice.xml.SviSmestajiResponse;
import com.megatravel.mikroservissmestajnejedinice.xml.SviTipoviRequest;
import com.megatravel.mikroservissmestajnejedinice.xml.SviTipoviResponse;
import com.megatravel.mikroservissmestajnejedinice.xml.TipSmestajaDTO;
import com.megatravel.mikroservissmestajnejedinice.xml.UslugaDTO;

@Endpoint
public class EndpointZaSmestaje {

	private static final String NAMESPACE_URI = "http://www.megatravel.com/mikroservissmestajnejedinice/xml";
	
	@Autowired
	private AdresaRepozitorijum adresaRepozitorijum;
	
	@Autowired
	private TipSmestajaServis tipSmestajaServis;
	
	@Autowired
	private UslugaServis uslugaServis;
	
	@Autowired
	private SmestajServis smestajServis;
	
	@Autowired
	private RezervacijaRepozitorijum rezervacijeRepozitorijum;
	
	@Autowired
	private KomentarRepozitorijum komentarRepozitorijum;
	
	@Autowired
	private RezervacijaServis rezervacijeServis;
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "sveAdreseRequest")
	@ResponsePayload
	public SveAdreseResponse sveAdrese(@RequestPayload SveAdreseRequest sveAdreseRequest) {
		List<Adresa> adrese = this.adresaRepozitorijum.findAll();
		List<AdresaDTO> adreseDTO = new ArrayList<AdresaDTO>();
		for(Adresa adresa : adrese) {
			AdresaDTO adresaDTO = new AdresaDTO();
			adresaDTO.setId(adresa.getId());
			adresaDTO.setBroj(adresa.getBroj());
			adresaDTO.setGrad(adresa.getGrad());
			adresaDTO.setUlica(adresa.getUlica());
			adresaDTO.setZemlja(adresa.getZemlja());
			adreseDTO.add(adresaDTO);
		}
		SveAdreseResponse sveAdreseResponse = new SveAdreseResponse();
		sveAdreseResponse.getAdrese().addAll(adreseDTO);
		return sveAdreseResponse;
	}
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "sviTipoviRequest")
	@ResponsePayload
	public SviTipoviResponse sviTipovi(@RequestPayload SviTipoviRequest sviTipoviRequest) {
		List<TipSmestaja> tipovi = tipSmestajaServis.nadjiSve();
		List<TipSmestajaDTO> tipoviDTO = new ArrayList<TipSmestajaDTO>();
		for(TipSmestaja tip : tipovi) {
			TipSmestajaDTO tipDTO = new TipSmestajaDTO();
			tipDTO.setId(tip.getId());
			tipDTO.setNaziv(tip.getNaziv());
			tipoviDTO.add(tipDTO);
		}
		SviTipoviResponse sviTipoviResponse = new SviTipoviResponse();
		sviTipoviResponse.getTipovi().addAll(tipoviDTO);
		return sviTipoviResponse ;
	}
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "sveUslugeRequest")
	@ResponsePayload
	public SveUslugeResponse sveUsluge(@RequestPayload SveUslugeRequest sveUslugeRequest) {
		List<Usluga> usluge = uslugaServis.nadjiSve();
		List<UslugaDTO> uslugeDTO = new ArrayList<UslugaDTO>();
		for(Usluga usluga : usluge) {
			UslugaDTO uslugaDTO = new UslugaDTO();
			uslugaDTO.setId(usluga.getId());
			uslugaDTO.setNaziv(usluga.getNaziv());
			uslugaDTO.setOpis(usluga.getOpis());
			uslugeDTO.add(uslugaDTO);
		}
		SveUslugeResponse sveUslugeResponse = new SveUslugeResponse();
		sveUslugeResponse.getUsluge().addAll(uslugeDTO);
		return sveUslugeResponse;
	}
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "sviSmestajiRequest")
	@ResponsePayload
	public SviSmestajiResponse sviSmestaji(@RequestPayload SviSmestajiRequest sviSmestajiRequest) {
		List<Smestaj> smestaji = smestajServis.nadjiSve();
		List<SmestajDTO> smestajiDTO = new ArrayList<SmestajDTO>();
		for(Smestaj smestaj : smestaji) {
			SmestajDTO smestajDTO = new SmestajDTO();
			smestajDTO.setId(smestaj.getId());
			smestajDTO.setAgent(smestaj.getAgent());
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
			smestajiDTO.add(smestajDTO);
		}
		SviSmestajiResponse sviSmestajiResponse = new SviSmestajiResponse();
		sviSmestajiResponse.getSmestaji().addAll(smestajiDTO);
		return sviSmestajiResponse;
	}
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "sveRezervacijeRequest")
	@ResponsePayload
	public SveRezervacijeResponse sveRezervacije(@RequestPayload SveRezervacijeRequest sveRezervacijeRequest) {
		List<Rezervacija> rezervacije = rezervacijeRepozitorijum.findAll();
		List<RezervacijaDTO> rezervacijeDTO = new ArrayList<RezervacijaDTO>();
		for(Rezervacija rezervacija : rezervacije) {
			RezervacijaDTO rezervacijaDTO = new RezervacijaDTO();
			rezervacijaDTO.setId(rezervacija.getId());
			rezervacijaDTO.setCena(rezervacija.getCena());
			if(rezervacija.getKorisnik() != null) rezervacijaDTO.setKorisnik(rezervacija.getKorisnik());
			rezervacijaDTO.setRealizovana(rezervacija.isRealizovana());
			rezervacijaDTO.setSmestaj(rezervacija.getSmestaj().getId());
			try {
				GregorianCalendar kalendar = new GregorianCalendar();
				kalendar.setTime(rezervacija.getPocetak());
				rezervacijaDTO.setPocetak(DatatypeFactory.newInstance().newXMLGregorianCalendar(kalendar));
				kalendar.setTime(rezervacija.getKraj());
				rezervacijaDTO.setKraj(DatatypeFactory.newInstance().newXMLGregorianCalendar(kalendar));
			} catch(Exception e) { }
			rezervacijeDTO.add(rezervacijaDTO);
		}
		SveRezervacijeResponse sveRezervacijeResponse = new SveRezervacijeResponse();
		sveRezervacijeResponse.getRezervacije().addAll(rezervacijeDTO);
		return sveRezervacijeResponse;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "sviKomentariRequest")
	@ResponsePayload
	public SviKomentariResponse sviKomentari(@RequestPayload SviKomentariRequest sviKomentariRequest) {
		List<Komentar> komentari = komentarRepozitorijum.findAll();
		List<KomentarDTO> komentariDTO = new ArrayList<KomentarDTO>();
		for(Komentar komentar : komentari) {
			KomentarDTO komentarDTO = new KomentarDTO();
			komentarDTO.setId(komentar.getId());
			komentarDTO.setOdobren(komentar.isOdobren());
			komentarDTO.setRezervacija(komentar.getRezervacija().getId());
			komentarDTO.setTekst(komentar.getTekst());
			komentariDTO.add(komentarDTO);
		}
		SviKomentariResponse sviKomentariResponse = new SviKomentariResponse();
		sviKomentariResponse.getKomentari().addAll(komentariDTO);
		return sviKomentariResponse;
	}
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "dodavanjeSmestajneJediniceRequest")
	@ResponsePayload
	public DodavanjeSmestajneJediniceResponse dodavanjeSmestajneJedinice(@RequestPayload DodavanjeSmestajneJediniceRequest dodavanjeSmestajneJediniceRequest) {
		smestajServis.kreiraj(dodavanjeSmestajneJediniceRequest.getSmestaj(), dodavanjeSmestajneJediniceRequest.getSmestaj().getAgent());
		DodavanjeSmestajneJediniceResponse dodavanjeSmestajneJediniceResponse = new DodavanjeSmestajneJediniceResponse();
		dodavanjeSmestajneJediniceResponse.setUspesno(true);
		return dodavanjeSmestajneJediniceResponse ;
	}
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "dodavanjeRezervacijeRequest")
	@ResponsePayload
	public DodavanjeRezervacijeResponse dodavanjeRezervacije(@RequestPayload DodavanjeRezervacijeRequest dodavanjeRezervacijeRequest) {
		rezervacijeServis.kreiraj(dodavanjeRezervacijeRequest.getRezervacija(), dodavanjeRezervacijeRequest.getRezervacija().getSmestaj());
		DodavanjeRezervacijeResponse dodavanjeRezervacijeResponse = new DodavanjeRezervacijeResponse();
		dodavanjeRezervacijeResponse.setUspesno(true);
		return dodavanjeRezervacijeResponse;
	}
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "potvrdaRezervacijeRequest")
	@ResponsePayload
	public PotvrdaRezervacijeResponse potvrdaRezervacije(@RequestPayload PotvrdaRezervacijeRequest potvrdaRezervacijeRequest) {
		rezervacijeServis.realizujRezervaciju(potvrdaRezervacijeRequest.getRezervacija());
		PotvrdaRezervacijeResponse potvrdaRezervacijeResponse = new PotvrdaRezervacijeResponse();
		potvrdaRezervacijeResponse.setUspesno(true);
		return potvrdaRezervacijeResponse;
	}
	
}
