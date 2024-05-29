package com.pagamento;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class PagamentomsApplication {

	public static void main(String[] args) {
		SpringApplication.run(PagamentomsApplication.class, args);
	}

}
