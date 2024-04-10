package com.pagamento.microservice.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PagamentoInputDTO(

    @NotBlank
    String nome,
    String numero,
    String expiracao,
    @NotNull
    Long pedidoId

) {
    
}
