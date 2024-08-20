package com.pagamento.validations.validarId;

import org.springframework.beans.factory.annotation.Autowired;

import com.pagamento.infra.exceptions.ValidacaoException;
import com.pagamento.repository.PagamentoRepository;

public class ValidandoSePagamentoPertenceAUsuario implements ValidadorId {

    @Autowired
    private PagamentoRepository repository;

    @Override
    public void validar(Long idPedido, Long idUsuario) {
        var pagamento = repository.pagamentoPorIdPedido(idPedido);
        if (!pagamento.get().getIdCliente().equals(idUsuario)) {
            throw new ValidacaoException("Pagamento não pertence ao usuário");
        }
    }
    
}
