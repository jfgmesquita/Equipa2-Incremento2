package Equipa2.Incremento2.model;

import java.util.UUID;

import jakarta.persistence.*;

import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Classe que representa uma avaliação.
 */
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Avaliacao {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private int valor;
    
    @ManyToOne
    @JoinColumn(name = "profissional_id")
    private Profissional profissional;
    
    private String descricao;

    private String data;
    
    @OneToOne
    @JoinColumn(name = "id_solicitacao", referencedColumnName = "id")
    private Solicitacao servico;
    
    /**
     * Construtor que inicializa uma avaliação com os dados fornecidos.
     *
     * @param valor o valor da avaliação
     * @param descricao a descrição da avaliação
     * @param servico a solicitação de serviço associada à avaliação
     */
    public Avaliacao(int valor, String descricao, Solicitacao servico) {
        this.valor = valor;
        this.descricao = descricao;
        this.servico = servico;
        data = "Hoje";
    }
    
}