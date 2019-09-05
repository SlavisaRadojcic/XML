package com.megatravel.vebaplikacijaagent.servisi;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.megatravel.vebaplikacijaagent.model.TipSmestaja;
import com.megatravel.vebaplikacijaagent.repozitorijumi.TipSmestajaRepozitorijum;

@Component
public class TipSmestajaServis {

	@Autowired
	private TipSmestajaRepozitorijum tipSmestajaRepozitorijum;
	
	public List<TipSmestaja> nadjiSve() {
		return tipSmestajaRepozitorijum.findAll();
	}
	
}
