package Equipa2.Incremento2.model;

import java.util.UUID;

import jakarta.persistence.*;

import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import Equipa2.Incremento2.model.enums.MetodoPagamento;
import Equipa2.Incremento2.model.enums.StatusPagamento;

/**
 * Classe que representa um pagamento.
 */
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Pagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private double valor;

    @ManyToOne
    @JoinColumn(name = "origem_cliente_id")
    private Cliente origemCliente;

    @ManyToOne
    @JoinColumn(name = "destino_profissional_id")
    private Profissional destinoProfissional;

    @Enumerated(EnumType.STRING)
    private MetodoPagamento metodo;

    @Enumerated(EnumType.STRING)
    private StatusPagamento status;

    /**
     * Construtor que inicializa um pagamento com os dados fornecidos.
     *
     * @param valor o valor do pagamento
     * @param origemCliente o cliente que realiza o pagamento
     * @param destinoProfissional o profissional que recebe o pagamento
     * @param metodo o método de pagamento utilizado
     */
    public Pagamento(double valor, Cliente origemCliente, Profissional destinoProfissional, MetodoPagamento metodo) {
        this.valor = valor;
        this.origemCliente = origemCliente;
        this.destinoProfissional = destinoProfissional;
        this.metodo = metodo;
        status = StatusPagamento.PENDENTE;
    }
	
}
