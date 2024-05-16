package com.pagamento.microservice.validations.validarPost;

import com.pagamento.microservice.dtos.PagamentoInputDTO;

public interface ValidadorPost {

    void validar(PagamentoInputDTO dto);

}
