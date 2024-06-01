package com.pagamento.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pagamento.dtos.PagamentoInputDTO;
import com.pagamento.dtos.PagamentoOutputDTO;
import com.pagamento.model.Pagamento;
import com.pagamento.model.Status;
import com.pagamento.repository.PagamentoRepository;
import com.pagamento.validations.ValidadorPagamentos;

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
        rabbitTemplate.convertAndSend("pagamento.ex","", pagamento.get().getPedidoId());
    }


    public void cancelarPagamento(Long idPedido) {
        validador.validarDelete(idPedido);
        var pagamento = repository.pagamentoPorIdPedido(idPedido);
        pagamento.get().setStatus(Status.CANCELADO);
        repository.save(pagamento.get());
        
    }
}
    