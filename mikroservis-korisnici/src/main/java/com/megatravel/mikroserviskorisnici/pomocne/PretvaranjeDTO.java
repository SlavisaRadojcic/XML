package com.megatravel.mikroserviskorisnici.pomocne;

import java.util.ArrayList;
import java.util.List;

import com.megatravel.mikroserviskorisnici.dto.KorisnikDTO;
import com.megatravel.mikroserviskorisnici.model.Korisnik;

public class PretvaranjeDTO {

	public static final List<KorisnikDTO> pretvori(List<Korisnik> korisnici) {
		List<KorisnikDTO> pretvoreni = new ArrayList<>();
		for(Korisnik korisnik : korisnici) {
			KorisnikDTO korisnikDTO = new KorisnikDTO(korisnik);
			pretvoreni.add(korisnikDTO);
		}
		return pretvoreni;
	}
	
}
