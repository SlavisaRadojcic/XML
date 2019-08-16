package com.megatravel.mikroservissmestajnejedinice.pomocne;

import java.util.ArrayList;
import java.util.List;

import com.megatravel.mikroservissmestajnejedinice.dto.RezervacijaDTO;
import com.megatravel.mikroservissmestajnejedinice.dto.SmestajDTO;
import com.megatravel.mikroservissmestajnejedinice.model.Rezervacija;
import com.megatravel.mikroservissmestajnejedinice.model.Smestaj;

public class PretvaranjeDTO {

	public static final List<SmestajDTO> pretvoriSmestaje(List<Smestaj> smestaji) {
		List<SmestajDTO> pretvoreni = new ArrayList<SmestajDTO>();
		for(Smestaj smestaj : smestaji) {
			SmestajDTO smestajDTO = new SmestajDTO(smestaj);
			pretvoreni.add(smestajDTO);
		}
		return pretvoreni;
	}

	public static final List<RezervacijaDTO> pretvoriRezervacije(List<Rezervacija> rezervacije) {
		List<RezervacijaDTO> pretvorene = new ArrayList<RezervacijaDTO>();
		for(Rezervacija rezervacija : rezervacije) {
			RezervacijaDTO rezervacijaDTO = new RezervacijaDTO(rezervacija);
			pretvorene.add(rezervacijaDTO);
		}
		return pretvorene;
	}
	
}
