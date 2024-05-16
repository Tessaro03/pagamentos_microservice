package com.pagamento.microservice.validations;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pagamento.microservice.dtos.PagamentoInputDTO;
import com.pagamento.microservice.validations.validarPost.ValidadorPost;

@Service
public class ValidadorPagamentos {
    

    @Autowired
    private List<ValidadorPost> validadorIdPedido;

    public void validarPost(PagamentoInputDTO dto){
        validadorIdPedido.forEach( v -> v.validar(dto));
    }


}
