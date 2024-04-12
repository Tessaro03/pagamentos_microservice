package com.pagamento.microservice.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.pagamento.microservice.dtos.PagamentoInputDTO;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "pagamentos")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Pagamento {

    public Pagamento(PagamentoInputDTO dto) {
        this.nome = dto.nome();
        this.numero = dto.numero();
        this.pedidoId = dto.pedidoId();
        this.valor = dto.valor();

        this.expiracao = LocalDateTime.now().plusMinutes(30);
        this.status = Status.CRIADO;
        this.formaDePagamentoId = 1l;

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Positive
    private BigDecimal valor;

    @Size(max=100)
    private String nome;

    @Size(max=19)
    private String numero;

    private LocalDateTime expiracao;

    @Enumerated(EnumType.STRING)
    private Status status;

    private Long pedidoId;

    private Long formaDePagamentoId;


}