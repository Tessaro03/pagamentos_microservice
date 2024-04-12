package com.pagamento.microservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pagamento.microservice.dtos.PagamentoInputDTO;
import com.pagamento.microservice.service.PagamentoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/pagamentos")
public class PagamentoController {
    
    @Autowired
    private PagamentoService service;

    @GetMapping
    public ResponseEntity ver(){
        return ResponseEntity.ok().body(service.verPagamentos());
    }

    @PostMapping
    public void criar(@RequestBody @Valid PagamentoInputDTO dto){
        service.criarPagamento(dto);
    }

    @PatchMapping("{id}/confirmado")
    public void confirmarPagamento(@PathVariable Long id){
        service.confirmaPagamento(id);
        
    }
}
