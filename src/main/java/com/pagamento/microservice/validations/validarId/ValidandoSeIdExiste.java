package com.pagamento.microservice.validations.validarId;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pagamento.microservice.infra.exceptions.ValidacaoException;
import com.pagamento.microservice.repository.PagamentoRepository;

@Service
public class ValidandoSeIdExiste  {
        
    @Autowired
    private PagamentoRepository repository;

    public void validar(Long idPedido) {
        var pagamento = repository.pagamentoPorIdPedido(idPedido);
            if (pagamento.isEmpty()) {
                throw new ValidacaoException("Pagamento não existe");
        }
    }

}
