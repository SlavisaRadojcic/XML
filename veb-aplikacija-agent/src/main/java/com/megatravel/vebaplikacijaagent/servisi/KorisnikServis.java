package com.megatravel.vebaplikacijaagent.servisi;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import com.megatravel.vebaplikacijaagent.model.Korisnik;
import com.megatravel.vebaplikacijaagent.model.StatusKorisnika;
import com.megatravel.vebaplikacijaagent.model.TipKorisnika;
import com.megatravel.vebaplikacijaagent.repozitorijumi.KorisnikRepozitorijum;
import com.megatravel.vebaplikacijaagent.wsdl.komunikacija.KorisniciMikroservis;


@Component
public class KorisnikServis {

	@Autowired
	private KorisnikRepozitorijum korisnikRepozitorijum;
	
	@Autowired
	private KorisniciMikroservis korisniciMikroservis;
	
	@Autowired
	private SinhronizacijaServis sinhronizacijaServis;
	
	public Korisnik logovanje(String mejl, String sifra) {
		List<Korisnik> korisnici = korisnikRepozitorijum.findAll();
		for(Korisnik korisnik : korisnici) {
			if(korisnik.getMejl().equals(mejl)) {
				if(korisnik.getLozinka().equals(sifra)) {
					if(korisnik.getTip().equals(TipKorisnika.AGENT)) {
						if(korisniciMikroservis.logovanjeAgenta(mejl, sifra)) {
							sinhronizacijaServis.sinhronizujSve();
							return korisnik;
						} else {
							break;
						}
					} else {
						break;
					}
				} else {
					break;
				}
			}
		}
		throw new ResponseStatusException(HttpStatus.NOT_FOUND);
	}
	
	@PostConstruct
	private void dodavanjePocetnogAgenta() {
		Korisnik agent = new Korisnik();
		agent.setId(1L);
		agent.setIme("Agent");
		agent.setPrezime("Agent");
		agent.setMejl("agent@megatravel.com");
		agent.setLozinka("agent");
		agent.setPoslovniMaticniBroj("0");
		agent.setStatus(StatusKorisnika.AKTIVAN);
		agent.setTip(TipKorisnika.AGENT);
		korisnikRepozitorijum.save(agent);
	}
	
}
