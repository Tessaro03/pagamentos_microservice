package com.pagamento.microservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class PagamentomcApplication {

	public static void main(String[] args) {
		SpringApplication.run(PagamentomcApplication.class, args);
	}

}
