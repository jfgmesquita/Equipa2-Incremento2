package Equipa2.Incremento2.model;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDateTime;

import jakarta.persistence.*;

import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import Equipa2.Incremento2.model.enums.Servicos;

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

    @Enumerated(EnumType.STRING)
    private Servicos tipo;

    private String descricao;

    private LocalDateTime data;

    private double valorHora;

    
    @ManyToOne
    @JoinColumn(name = "profissional_id")
    @JsonIgnore
    private Profissional profissional;

    /**
     * Construtor que inicializa um novo serviço com os dados fornecidos.
     *
     * @param tipo         Tipo de serviço.
     * @param descricao    Descrição do serviço.
     * @param data         Data do serviço.
     * @param valorHora    Valor por hora do serviço.
     * @param profissional Profissional que realizará o serviço.
     */
    public Servico(Servicos tipo, String descricao, LocalDateTime data, double valorHora, Profissional profissional) {
        this.tipo = tipo;
        this.descricao = descricao;
        this.data = data;
        this.valorHora = valorHora;
        this.profissional = profissional;
    }
    
}
