package com.pagamento.microservice.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pagamento.microservice.dtos.PagamentoInputDTO;
import com.pagamento.microservice.dtos.PagamentoOutputDTO;
import com.pagamento.microservice.model.Pagamento;
import com.pagamento.microservice.model.Status;
import com.pagamento.microservice.repository.PagamentoRepository;
import com.pagamento.microservice.validations.ValidadorPagamentos;

@Service
public class PagamentoService {
    
    @Autowired
    private PagamentoRepository repository;

    @Autowired
    private ValidadorPagamentos validador;

    @Autowired 
	private RabbitTemplate rabbitTemplate;

    public void criarPagamento(PagamentoInputDTO dto){
        validador.validarPost(dto);
        var pagamento = new Pagamento(dto);
        repository.save(pagamento);
    }


    public List<PagamentoOutputDTO> verPagamentos() {
        var pagamentos = repository.findAll();
        return pagamentos.stream().map(PagamentoOutputDTO::new).collect(Collectors.toList());
    }

    public void confirmaPagamento(Long idPedido){
        validador.validarPatch(idPedido);
        var pagamento = repository.pagamentoPorIdPedido(idPedido);
        pagamento.get().setStatus(Status.CONFIRMADO);
        repository.save(pagamento.get());
        rabbitTemplate.convertAndSend("pagamento.concluido", pagamento.get().getPedidoId());
    }


    public void cancelarPagamento(Long idPedido) {
        validador.validarDelete(idPedido);
        var pagamento = repository.pagamentoPorIdPedido(idPedido);
        pagamento.get().setStatus(Status.CANCELADO);
        repository.save(pagamento.get());
        
    }
}
    