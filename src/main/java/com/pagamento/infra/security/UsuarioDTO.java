package com.pagamento.infra.security;


public record UsuarioDTO(

    Long id,
    String username,
    String email,
    String tipo
) {

}
