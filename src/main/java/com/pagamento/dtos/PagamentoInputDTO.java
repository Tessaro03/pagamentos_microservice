package com.pagamento.dtos;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PagamentoInputDTO(

    @NotBlank
    Long idCliente,
    @NotBlank
    Long idLoja,

    Double valor,
    @NotNull
    Long pedidoId

) {
    
}
