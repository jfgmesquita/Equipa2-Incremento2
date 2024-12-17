package Equipa2.Incremento2.model;

import java.util.UUID;
import java.time.LocalDateTime;

import jakarta.persistence.*;

import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Classe que representa um serviço.
 */
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "Servico")
public class Servico {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "tipo_servico_id")
    private TiposServico tipoServico;

    private String descricao;

    private LocalDateTime data;

    private double valorHora;

    @ManyToOne
    @JoinColumn(name = "profissional_id")
    private Profissional profissional;

    /**
     * Construtor que inicializa um novo serviço com os dados fornecidos.
     *
     * @param tipoServico         Tipo de serviço.
     * @param descricao    Descrição do serviço.
     * @param data         Data do serviço.
     * @param valorHora    Valor por hora do serviço.
     * @param profissional Profissional que realizará o serviço.
     */
    public Servico(TiposServico tipoServico, String descricao, LocalDateTime data, double valorHora, Profissional profissional) {
        this.tipoServico = tipoServico;
        this.descricao = descricao;
        this.data = data;
        this.valorHora = valorHora;
        this.profissional = profissional;
    }
    
}
