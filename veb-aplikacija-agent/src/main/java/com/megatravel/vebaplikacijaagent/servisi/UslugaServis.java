package com.megatravel.vebaplikacijaagent.servisi;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.megatravel.vebaplikacijaagent.model.Usluga;
import com.megatravel.vebaplikacijaagent.repozitorijumi.UslugaRepozitorijum;

@Component
public class UslugaServis {

	@Autowired
	private UslugaRepozitorijum uslugaRepozitorijum;
	
	public List<Usluga> nadjiSve() {
		return uslugaRepozitorijum.findAll();
	}
	
}
