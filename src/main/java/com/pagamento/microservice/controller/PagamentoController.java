package com.pagamento.microservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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
    public ResponseEntity criar(@RequestBody @Valid PagamentoInputDTO dto){
        service.criarPagamento(dto);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("{idPedido}/confirmado")
    public void confirmarPagamento(@PathVariable Long idPedido){
        service.confirmaPagamento(idPedido);
           
    }

    @DeleteMapping("{idPedido}/cancelado")
    public void cancelarPagamento(@PathVariable Long idPedido){
        service.cancelarPagamento(idPedido);
        
    }
}
