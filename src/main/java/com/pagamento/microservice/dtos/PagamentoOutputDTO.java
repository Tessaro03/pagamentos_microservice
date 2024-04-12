package com.pagamento.microservice.dtos;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.pagamento.microservice.model.Pagamento;
import com.pagamento.microservice.model.Status;

public record PagamentoOutputDTO(

    Long id,
    BigDecimal valor,
    String nome,
    String numero,
    LocalDateTime expiracao,
    Status status,
    Long pedidoId

) {
    public PagamentoOutputDTO(Pagamento pagamento){
        this(pagamento.getId(), pagamento.getValor(),  pagamento.getNome(),  pagamento.getNumero(),  pagamento.getExpiracao(), 
           pagamento.getStatus(),  pagamento.getPedidoId());
    }
}
