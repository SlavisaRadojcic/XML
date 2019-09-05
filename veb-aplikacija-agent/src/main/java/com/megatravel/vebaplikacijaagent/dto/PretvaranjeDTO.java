package com.megatravel.vebaplikacijaagent.dto;

import java.util.ArrayList;
import java.util.List;

import com.megatravel.vebaplikacijaagent.model.Komentar;
import com.megatravel.vebaplikacijaagent.model.Rezervacija;
import com.megatravel.vebaplikacijaagent.model.Smestaj;
import com.megatravel.vebaplikacijaagent.model.TipSmestaja;
import com.megatravel.vebaplikacijaagent.model.Usluga;

public class PretvaranjeDTO {

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
	
}
