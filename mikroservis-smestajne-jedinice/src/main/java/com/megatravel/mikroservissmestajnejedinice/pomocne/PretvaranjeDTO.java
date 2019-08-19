package com.megatravel.mikroservissmestajnejedinice.pomocne;

import java.util.ArrayList;
import java.util.List;

import com.megatravel.mikroservissmestajnejedinice.dto.KomentarDTO;
import com.megatravel.mikroservissmestajnejedinice.dto.RezervacijaDTO;
import com.megatravel.mikroservissmestajnejedinice.dto.SmestajDTO;
import com.megatravel.mikroservissmestajnejedinice.dto.TipSmestajaDTO;
import com.megatravel.mikroservissmestajnejedinice.dto.UslugaDTO;
import com.megatravel.mikroservissmestajnejedinice.model.Komentar;
import com.megatravel.mikroservissmestajnejedinice.model.Rezervacija;
import com.megatravel.mikroservissmestajnejedinice.model.Smestaj;
import com.megatravel.mikroservissmestajnejedinice.model.TipSmestaja;
import com.megatravel.mikroservissmestajnejedinice.model.Usluga;

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

	public static List<KomentarDTO> pretvoriKomentare(List<Komentar> komentari) {
		List<KomentarDTO> pretvorene = new ArrayList<KomentarDTO>();
		for(Komentar komentar : komentari) {
			KomentarDTO komentarDTO = new KomentarDTO(komentar);
			pretvorene.add(komentarDTO);
		}
		return pretvorene;
	}

	public static List<TipSmestajaDTO> pretvoriTipoveSmestaja(List<TipSmestaja> tipovi) {
		List<TipSmestajaDTO> pretvoreni = new ArrayList<TipSmestajaDTO>();
		for(TipSmestaja tip : tipovi) {
			TipSmestajaDTO tipDTO = new TipSmestajaDTO(tip);
			pretvoreni.add(tipDTO);
		}
		return pretvoreni;
	}

	public static List<UslugaDTO> pretvoriUsluge(List<Usluga> usluge) {
		List<UslugaDTO> pretvorene = new ArrayList<UslugaDTO>();
		for(Usluga usluga : usluge) {
			UslugaDTO uslugaDTO = new UslugaDTO(usluga);
			pretvorene.add(uslugaDTO);
		}
		return pretvorene;
	}
	
}
