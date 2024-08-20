package com.pagamento.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pagamento.dtos.PagamentoInputDTO;
import com.pagamento.dtos.PagamentoOutputDTO;
import com.pagamento.infra.security.TokenService;
import com.pagamento.model.Pagamento;
import com.pagamento.model.Status;
import com.pagamento.repository.PagamentoRepository;
import com.pagamento.validations.ValidadorPagamentos;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class PagamentoService {
    
    
    @Autowired
    private ValidadorPagamentos validador;
    
    @Autowired
    private PagamentoRepository repository;
    
    @Autowired 
	private RabbitTemplate rabbitTemplate;

    @Autowired
    private TokenService tokenService;

    public void criarPagamento(PagamentoInputDTO dto){
        validador.validarPost(dto);
        var pagamento = new Pagamento(dto);
        repository.save(pagamento);
    }


    public List<PagamentoOutputDTO> verPagamentos(HttpServletRequest request) {
        var usuario = tokenService.extrairInformacoes(request);
        List<Pagamento> pagamentos = null;
        if (usuario.tipo().equals("Cliente")) {
            pagamentos = repository.pagamentosPorIdCliente(usuario.id());
        } else if (usuario.tipo().equals("Loja")){
            pagamentos = repository.pagamentosPorIdLoja(usuario.id());
        }
        return pagamentos.stream().map(PagamentoOutputDTO::new).collect(Collectors.toList());
    }

    public void confirmaPagamento(Long idPedido, HttpServletRequest request){
        var usuario = tokenService.extrairInformacoes(request);

        validador.validarPatch(idPedido, usuario.id());
        var pagamento = repository.pagamentoPorIdPedido(idPedido);
        pagamento.get().setStatus(Status.CONFIRMADO);
        repository.save(pagamento.get());
        rabbitTemplate.convertAndSend("pagamento.ex","", pagamento.get().getPedidoId());
    }


    public void cancelarPagamento(Long idPedido, HttpServletRequest request) {
        var usuario = tokenService.extrairInformacoes(request);
        validador.validarDelete(idPedido, usuario.id());
        var pagamento = repository.pagamentoPorIdPedido(idPedido);
        pagamento.get().setStatus(Status.CANCELADO);
        repository.save(pagamento.get());
        
    }
    public void cancelarPagamento(Long idPedido) {
        var pagamento = repository.pagamentoPorIdPedido(idPedido);
        pagamento.get().setStatus(Status.CANCELADO);
        repository.save(pagamento.get());
    }
}
    