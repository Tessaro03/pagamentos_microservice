package com.pagamento.dtos;

import java.time.LocalDateTime;

import com.pagamento.model.Pagamento;
import com.pagamento.model.Status;

public record PagamentoOutputDTO(

    Long id,
    Long idCliente,
    Double valor,
    LocalDateTime expiracao,
    Status status,
    Long pedidoId

) {
    public PagamentoOutputDTO(Pagamento pagamento){
        this(pagamento.getId(), pagamento.getIdCliente(), pagamento.getValor(), pagamento.getExpiracao(), 
           pagamento.getStatus(),  pagamento.getPedidoId());
    }
}
