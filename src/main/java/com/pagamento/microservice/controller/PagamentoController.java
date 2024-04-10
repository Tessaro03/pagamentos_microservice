package com.pagamento.microservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pagamento.microservice.service.PagamentoService;

@RestController
@RequestMapping("/pagamentos")
public class PagamentoController {
    
    @Autowired
    private PagamentoService service;
}
