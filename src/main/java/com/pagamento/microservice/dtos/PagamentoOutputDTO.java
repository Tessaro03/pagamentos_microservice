package com.pagamento.microservice.dtos;

import java.time.LocalDateTime;

import com.pagamento.microservice.model.Pagamento;
import com.pagamento.microservice.model.Status;

public record PagamentoOutputDTO(

    Long id,
    Double valor,
    String nome,
    LocalDateTime expiracao,
    Status status,
    Long pedidoId

) {
    public PagamentoOutputDTO(Pagamento pagamento){
        this(pagamento.getId(), pagamento.getValor(),  pagamento.getNome(),   pagamento.getExpiracao(), 
           pagamento.getStatus(),  pagamento.getPedidoId());
    }
}
