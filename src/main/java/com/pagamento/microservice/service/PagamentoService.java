package com.pagamento.microservice.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pagamento.microservice.dtos.PagamentoInputDTO;
import com.pagamento.microservice.dtos.PagamentoOutputDTO;
import com.pagamento.microservice.http.PedidoClient;
import com.pagamento.microservice.model.Pagamento;
import com.pagamento.microservice.model.Status;
import com.pagamento.microservice.repository.PagamentoRepository;

@Service
public class PagamentoService {
    
    @Autowired
    private PagamentoRepository repository;

    @Autowired
    private PedidoClient pedido;


    public void criarPagamento(PagamentoInputDTO dto){
        var pagamento = new Pagamento(dto);
        repository.save(pagamento);
    }


    public List<PagamentoOutputDTO> verPagamentos() {
        var pagamentos = repository.findAll();

        return pagamentos.stream().map(PagamentoOutputDTO::new).collect(Collectors.toList());
    }

    public void confirmaPagamento(Long id){
        var pagamento = repository.getReferenceById(id);
        pagamento.setStatus(Status.CONFIRMADO);
        repository.save(pagamento);
        pedido.atualizarPedido(pagamento.getPedidoId());
    }
}
