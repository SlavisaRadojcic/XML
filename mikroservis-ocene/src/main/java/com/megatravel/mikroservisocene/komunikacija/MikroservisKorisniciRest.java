package com.megatravel.mikroservisocene.komunikacija;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("mikroservis-korisnici")
public interface MikroservisKorisniciRest {

	@RequestMapping(method = RequestMethod.GET,
			value = "/api/korisnici/{id}")
	public ResponseEntity<?> nadjiJednog(@PathVariable("id") Long id);
	
}
