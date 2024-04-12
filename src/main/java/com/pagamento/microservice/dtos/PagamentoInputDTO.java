package com.pagamento.microservice.dtos;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PagamentoInputDTO(

    @NotBlank
    String nome,
    String numero,
    BigDecimal valor,
    @NotNull
    Long pedidoId

) {
    
}
