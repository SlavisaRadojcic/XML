package com.megatravel.mikroservisocene.xml.endpointi;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.megatravel.mikroservisocene.model.Ocena;
import com.megatravel.mikroservisocene.repozitorijumi.OceneRepozitorijum;
import com.megatravel.mikroservisocene.xml.OcenaDTO;
import com.megatravel.mikroservisocene.xml.SveOceneRequest;
import com.megatravel.mikroservisocene.xml.SveOceneResponse;

@Endpoint
public class EndpointZaOcene {

	private static final String NAMESPACE_URI = "http://www.megatravel.com/mikroservisocene/xml";

	@Autowired
	private OceneRepozitorijum oceneRepozitorijum;
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "sveOceneRequest")
	@ResponsePayload
	public SveOceneResponse sveOcene(@RequestPayload SveOceneRequest sveOceneRequest) {
		List<Ocena> ocene = oceneRepozitorijum.findAll();
		List<OcenaDTO> oceneDTO = new ArrayList<OcenaDTO>();
		for(Ocena ocena : ocene) {
			OcenaDTO ocenaDTO = new OcenaDTO();
			ocenaDTO.setId(ocena.getId());
			ocenaDTO.setRezervacija(ocena.getRezervacija());
			ocenaDTO.setVrednost(ocenaDTO.getVrednost());
			oceneDTO.add(ocenaDTO);
		}
		SveOceneResponse sveOceneResponse = new SveOceneResponse();
		sveOceneResponse.getOcene().addAll(oceneDTO);
		return sveOceneResponse;
	}
	
}
