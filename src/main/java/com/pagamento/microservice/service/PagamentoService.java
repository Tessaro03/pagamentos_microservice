package com.pagamento.microservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pagamento.microservice.repository.PagamentoRepository;

@Service
public class PagamentoService {
    
    @Autowired
    private PagamentoRepository repository;

}
