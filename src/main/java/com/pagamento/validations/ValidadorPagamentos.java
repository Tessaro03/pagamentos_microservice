package com.pagamento.validations;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pagamento.dtos.PagamentoInputDTO;
import com.pagamento.validations.validarId.ValidadorId;
import com.pagamento.validations.validarPost.ValidadorPost;

@Service
public class ValidadorPagamentos {
    

    @Autowired
    private List<ValidadorPost> validadorPost;


    @Autowired
    private List<ValidadorId> validadorId;

    public void validarPost(PagamentoInputDTO dto){
        validadorPost.forEach( v -> v.validar(dto));
    }

    public void validarPatch(Long idPedido, Long idUsuario){
        validadorId.forEach(v -> v.validar(idPedido, idUsuario));
    }

    public void validarDelete(Long idPedido, Long idUsuario){
        validadorId.forEach(v -> v.validar(idPedido, idUsuario));
    }

}
