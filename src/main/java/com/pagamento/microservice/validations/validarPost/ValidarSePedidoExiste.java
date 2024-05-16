package com.pagamento.microservice.validations.validarPost;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pagamento.microservice.dtos.PagamentoInputDTO;
import com.pagamento.microservice.infra.exceptions.ValidacaoException;
import com.pagamento.microservice.repository.PagamentoRepository;

@Service
public class ValidarSePedidoExiste implements ValidadorPost{

    @Autowired
    private PagamentoRepository repository;

    public void validar(PagamentoInputDTO dto) {
       var pagamento = repository.pagamentoPorIdPedido(dto.pedidoId());
       
        if (pagamento.isPresent()) {
            throw new ValidacaoException("Pagamento já existe");
       }
    }
    
}
