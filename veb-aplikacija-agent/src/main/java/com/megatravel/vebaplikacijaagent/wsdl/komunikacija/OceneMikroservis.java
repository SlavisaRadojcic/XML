package com.megatravel.vebaplikacijaagent.wsdl.komunikacija;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Service;
import org.springframework.ws.client.core.WebServiceTemplate;

import com.megatravel.vebaplikacijaagent.servisi.SinhronizacijaServis;
import com.megatravel.vebaplikacijaagent.wsdl.OcenaDTO;
import com.megatravel.vebaplikacijaagent.wsdl.SveOceneRequest;
import com.megatravel.vebaplikacijaagent.wsdl.SveOceneResponse;

@Service
public class OceneMikroservis {

private static final String URI = "http://localhost:9999/mikroservis-ocene/servisi";
	
	@Autowired
	private Jaxb2Marshaller marshaller;
	
	private WebServiceTemplate template;
	
	@Autowired
	private SinhronizacijaServis sinhronizacijaServis;
	
	public void dodajSveOcene() {
		this.template = new WebServiceTemplate(this.marshaller);
		SveOceneResponse odgovor = (SveOceneResponse) this.template.marshalSendAndReceive(URI, new SveOceneRequest());
		for(OcenaDTO ocenaDTO : odgovor.getOcene()) {
			sinhronizacijaServis.dodajOcenu(ocenaDTO);		}
	}
	
}
