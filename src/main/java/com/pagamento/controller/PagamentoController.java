package com.pagamento.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pagamento.service.PagamentoService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/pagamentos")
public class PagamentoController {
    
    @Autowired
    private PagamentoService service;

    @GetMapping
    @Operation(summary = "Ver Pagamentos", description = "Retorna os pagamentos criado")
    public ResponseEntity ver(HttpServletRequest request){
        return ResponseEntity.ok().body(service.verPagamentos(request));
    }


    @PatchMapping("{idPedido}/confirmado")
    @Operation(summary = "Confirma o pagamento", description = "Confirma o pagamento e notifica a API de Pedidos e Avaliação")
    public void confirmarPagamento(@PathVariable Long idPedido, HttpServletRequest request){
        service.confirmaPagamento(idPedido, request);
           
    }

    @DeleteMapping("{idPedido}/cancelado")
    @Operation(summary = "Cancela o pagamento", description = "Cancela o pagamento e notifica a API de Pedidos")
    public void cancelarPagamento(@PathVariable Long idPedido, HttpServletRequest request){
        service.cancelarPagamento(idPedido, request);
        
    }
}
