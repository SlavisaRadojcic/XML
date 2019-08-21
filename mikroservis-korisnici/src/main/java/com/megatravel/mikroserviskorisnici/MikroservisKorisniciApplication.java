package com.megatravel.mikroserviskorisnici;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class MikroservisKorisniciApplication {

	public static void main(String[] args) {
		SpringApplication.run(MikroservisKorisniciApplication.class, args);
	}

}
