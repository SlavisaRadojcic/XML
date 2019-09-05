package com.megatravel.vebaplikacijaagent.wsdl.komunikacija;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
public class Konfiguracija {

	@Bean
	public Jaxb2Marshaller marshaller() {
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		marshaller.setPackagesToScan("com.megatravel.vebaplikacijaagent.wsdl");
		return marshaller;
	}
	
}
