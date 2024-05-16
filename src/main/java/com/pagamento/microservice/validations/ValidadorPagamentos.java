package com.pagamento.microservice.validations;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pagamento.microservice.dtos.PagamentoInputDTO;
import com.pagamento.microservice.validations.validarId.ValidandoSeIdExiste;
import com.pagamento.microservice.validations.validarPost.ValidadorPost;

@Service
public class ValidadorPagamentos {
    

    @Autowired
    private List<ValidadorPost> validadorPost;


    @Autowired
    private ValidandoSeIdExiste validadorId;

    public void validarPost(PagamentoInputDTO dto){
        validadorPost.forEach( v -> v.validar(dto));
    }

    public void validarPatch(Long idPedido){
        validadorId.validar(idPedido);
    }

    public void validarDelete(Long idPedido){
        validadorId.validar(idPedido);
    }

}
