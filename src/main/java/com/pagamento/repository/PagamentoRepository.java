package com.pagamento.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.pagamento.model.Pagamento;

public interface PagamentoRepository extends JpaRepository<Pagamento, Long>{
   
    @Query("SELECT p FROM Pagamento p WHERE p.pedidoId = :idPedido")
    Optional<Pagamento> pagamentoPorIdPedido(Long idPedido);

    @Query("SELECT p FROM Pagamento p WHERE p.idCliente = :idCliente")
    List<Pagamento> pagamentosPorIdCliente(Long idCliente);

    @Query("SELECT p FROM Pagamento p WHERE p.idLoja = :idLoja")
    List<Pagamento> pagamentosPorIdLoja(Long idLoja);
}
