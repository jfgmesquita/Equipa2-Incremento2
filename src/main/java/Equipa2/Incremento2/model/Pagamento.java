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

    @OneToOne
    @JoinColumn(name = "solicitacao_id")
    private Solicitacao solicitacao;

    @ManyToOne
    @JoinColumn(name = "origem_cliente_id")
    private Cliente origemCliente;

    @ManyToOne
    @JoinColumn(name = "destino_profissional_id")
    private Profissional destinoProfissional;

    @Enumerated(EnumType.STRING)
    private StatusPagamento status;

    /**
     * Construtor que inicializa um pagamento com os dados fornecidos.
     *
     * @param valor o valor do pagamento
     * @param origemCliente o cliente que realiza o pagamento
     * @param destinoProfissional o profissional que recebe o pagamento
     */
    public Pagamento(double valor, Cliente origemCliente, Profissional destinoProfissional) {
        this.valor = valor;
        this.origemCliente = origemCliente;
        this.destinoProfissional = destinoProfissional;
        status = StatusPagamento.PENDENTE;
    }
	
}
