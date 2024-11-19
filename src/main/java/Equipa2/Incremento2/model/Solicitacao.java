package Equipa2.Incremento2.model;

import java.util.UUID;

import Equipa2.Incremento2.model.enums.StatusServico;

import java.time.LocalDateTime;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Classe para representar a Solicitação do serviço.
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "solicitacao_de_servico")
@Inheritance(strategy = InheritanceType.JOINED)
public class Solicitacao {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Enumerated(EnumType.STRING)
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

	// @OneToOne(cascade = CascadeType.ALL)
	// private Pagamento pagamento;

    @Column(name = "data")
    private LocalDateTime data;
}
