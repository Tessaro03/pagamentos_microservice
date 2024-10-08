package com.pagamento.validations.validarId;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pagamento.infra.exceptions.ValidacaoException;
import com.pagamento.repository.PagamentoRepository;

@Service
public class ValidandoSeIdExiste implements ValidadorId {
        
    @Autowired
    private PagamentoRepository repository;

  
    @Override
    public void validar(Long idPedido, Long idUsuario) {
        var pagamento = repository.pagamentoPorIdPedido(idPedido);
            if (pagamento.isEmpty()) {
                throw new ValidacaoException("Pagamento não existe");
        }
    }

}
