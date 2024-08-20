package com.pagamento.validations.validarPost;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pagamento.dtos.PagamentoInputDTO;
import com.pagamento.infra.exceptions.ValidacaoException;
import com.pagamento.repository.PagamentoRepository;


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
