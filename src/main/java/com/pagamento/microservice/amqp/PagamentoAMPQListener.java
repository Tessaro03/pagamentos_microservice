package com.pagamento.microservice.amqp;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pagamento.microservice.dtos.PagamentoInputDTO;
import com.pagamento.microservice.service.PagamentoService;

@Service
public class PagamentoAMPQListener {

    @Autowired
    private PagamentoService service;
    
    @RabbitListener(queues = "pedido.concluido")
    public void pedidoConcluido(PagamentoInputDTO pagamento){
        service.criarPagamento(pagamento);
    }

    @RabbitListener(queues = "pedido.cancelado")
    public void pedidoCancelado(Long idPedido){
        service.cancelarPagamento(idPedido);
    }

}
