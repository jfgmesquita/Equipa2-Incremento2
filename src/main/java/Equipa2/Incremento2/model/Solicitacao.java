package Equipa2.Incremento2.model;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDateTime;

import jakarta.persistence.*;

import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import Equipa2.Incremento2.model.enums.StatusServico;

/**
 * Classe que representa a Solicitação do serviço.
 */
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "solicitacao_de_servico")
@Inheritance(strategy = InheritanceType.JOINED)
public class Solicitacao {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = 50)
    private StatusServico status;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "profissional_id")
    private Profissional profissional;

    @ManyToOne
    @JoinColumn(name = "servico_id")
    private Servico servico;

    @OneToOne
    private Pagamento pagamento;

    @Column(name = "data")
    private LocalDateTime data;

    /**
     * Construtor que inicializa uma nova solicitação com os dados fornecidos.
     *
     * @param status       Status do serviço.
     * @param cliente      Cliente que solicitou o serviço.
     * @param profissional Profissional que realizará o serviço.
     * @param servico      Serviço solicitado.
     * @param pagamento    Pagamento associado à solicitação.
     * @param data         Data da solicitação.
     */
    public Solicitacao(StatusServico status, Cliente cliente, Profissional profissional, Servico servico, Pagamento pagamento, LocalDateTime data) {
        this.status = status;
        this.cliente = cliente;
        this.profissional = profissional;
        this.servico = servico;
        this.pagamento = pagamento;
        this.data = data;
    }
    
}
