package com.megatravel.mikroserviskorisnici.xml.endpointi;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.megatravel.mikroserviskorisnici.model.Korisnik;
import com.megatravel.mikroserviskorisnici.repozitorijumi.KorisnikRepozitorijum;
import com.megatravel.mikroserviskorisnici.servisi.LoginServis;
import com.megatravel.mikroserviskorisnici.xml.KorisnikDTO;
import com.megatravel.mikroserviskorisnici.xml.LogovanjeAgentaRequest;
import com.megatravel.mikroserviskorisnici.xml.LogovanjeAgentaResponse;
import com.megatravel.mikroserviskorisnici.xml.PreuzmiSveKorisnikeRequest;
import com.megatravel.mikroserviskorisnici.xml.StatusKorisnika;
import com.megatravel.mikroserviskorisnici.xml.SviKorisniciResponse;
import com.megatravel.mikroserviskorisnici.xml.TipKorisnika;

@Endpoint
public class EndpointZaKorisnike {

	private static final String NAMESPACE_URI = "http://www.megatravel.com/mikroserviskorisnici/xml";

	@Autowired
	private KorisnikRepozitorijum korisnikRepozitorijum;

	@Autowired
	private LoginServis loginServis;

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "preuzmiSveKorisnikeRequest")
	@ResponsePayload
	public SviKorisniciResponse sviKorisnici(@RequestPayload PreuzmiSveKorisnikeRequest preuzmiSveKorisnike) {
		List<Korisnik> korisnici = korisnikRepozitorijum.findAll();
		List<KorisnikDTO> korisniciDTO = new ArrayList<KorisnikDTO>();
		for(Korisnik korisnik : korisnici) {
			KorisnikDTO korisnikDTO = new KorisnikDTO();
			korisnikDTO.setId(korisnik.getId());
			korisnikDTO.setIme(korisnik.getIme());
			korisnikDTO.setMejl(korisnik.getMejl());
			korisnikDTO.setPoslovniMaticniBroj(korisnik.getPoslovniMaticniBroj());
			korisnikDTO.setPrezime(korisnik.getPrezime());
			korisnikDTO.setSifra(korisnik.getLozinka());
			switch(korisnik.getStatus()) {
				case AKTIVAN : korisnikDTO.setStatus(StatusKorisnika.AKTIVAN); break;
				case BLOKIRAN : korisnikDTO.setStatus(StatusKorisnika.BLOKIRAN); break;
			}
			switch(korisnik.getTip()) {
				case AGENT : korisnikDTO.setTip(TipKorisnika.AGENT); break;
				case ADMINISTRATOR : korisnikDTO.setTip(TipKorisnika.ADMINISTRATOR); break;
				case KORISNIK : korisnikDTO.setTip(TipKorisnika.KORISNIK); break;
			}
			korisniciDTO.add(korisnikDTO);
		}
		SviKorisniciResponse sviKorisnici = new SviKorisniciResponse();
		sviKorisnici.getKorisnici().addAll(korisniciDTO);
		return sviKorisnici;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "logovanjeAgentaRequest")
	@ResponsePayload
	public LogovanjeAgentaResponse logovanjeAgenta(@RequestPayload LogovanjeAgentaRequest logovanjeAgenta) {
		loginServis.ulogujAgenta(logovanjeAgenta.getMejl(), logovanjeAgenta.getSifra());
		LogovanjeAgentaResponse logovanjeAgentaResponse = new LogovanjeAgentaResponse();
		logovanjeAgentaResponse.setUspesno(true);
		return logovanjeAgentaResponse;
	}

}