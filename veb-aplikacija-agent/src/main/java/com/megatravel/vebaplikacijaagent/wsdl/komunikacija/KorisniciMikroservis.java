package com.megatravel.vebaplikacijaagent.wsdl.komunikacija;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Service;
import org.springframework.ws.client.core.WebServiceTemplate;

import com.megatravel.vebaplikacijaagent.servisi.SinhronizacijaServis;
import com.megatravel.vebaplikacijaagent.wsdl.KorisnikDTO;
import com.megatravel.vebaplikacijaagent.wsdl.LogovanjeAgentaRequest;
import com.megatravel.vebaplikacijaagent.wsdl.LogovanjeAgentaResponse;
import com.megatravel.vebaplikacijaagent.wsdl.PreuzmiSveKorisnikeRequest;
import com.megatravel.vebaplikacijaagent.wsdl.SviKorisniciResponse;

@Service
public class KorisniciMikroservis {

	private static final String URI = "http://localhost:9999/mikroservis-korisnici/servisi";
	
	@Autowired
	private Jaxb2Marshaller marshaller;
	
	private WebServiceTemplate template;
	
	@Autowired
	private SinhronizacijaServis sinhronizacijaServis;
	
	public boolean logovanjeAgenta(String mejl, String sifra) {
		this.template = new WebServiceTemplate(this.marshaller);
		LogovanjeAgentaRequest zahtev = new LogovanjeAgentaRequest();
		zahtev.setMejl(mejl);
		zahtev.setSifra(sifra);
		LogovanjeAgentaResponse odgovor = (LogovanjeAgentaResponse) this.template.marshalSendAndReceive(URI, zahtev);
		return odgovor.isUspesno();
	}
	
	public void dodajSveKorisnike() {
		this.template = new WebServiceTemplate(this.marshaller);
		PreuzmiSveKorisnikeRequest preuzmiSveKorisnikeRequest = new PreuzmiSveKorisnikeRequest();
		SviKorisniciResponse sviKorisniciResponse = (SviKorisniciResponse) this.template.marshalSendAndReceive(URI, preuzmiSveKorisnikeRequest);
		for(KorisnikDTO korisnikDTO : sviKorisniciResponse.getKorisnici()) {
			sinhronizacijaServis.dodajKorisnika(korisnikDTO);
		}
	}
	
}
