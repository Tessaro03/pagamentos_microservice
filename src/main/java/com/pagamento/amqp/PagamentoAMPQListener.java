package com.pagamento.amqp;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pagamento.dtos.PagamentoInputDTO;
import com.pagamento.service.PagamentoService;

@Service
public class PagamentoAMPQListener {

    @Autowired
    private PagamentoService service;
    
    @RabbitListener(queues = "pedido.concluido")
    public void pedidoConcluido(PagamentoInputDTO pagamento){
        service.criarPagamento(pagamento);
    }

    @RabbitListener(queues="pedido.cancelado-pagamento")
    public void pedidoCancelado(Long id){
        service.cancelarPagamento(id);
    }

}
