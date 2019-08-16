package com.megatravel.registar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class Registar {

	public static void main(String[] args) {
		SpringApplication.run(Registar.class, args);
	}

}
